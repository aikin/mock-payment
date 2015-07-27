package com.thoughtworks.mockpayment.persistence.model;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class DepositsOrder {

    private long id;
    private String depositsFlowId;
    private String customerId;
    private String orderId;
    private String userName;
    private String idCardNo;
    private String bankCode;
    private String bankCardNo;
    private String bankName;
    private String expandInfo;
    private String amount;
    private String currency;
    private Date createdAt;
    private String bankSerialNo;
    private DepositsStatus depositsStatus;
    private String responseCode;
    private String depositsMessage;
    private Date depositsAt;


    public DepositsOrder() {

    }

    public DepositsOrder(Map<String, String> depositsRequest) {

        this.customerId = depositsRequest.get("customerId");
        this.orderId = depositsRequest.get("orderId");
        this.userName = depositsRequest.get("userName");
        this.idCardNo = depositsRequest.get("idCardNo");
        this.bankCode = depositsRequest.get("bankCode");
        this.bankCardNo = depositsRequest.get("bankCardNo");
        this.bankName = depositsRequest.get("bankName");
        this.expandInfo = depositsRequest.get("expandInfo");
        this.amount = depositsRequest.get("amount");
        this.currency = depositsRequest.get("currency");
        this.createdAt = DateTime.now().toDate();
        this.depositsFlowId = UUID.randomUUID().toString();
        this.depositsStatus = DepositsStatus.PROCESSING;
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

    public String getExpandInfo() {
        return expandInfo;
    }

    public void setExpandInfo(String expandInfo) {
        this.expandInfo = expandInfo;
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

    public String getDepositsFlowId() {
        return depositsFlowId;
    }

    public void setDepositsFlowId(String depositsFlowId) {
        this.depositsFlowId = depositsFlowId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBankSerialNo() {
        return bankSerialNo;
    }

    public void setBankSerialNo(String bankSerialNo) {
        this.bankSerialNo = bankSerialNo;
    }

    public DepositsStatus getDepositsStatus() {
        return depositsStatus;
    }

    public void setDepositsStatus(DepositsStatus depositsStatus) {
        this.depositsStatus = depositsStatus;
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

    public static enum DepositsStatus {
        PROCESSING, SUCCESS, FAILURE
    }
}
