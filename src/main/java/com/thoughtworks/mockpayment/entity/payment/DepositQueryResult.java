package com.thoughtworks.mockpayment.entity.payment;


import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;

import java.util.Date;

public class DepositQueryResult {


    private String flowId;
    private String message;
    private String responseCode;
    private Date depositsAt;
    private String bankSerialNo;
    private String orderId;
    private String amount;

    public DepositQueryResult(DepositsOrder depositOrder) {

        this.flowId = depositOrder.getDepositsFlowId();
        this.orderId = depositOrder.getOrderId();
        this.amount = depositOrder.getAmount();
        this.responseCode = depositOrder.getResponseCode();
        this.message = depositOrder.getDepositsMessage();
        this.depositsAt = depositOrder.getDepositsAt();
        this.bankSerialNo = depositOrder.getBankSerialNo();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Date getDepositsAt() {
        return depositsAt;
    }

    public void setDepositsAt(Date depositsAt) {
        this.depositsAt = depositsAt;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
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
}
