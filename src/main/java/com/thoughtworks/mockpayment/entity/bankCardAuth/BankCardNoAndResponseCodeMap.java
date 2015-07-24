package com.thoughtworks.mockpayment.entity.bankCardAuth;


public enum BankCardNoAndResponseCodeMap {

    BANK_CARD_NO_ILLEGAL("222222222222222", BankAuthResponseCode.BANK_CARD_NO_ILLEGAL.getCode()),
    UNSUPPORTED_BANK_CARD("333333333333333", BankAuthResponseCode.UNSUPPORTED_BANK_CARD.getCode()),
    BANK_CODE_NOT_MATCH_BANK_CARD_NO("444444444444444", BankAuthResponseCode.BANK_CODE_NOT_MATCH_BANK_CARD_NO.getCode()),
    ID_CARD_NO_ILLEGAL("555555555555555", BankAuthResponseCode.ID_CARD_NO_ILLEGAL.getCode()),
    INACTIVE_BANK_CARD_NO("666666666666666", BankAuthResponseCode.INACTIVE_BANK_CARD_NO.getCode()),
    LIMITED_BANK_CARD_NO("7777777777777777", BankAuthResponseCode.LIMITED_BANK_CARD_NO.getCode()),
    SHORT_BALANCE_BANK_CARD_NO("888888888888888", BankAuthResponseCode.SHORT_BALANCE_BANK_CARD_NO.getCode()),
    EXPIRED_BALANCE_BANK_CARD_NO("9999999999999999", BankAuthResponseCode.EXPIRED_BALANCE_BANK_CARD_NO.getCode()),
    INVALID_BANK_CARD_NO("101010101010101", BankAuthResponseCode.INVALID_BANK_CARD_NO.getCode()),
    BANK_CARD_NO_NOT_MATCH_NAME("111111111111110", BankAuthResponseCode.BANK_CARD_NO_NOT_MATCH_NAME.getCode()),
    BANK_CARD_NO_NOT_MATCH_ID_CARD("121212121212121", BankAuthResponseCode.BANK_CARD_NO_NOT_MATCH_ID_CARD.getCode()),
    AUTH_SYSTEM_ERROR("131313131313131", BankAuthResponseCode.AUTH_SYSTEM_ERROR.getCode());


    private String bankCardNo;
    private String responseCode;

    BankCardNoAndResponseCodeMap(String bankCardNo, String responseCode) {

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

        for (BankCardNoAndResponseCodeMap bankCardNoAndResponseCodeMap : BankCardNoAndResponseCodeMap.values()) {
            if (bankCardNoAndResponseCodeMap.getBankCardNo().equals(bankCardNo)) {
                return bankCardNoAndResponseCodeMap.getResponseCode();
            }
        }
        return null;
    }
}
