package com.thoughtworks.mockpayment.entity;


public enum BankCardNoAndStatusCodeMap {

    SUCCESS("111111111111111", "1"),
    BANK_CARD_NO_ILLEGAL("222222222222222", "2"),
    UNSUPPORTED_BANK_CARD("333333333333333", "3"),
    BANK_CODE_NOT_MATCH_BANK_CARD_NO("444444444444444", "4"),
    ID_CARD_NO_ILLEGAL("555555555555555", "5"),
    INACTIVE_BANK_CARD_NO("666666666666666", "6"),
    LIMITED_BANK_CARD_NO("7777777777777777", "7"),
    SHORT_BALANCE_BANK_CARD_NO("888888888888888", "8"),
    EXPIRED_BALANCE_BANK_CARD_NO("9999999999999999", "9"),
    INVALID_BANK_CARD_NO("101010101010101", "10"),
    BANK_CARD_NO_NOT_MATCH_NAME("111111111111110", "11"),
    BANK_CARD_NO_NOT_MATCH_ID_CARD("121212121212121", "12"),
    AUTH_SYSTEM_ERROR("131313131313131", "13");


    private String bankCardNo;
    private String statusCode;

    BankCardNoAndStatusCodeMap(String bankCardNo, String statusCode) {

        this.bankCardNo= bankCardNo;
        this.statusCode = statusCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public static String fetchStatusCodeByBankCardNo(String bankCardNo) {

        for (BankCardNoAndStatusCodeMap bankCardNoAndStatusCodeMap : BankCardNoAndStatusCodeMap.values()) {
            if (bankCardNoAndStatusCodeMap.getBankCardNo().equals(bankCardNo)) {
                return bankCardNoAndStatusCodeMap.getStatusCode();
            }
        }
        return null;
    }
}
