package com.thoughtworks.mockpayment.entity.withdraw;


import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;

public class WithdrawQueryResult {

    private String withdrawFlowId;
    private String customerId;
    private String orderId;
    private Double amount;
    private String bankCode;
    private String userName;
    private String bankCardNo;

    public WithdrawQueryResult(WithdrawOrder withdrawOrder) {
        this.withdrawFlowId = withdrawOrder.getFlowId();
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
