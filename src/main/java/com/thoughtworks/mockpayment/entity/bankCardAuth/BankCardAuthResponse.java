package com.thoughtworks.mockpayment.entity.bankCardAuth;


import java.util.UUID;

public class BankCardAuthResponse {

    private String command;
    private String customerId;
    private String orderId;
    private String expandInfo;
    private String flowId;
    private String authStatus;
    private String responseCode;
    private String authMsg;

    public BankCardAuthResponse(String command, String customerId, String orderId, String expandInfo) {
        this.command = command;
        this.customerId = customerId;
        this.orderId = orderId;
        this.expandInfo = expandInfo;
        this.flowId = UUID.randomUUID().toString();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public String getExpandInfo() {
        return expandInfo;
    }

    public void setExpandInfo(String expandInfo) {
        this.expandInfo = expandInfo;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getAuthMsg() {
        return authMsg;
    }

    public void setAuthMsg(String authMsg) {
        this.authMsg = authMsg;
    }

}
