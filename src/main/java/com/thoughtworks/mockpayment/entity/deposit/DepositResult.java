package com.thoughtworks.mockpayment.entity.deposit;

import com.thoughtworks.mockpayment.persistence.model.DepositOrder;
import org.joda.time.DateTime;

import java.util.Date;

public class DepositResult {

    private String flowId;
    private String message;
    private String responseCode;
    private Date depositAt;
    private String customerId;
    private String orderId;
    private String amount;

    public DepositResult(DepositOrder depositOrder, DepositResponseCode depositResponseCode) {
        this.flowId = depositOrder.getFlowId();
        this.customerId = depositOrder.getCustomerId();
        this.orderId = depositOrder.getOrderId();
        this.amount = depositOrder.getAmount();
        this.responseCode = depositResponseCode.getCode();
        this.message = depositResponseCode.getDescription();
        this.depositAt = DateTime.now().toDate();
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

    public Date getDepositAt() {
        return depositAt;
    }

    public void setDepositAt(Date depositAt) {
        this.depositAt = depositAt;
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

}
