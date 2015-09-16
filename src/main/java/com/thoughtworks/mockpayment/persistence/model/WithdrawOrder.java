package com.thoughtworks.mockpayment.persistence.model;


import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class WithdrawOrder {

    private long id;
    private String flowId;
    private String customerId;
    private String orderId;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String userName;
    private double amount;
    private WithdrawStatus withdrawStatus;
    private String responseCode;
    private String withdrawMessage;
    private Date withdrawAt;
    private Date createdAt;

    public WithdrawOrder() {

    }

    public WithdrawOrder(Map<String, Object> request) {
        this.customerId = request.get("customerId").toString();
        this.orderId = request.get("orderId").toString();
        this.bankCode = request.get("bankCode").toString();
        this.bankName = request.get("bankName").toString();
        this.bankCardNo = request.get("bankCardNo").toString();
        this.userName = request.get("userName").toString();
        this.amount = Double.valueOf(request.get("amount").toString());
        this.createdAt = DateTime.now().toDate();
        this.withdrawStatus = WithdrawStatus.PENDING;
        this.flowId = UUID.randomUUID().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
        PENDING, SUCCESS, FAILURE
    }
}
