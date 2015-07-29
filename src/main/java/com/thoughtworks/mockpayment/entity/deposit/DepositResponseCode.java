package com.thoughtworks.mockpayment.entity.deposit;


import com.thoughtworks.mockpayment.persistence.model.DepositOrder;
import com.thoughtworks.mockpayment.persistence.model.DepositOrder.DepositStatus;

public enum DepositResponseCode {

    SUCCESS("01", DepositOrder.DepositStatus.SUCCESS, "充值成功"),
    SHORT_BALANCE("02", DepositStatus.FAILURE, "余额不足"),
    EXCEED_BALANCE("03", DepositStatus.FAILURE, "超出充值限额"),
    LIMITED_BANK_CARD_NO("04", DepositStatus.FAILURE, "银行卡被限制"),
    EXPIRED_BALANCE_BANK_CARD_NO("05", DepositStatus.FAILURE, "银行卡过期"),
    DEPOSIT_PENDING("06", DepositOrder.DepositStatus.FAILURE, "系统繁忙"),
    DEPOSIT_SYSTEM_ERROR("07", DepositStatus.FAILURE, "充值系统异常"),
    ORDER_ID_REPEAT("08", DepositStatus.SUCCESS, "重复业务，订单号重复");

    private String code;
    private DepositStatus status;
    private String description;

    DepositResponseCode(String code, DepositOrder.DepositStatus status, String description) {

        this.code = code;
        this.status = status;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public DepositStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return this.description;
    }

    public static DepositResponseCode codeOf(String code) {

        for (DepositResponseCode responseCode : DepositResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }
}
