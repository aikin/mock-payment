package com.thoughtworks.mockpayment.entity.withdraw;


public enum BankCardNoAndResponseCodeMapper {

    SUCCESS("001001001001001", WithdrawResponseCode.SUCCESS.getCode()),
    WITHDRAW_ORDER_ID_REPEAT("002002002002002", WithdrawResponseCode.WITHDRAW_ORDER_ID_REPEAT.getCode()),
    WITHDRAW_PROCESSING("003003003003003", WithdrawResponseCode.WITHDRAW_PROCESSING.getCode()),
    WITHDRAW_FAILURE_BANK_REJECT("004004004004004", WithdrawResponseCode.WITHDRAW_FAILURE_BANK_REJECT.getCode()),
    WITHDRAW_SYSTEM_ERROR("005005005005005", WithdrawResponseCode.WITHDRAW_SYSTEM_ERROR.getCode());

    private String bankCardNo;
    private String responseCode;

    BankCardNoAndResponseCodeMapper(String bankCardNo, String responseCode) {

        this.bankCardNo = bankCardNo;
        this.responseCode = responseCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public static String fetchStatusCodeByBankCardNo(String bankCardNo) {

        for (BankCardNoAndResponseCodeMapper bankCardNoAndResponseCodeMapper : BankCardNoAndResponseCodeMapper.values()) {
            if (bankCardNoAndResponseCodeMapper.getBankCardNo().equals(bankCardNo)) {
                return bankCardNoAndResponseCodeMapper.getResponseCode();
            }
        }
        return null;
    }
}
