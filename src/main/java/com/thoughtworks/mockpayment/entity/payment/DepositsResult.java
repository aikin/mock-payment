package com.thoughtworks.mockpayment.entity.payment;

import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;
import org.joda.time.DateTime;

import java.util.Date;

public class DepositsResult {

    private String depositsFlowId;
    private String depositsMessage;
    private String responseCode;
    private Date depositsAt;
    private String bankSerialNo;
    private String customerId;
    private String orderId;
    private String amount;
    private String currency;
    private String expandInfo;


    public DepositsResult(DepositsOrder depositsOrder, DepositsResponseCode depositsResponseCode) {

        this.depositsFlowId = depositsOrder.getDepositsFlowId();
        this.customerId = depositsOrder.getCustomerId();
        this.orderId = depositsOrder.getOrderId();
        this.amount = depositsOrder.getAmount();
        this.currency = depositsOrder.getCurrency();
        this.expandInfo = depositsOrder.getExpandInfo();
        this.responseCode = depositsResponseCode.getCode();
        this.depositsMessage = depositsResponseCode.getDescription();
        this.depositsAt = DateTime.now().toDate();
        this.bankSerialNo = DateTime.now().toDate().toString();
    }

    public DepositsResult(DepositsOrder depositOrder) {

        this.depositsFlowId = depositOrder.getDepositsFlowId();
        this.customerId = depositOrder.getCustomerId();
        this.orderId = depositOrder.getOrderId();
        this.amount = depositOrder.getAmount();
        this.currency = depositOrder.getCurrency();
        this.expandInfo = depositOrder.getExpandInfo();
        this.responseCode = depositOrder.getResponseCode();
        this.depositsMessage = depositOrder.getDepositsMessage();
        this.depositsAt = depositOrder.getDepositsAt();
        this.bankSerialNo = depositOrder.getBankSerialNo();
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
