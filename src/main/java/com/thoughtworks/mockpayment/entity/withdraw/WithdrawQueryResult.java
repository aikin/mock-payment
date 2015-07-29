package com.thoughtworks.mockpayment.entity.withdraw;


import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;

import java.util.Date;

public class WithdrawQueryResult {

    private String withdrawFlowId;
    private String queryResponseCode;
    private String queryMessage;
    private String queryStatus;
    private Date queryAt;
    private String customerId;
    private String orderId;
    private Double amount;
    private String bankCode;
    private String userName;
    private String bankCardNo;

    public WithdrawQueryResult(WithdrawOrder withdrawOrder) {
        this.withdrawFlowId = withdrawOrder.getWithdrawFlowId();
        this.queryResponseCode = withdrawOrder.getQueryResponseCode();
        this.queryMessage = withdrawOrder.getQueryMessage();
        this.queryStatus = withdrawOrder.getQueryMessage();
        this.queryAt = withdrawOrder.getQueryAt();
        this.customerId = withdrawOrder.getCustomerId();
        this.orderId = withdrawOrder.getOrderId();
        this.amount = withdrawOrder.getAmount();
        this.bankCardNo = withdrawOrder.getBankCardNo();
        this.bankCode = withdrawOrder.getBankCode();
        this.userName = withdrawOrder.getUserName();
    }

    public String getWithdrawFlowId() {
        return withdrawFlowId;
    }

    public void setWithdrawFlowId(String withdrawFlowId) {
        this.withdrawFlowId = withdrawFlowId;
    }

    public String getQueryResponseCode() {
        return queryResponseCode;
    }

    public void setQueryResponseCode(String queryResponseCode) {
        this.queryResponseCode = queryResponseCode;
    }

    public String getQueryMessage() {
        return queryMessage;
    }

    public void setQueryMessage(String queryMessage) {
        this.queryMessage = queryMessage;
    }

    public String getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }

    public Date getQueryAt() {
        return queryAt;
    }

    public void setQueryAt(Date queryAt) {
        this.queryAt = queryAt;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }
}
