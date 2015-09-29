package com.thoughtworks.mockpayment.persistence.model;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class DepositOrder {

    private long id;
    private String flowId;
    private String customerId;
    private String orderId;
    private String userName;
    private String idCardNo;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String amount;
    private Date createdAt;
    private DepositStatus depositStatus;
    private String responseCode;
    private String depositMessage;
    private Date depositAt;

    public DepositOrder() {

    }

    public DepositOrder(Map<String, Object> depositRequest) {
        this.customerId = depositRequest.get("customerId").toString();
        this.orderId = depositRequest.get("orderId").toString();
        this.userName = depositRequest.get("name").toString();
        this.idCardNo = depositRequest.get("pin").toString();
        this.bankCode = depositRequest.get("bankCode").toString();
        this.bankCardNo = depositRequest.get("cardNo").toString();
        this.bankName = depositRequest.get("bankName").toString();
        this.amount = depositRequest.get("amount").toString();
        this.createdAt = DateTime.now().toDate();
        this.flowId = UUID.randomUUID().toString();
        this.depositStatus = DepositStatus.PENDING;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public DepositStatus getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(DepositStatus depositStatus) {
        this.depositStatus = depositStatus;
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

    public static enum DepositStatus {
        PENDING, SUCCESS, FAILURE
    }
}
