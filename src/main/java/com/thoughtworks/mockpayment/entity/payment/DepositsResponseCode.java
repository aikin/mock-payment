package com.thoughtworks.mockpayment.entity.payment;


import com.thoughtworks.mockpayment.persistence.model.DepositsOrder.DepositsStatus;

public enum DepositsResponseCode {

    SUCCESS("01", DepositsStatus.SUCCESS, "充值成功"),
    SHORT_BALANCE("02", DepositsStatus.FAILURE, "余额不足"),
    EXCEED_BALANCE("03", DepositsStatus.FAILURE, "超出充值限额"),
    LIMITED_BANK_CARD_NO("04", DepositsStatus.FAILURE, "银行卡被限制"),
    EXPIRED_BALANCE_BANK_CARD_NO("05", DepositsStatus.FAILURE, "银行卡过期"),
    DEPOSITS_PROCESSING("06", DepositsStatus.FAILURE, "系统繁忙"),
    DEPOSITS_SYSTEM_ERROR("07", DepositsStatus.FAILURE, "充值系统异常"),
    ORDER_ID_REPEAT("08", DepositsStatus.SUCCESS, "重复业务，订单号重复");

    private String code;
    private DepositsStatus status;
    private String description;

    DepositsResponseCode(String code, DepositsStatus status, String description) {

        this.code = code;
        this.status = status;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public DepositsStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return this.description;
    }

    public static DepositsResponseCode codeOf(String code) {

        for (DepositsResponseCode responseCode : DepositsResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }
}
