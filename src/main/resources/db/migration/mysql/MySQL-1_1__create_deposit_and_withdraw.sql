CREATE TABLE DEPOSIT_ORDER (
    ID              BIGINT PRIMARY KEY            AUTO_INCREMENT,
    FLOW_ID         VARCHAR(64) UNIQUE,
    CUSTOMER_ID     VARCHAR(64)         NOT NULL,
    ORDER_ID        VARCHAR(64) UNIQUE  NOT NULL,
    USER_NAME       VARCHAR(16)         NOT NULL,
    BANK_CODE       VARCHAR(16)         NOT NULL,
    BANK_CARD_NO    VARCHAR(32)         NOT NULL,
    BANK_NAME       VARCHAR(16),
    AMOUNT          NUMERIC(15, 2)      NOT NULL,
    BANK_SERIAL_NO  VARCHAR(128)        NOT NULL  DEFAULT '',
    DEPOSIT_STATUS  VARCHAR(64)         NOT NULL,
    DEPOSIT_MESSAGE VARCHAR(128)        NOT NULL  DEFAULT '',
    RESPONSE_CODE   VARCHAR(16),
    DEPOSIT_AT      DATETIME,
    CREATED_AT      DATETIME            NOT NULL

) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE WITHDRAW_ORDER (
    ID               BIGINT PRIMARY KEY            AUTO_INCREMENT,
    FLOW_ID          VARCHAR(64) UNIQUE,
    CUSTOMER_ID      VARCHAR(64)         NOT NULL,
    ORDER_ID         VARCHAR(64) UNIQUE  NOT NULL,
    USER_NAME        VARCHAR(16)         NOT NULL,
    BANK_CODE        VARCHAR(16)         NOT NULL,
    BANK_CARD_NO     VARCHAR(32)         NOT NULL,
    BANK_NAME        VARCHAR(16),
    AMOUNT           NUMERIC(15, 2)      NOT NULL,
    WITHDRAW_STATUS  VARCHAR(64)         NOT NULL,
    WITHDRAW_MESSAGE VARCHAR(128)        NOT NULL  DEFAULT '',
    RESPONSE_CODE    VARCHAR(16),
    WITHDRAW_AT      DATETIME,
    CREATED_AT       DATETIME            NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
