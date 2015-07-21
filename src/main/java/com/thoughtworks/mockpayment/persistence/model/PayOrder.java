package com.thoughtworks.mockpayment.persistence.model;

import java.util.Date;

public class PayOrder {

    private long id;
    private String customerId;
    private String orderId;
    private Date createdAt;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String userName;
    private String idCardNo;
    private double amount;
    private String currency;
//    private String notifyUrl;
    private String bankSerialNo;
    private PayStatus payStatus;
    private String payMessage;
    private Date payAt;
//    private SyncStatus syncStatus;

    public static enum PayStatus {
        WAIT_FOR_PAY, SUCCESS, FAILURE
    }


    public static enum SyncStatus {
        WAIT_FOR_SYNC, SYNCED
    }

}
