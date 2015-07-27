package com.thoughtworks.mockpayment.entity.withdraw;


import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder.WithdrawStatus;

public enum WithdrawResponseCode {

    SUCCESS("001", WithdrawStatus.SUCCESS, "已接收"),
    WITHDRAW_ORDER_ID_REPEAT("002", WithdrawStatus.SUCCESS, "订单号不合法或重复"),
    WITHDRAW_PROCESSING("003", WithdrawStatus.PROCESSING, "取现处理中"),
    WITHDRAW_FAILURE_BANK_REJECT("004", WithdrawStatus.FAILURE, "已拒绝"),
    WITHDRAW_SYSTEM_ERROR("005", WithdrawStatus.FAILURE, "取现系统异常");

    private String code;
    private WithdrawStatus status;
    private String description;

    WithdrawResponseCode(String code, WithdrawStatus status, String description) {

        this.code = code;
        this.status = status;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public WithdrawStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return this.description;
    }

    public static WithdrawResponseCode codeOf(String code) {

        for (WithdrawResponseCode responseCode : WithdrawResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }
}
