package com.thoughtworks.mockpayment.persistence.model;


import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class WithdrawOrder {

    private long id;
    private String withdrawFlowId;
    private String customerId;
    private String orderId;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String userName;
    private String amount;
    private WithdrawStatus withdrawStatus;
    private String responseCode;
    private String withdrawMessage;
    private Date withdrawAt;
    private Date createdAt;


    public WithdrawOrder(Map<String, String> request) {

        this.customerId = request.get("customerId");
        this.orderId = request.get("orderId");
        this.bankCode = request.get("bankCode");
        this.bankName = request.get("bankName");
        this.userName = request.get("userName");
        this.amount = request.get("amount");
        this.createdAt = DateTime.now().toDate();
        this.withdrawStatus = WithdrawStatus.PROCESSING;
        this.withdrawFlowId = UUID.randomUUID().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWithdrawFlowId() {
        return withdrawFlowId;
    }

    public void setWithdrawFlowId(String withdrawFlowId) {
        this.withdrawFlowId = withdrawFlowId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public WithdrawStatus getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(WithdrawStatus withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getWithdrawMessage() {
        return withdrawMessage;
    }

    public void setWithdrawMessage(String withdrawMessage) {
        this.withdrawMessage = withdrawMessage;
    }

    public Date getWithdrawAt() {
        return withdrawAt;
    }

    public void setWithdrawAt(Date withdrawAt) {
        this.withdrawAt = withdrawAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static enum WithdrawStatus {
        PROCESSING, SUCCESS, FAILURE
    }
}
