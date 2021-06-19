CREATE TABLE USER (
    ID IDENTITY NOT NULL PRIMARY KEY,
    EMAIL VARCHAR(50) NOT NULL,
    FIRST_NAME VARCHAR(20) NOT NULL,
    LAST_NAME VARCHAR(20) NOT NULL
);

CREATE TABLE LOAN (
    ID IDENTITY NOT NULL PRIMARY KEY,
    TOTAL DOUBLE NOT NULL,
    USER_ID BIGINT,

    FOREIGN KEY (USER_ID) REFERENCES USER (ID)
);