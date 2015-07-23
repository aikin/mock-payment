CREATE TABLE DEPOSITS_ORDER (
    ID               BIGINT PRIMARY KEY       AUTO_INCREMENT,
    DEPOSITS_FLOW_ID VARCHAR(64) UNIQUE,
    CUSTOMER_ID      VARCHAR(64) NOT NULL,
    ORDER_ID         VARCHAR(64) UNIQUE  NOT NULL,
    USER_NAME        VARCHAR(16)    NOT NULL,
    ID_CARD_NO       VARCHAR(64)    NOT NULL,
    BANK_CODE        VARCHAR(16)    NOT NULL,
    BANK_CARD_NO     VARCHAR(32)    NOT NULL,
    BANK_NAME        VARCHAR(16),
    EXPAND_INFO      VARCHAR(128),
    AMOUNT           NUMERIC(15, 2) NOT NULL,
    CURRENCY         VARCHAR(16),
    BANK_SERIAL_NO   VARCHAR(128)    NOT NULL  DEFAULT '',
    DEPOSITS_STATUS  VARCHAR(64)    NOT NULL,
    DEPOSITS_MESSAGE VARCHAR(128)   NOT NULL  DEFAULT '',
    RESPONSE_CODE    VARCHAR(16),
    DEPOSITS_AT      DATETIME,
    CREATED_AT       DATETIME       NOT NULL

)ENGINE = InnoDB DEFAULT CHARSET = utf8;