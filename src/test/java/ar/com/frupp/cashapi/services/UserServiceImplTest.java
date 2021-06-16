package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.Loan;
import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.exceptions.NotFoundException;
import ar.com.frupp.cashapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UserRepository repository;
    private UserService service;
    private User user;
    private int userId;

    @BeforeEach
    void setUp() {
        this.repository = Mockito.mock(UserRepository.class);
        this.service = new UserServiceImpl(this.repository);

        this.userId = 1;

        Loan loan = new Loan();
        this.user = new User();

        loan.setUser(this.user);
        this.user.setLoans(Collections.singleton(loan));

        loan.setTotal(2500.00);
        loan.setId(1);
        this.user.setId(this.userId);
        this.user.setEmail("user@test.com");
        this.user.setFirstName("Pepe");
        this.user.setLastName("Argento");
    }

    @Test
    void shouldFindUser() {
        int userId = this.user.getId();

        Mockito.when(this.repository.findById(userId)).thenReturn(Optional.ofNullable(this.user));

        User found = this.service.findById(userId);

        assertThat(found)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(found);
    }

    @Test
    void shouldThrowExceptionWhenNotFound() {
        Mockito.when(repository.findById(this.userId)).thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> this.service.findById(this.user.getId())
        );
    }
}