package com.thoughtworks.mockpayment.entity.withdraw;


import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import org.joda.time.DateTime;

import java.util.Date;

public class WithdrawResult {

    private String flowId;
    private String responseCode;
    private String withdrawMessage;
    private Date withdrawAt;
    private String customerId;
    private String orderId;
    private String amount;
    private String bankCardNo;

    public WithdrawResult(WithdrawOrder withdrawOrder, WithdrawResponseCode withdrawResponseCode) {

        this.flowId = withdrawOrder.getFlowId();
        this.customerId = withdrawOrder.getCustomerId();
        this.orderId = withdrawOrder.getOrderId();
        this.amount = String.valueOf(withdrawOrder.getAmount());
        this.bankCardNo = withdrawOrder.getBankCardNo();
        this.responseCode = withdrawResponseCode.getCode();
        this.withdrawMessage = withdrawResponseCode.getDescription();
        this.withdrawAt = DateTime.now().toDate();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }
}
