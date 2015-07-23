package com.thoughtworks.mockpayment.persistence.model;


import org.joda.time.DateTime;

import java.util.Date;

public class WithdrawOrder {

    private long id;
    private String batchNo;
    private String customerId;
    private String orderId;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String userName;
    private String idCardNo;
    private String amount;
    private WithdrawStatus withdrawStatus;
    private String responseCode;
    private String withdrawMessage;
    private Date withdrawAt;
    private Date createdAt;


    public WithdrawOrder(String batchNo,
                         String customerId,
                         String orderId,
                         String userName,
                         String idCardNo,
                         String bankCode,
                         String bankCardNo,
                         String bankName,
                         String amount
    ) {
        this.batchNo = batchNo;
        this.customerId = customerId;
        this.orderId = orderId;
        this.bankCode = bankCode;
        this.bankCardNo = bankCardNo;
        this.bankName = bankName;
        this.userName = userName;
        this.idCardNo = idCardNo;
        this.amount = amount;
        this.createdAt = DateTime.now().toDate();
        this.withdrawStatus = WithdrawStatus.PROCESSING;
    }

    public static enum WithdrawStatus {
        PROCESSING, SUCCESS, FAILURE
    }
}
