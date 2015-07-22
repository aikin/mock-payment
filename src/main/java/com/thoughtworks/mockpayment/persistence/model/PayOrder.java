package com.thoughtworks.mockpayment.persistence.model;

import org.joda.time.DateTime;

import java.util.Date;

public class PayOrder {

    private long id;
    private String customerId;
    private String orderId;
    private String userName;
    private String idCardNo;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String expandInfo;
    private String amount;
    private String currency;
    private Date createdAt;
    private String bankSerialNo;
    private PayStatus payStatus;
    private String payMessage;
    private Date payAt;

    public PayOrder() {

    }

    public PayOrder(String customerId,
                    String orderId,
                    String userName,
                    String idCardNo,
                    String bankCode,
                    String bankCardNo,
                    String bankName,
                    String expandInfo,
                    String amount,
                    String currency
    ) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.userName = userName;
        this.idCardNo = idCardNo;
        this.bankCode = bankCode;
        this.bankCardNo = bankCardNo;
        this.bankName = bankName;
        this.expandInfo = expandInfo;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = DateTime.now().toDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getExpandInfo() {
        return expandInfo;
    }

    public void setExpandInfo(String expandInfo) {
        this.expandInfo = expandInfo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayMessage() {
        return payMessage;
    }

    public void setPayMessage(String payMessage) {
        this.payMessage = payMessage;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public static enum PayStatus {
        WAIT_FOR_PAY, SUCCESS, FAILURE
    }

}
