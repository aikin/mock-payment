package com.thoughtworks.mockpayment.entity.deposit;

import com.thoughtworks.mockpayment.persistence.model.DepositOrder;
import org.joda.time.DateTime;

import java.util.Date;

public class DepositResult {

    private String depositFlowId;
    private String depositMessage;
    private String responseCode;
    private Date depositAt;
    private String bankSerialNo;
    private String customerId;
    private String orderId;
    private String amount;
    private String currency;
    private String expandInfo;


    public DepositResult(DepositOrder depositOrder, DepositResponseCode depositResponseCode) {
        this.depositFlowId = depositOrder.getDepositFlowId();
        this.customerId = depositOrder.getCustomerId();
        this.orderId = depositOrder.getOrderId();
        this.amount = depositOrder.getAmount();
        this.currency = depositOrder.getCurrency();
        this.expandInfo = depositOrder.getExpandInfo();
        this.responseCode = depositResponseCode.getCode();
        this.depositMessage = depositResponseCode.getDescription();
        this.depositAt = DateTime.now().toDate();
        this.bankSerialNo = DateTime.now().toDate().toString();
    }

    public String getDepositFlowId() {
        return depositFlowId;
    }

    public void setDepositFlowId(String depositFlowId) {
        this.depositFlowId = depositFlowId;
    }

    public String getDepositMessage() {
        return depositMessage;
    }

    public void setDepositMessage(String depositMessage) {
        this.depositMessage = depositMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Date getDepositAt() {
        return depositAt;
    }

    public void setDepositAt(Date depositAt) {
        this.depositAt = depositAt;
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
