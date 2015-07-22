package com.thoughtworks.mockpayment.entity.payment;


public enum BankCardNoAndResponseCodeMapper {

    SHORT_BALANCE("020202020202020", "02"),
    EXCEED_BALANCE("030303030303030", "03"),
    LIMITED_BANK_CARD_NO("040404040404040", "04"),
    EXPIRED_BALANCE_BANK_CARD_NO("050505050505050", "05"),
    DEPOSITS_PROCESSING("060606060606060",  "06"),
    DEPOSITS_SYSTEM_ERROR("070707070707070", "07");

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
