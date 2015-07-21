CREATE TABLE MOCK_PAYMENT_PAY_ORDER (
  ID              BIGINT          PRIMARY KEY AUTO_INCREMENT,
  CUSTOMER_ID     VARCHAR(64),
  ORDER_ID        VARCHAR(64)     NOT NULL,
  CREATED_AT      DATETIME        NOT NULL,
  BANK_CODE       VARCHAR(16)     NOT NULL,
  BANK_CARD_NO    VARCHAR(32)     NOT NULL,
  PROVINCE        VARCHAR(16),
  CITY            VARCHAR(16),
  BANK_NAME       VARCHAR(16),
  USER_NAME       VARCHAR(16)     NOT NULL,
  ID_CARD_NO      VARCHAR(64)     NOT NULL,
  AMOUNT          NUMERIC(15, 2)  NOT NULL,
  PIVATE_AREA     VARCHAR(128)  ,
  CURRENCY        VARCHAR(16),
  NOTIFY_URL      VARCHAR(128)    NOT NULL,
  BANK_SERIAL_NO  VARCHAR(64)     NOT NULL  DEFAULT '',
  PAY_STATUS      VARCHAR(16)     NOT NULL,
  PAY_MESSAGE     VARCHAR(128)    NOT NULL DEFAULT '',
  PAY_AT          DATETIME,
  SYNC_STATUS     VARCHAR(16)     NOT NULL,
  SYNC_AT         DATETIME

)ENGINE=InnoDB DEFAULT CHARSET=utf8;
