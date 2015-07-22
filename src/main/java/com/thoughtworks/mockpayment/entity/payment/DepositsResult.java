package com.thoughtworks.mockpayment.entity.payment;

public class DepositsResult {

    private String depositsFlowId;
    private String depositsMessage;
    private String responseCode;
    private String depositsAt;
    private String bankSerialNo;
    private String customerId;
    private String orderId;
    private String amount;
    private String currency;
    private String expandInfo;

    public DepositsResult(String depositsFlowId,
                          String customerId,
                          String orderId,
                          String amount,
                          String currency,
                          String expandInfo
    ) {

        this.depositsFlowId = depositsFlowId;
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.expandInfo = expandInfo;
    }

    public String getDepositsFlowId() {
        return depositsFlowId;
    }

    public void setDepositsFlowId(String depositsFlowId) {
        this.depositsFlowId = depositsFlowId;
    }

    public String getDepositsMessage() {
        return depositsMessage;
    }

    public void setDepositsMessage(String depositsMessage) {
        this.depositsMessage = depositsMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDepositsAt() {
        return depositsAt;
    }

    public void setDepositsAt(String depositsAt) {
        this.depositsAt = depositsAt;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
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

    public String getExpandInfo() {
        return expandInfo;
    }

    public void setExpandInfo(String expandInfo) {
        this.expandInfo = expandInfo;
    }
}
