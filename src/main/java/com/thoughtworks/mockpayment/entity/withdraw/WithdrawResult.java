package com.thoughtworks.mockpayment.entity.withdraw;


import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import org.joda.time.DateTime;

import java.util.Date;

public class WithdrawResult {

    private String withdrawFlowId;
    private String responseCode;
    private String withdrawMessage;
    private Date withdrawAt;
    private String customerId;
    private String orderId;
    private String amount;
    private String bankCardNo;

    public WithdrawResult(WithdrawOrder withdrawOrder, WithdrawResponseCode withdrawResponseCode) {

        this.withdrawFlowId = withdrawOrder.getWithdrawFlowId();
        this.customerId = withdrawOrder.getCustomerId();
        this.orderId = withdrawOrder.getOrderId();
        this.amount = withdrawOrder.getAmount();
        this.bankCardNo = withdrawOrder.getBankCardNo();
        this.responseCode = withdrawResponseCode.getCode();
        this.withdrawMessage = withdrawResponseCode.getDescription();
        this.withdrawAt = DateTime.now().toDate();
    }

    public String getWithdrawFlowId() {
        return withdrawFlowId;
    }

    public void setWithdrawFlowId(String withdrawFlowId) {
        this.withdrawFlowId = withdrawFlowId;
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
