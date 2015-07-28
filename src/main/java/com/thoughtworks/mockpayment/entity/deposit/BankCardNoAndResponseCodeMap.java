package com.thoughtworks.mockpayment.entity.deposit;


public enum BankCardNoAndResponseCodeMap {

    SHORT_BALANCE("020202020202020", DepositResponseCode.SHORT_BALANCE.getCode()),
    EXCEED_BALANCE("030303030303030", DepositResponseCode.EXCEED_BALANCE.getCode()),
    LIMITED_BANK_CARD_NO("040404040404040", DepositResponseCode.LIMITED_BANK_CARD_NO.getCode()),
    EXPIRED_BALANCE_BANK_CARD_NO("050505050505050", DepositResponseCode.EXPIRED_BALANCE_BANK_CARD_NO.getCode()),
    DEPOSIT_PROCESSING("060606060606060", DepositResponseCode.DEPOSIT_PROCESSING.getCode()),
    DEPOSIT_SYSTEM_ERROR("070707070707070", DepositResponseCode.DEPOSIT_SYSTEM_ERROR.getCode()),
    ORDER_ID_REPEAT("080808080808080", DepositResponseCode.ORDER_ID_REPEAT.getCode());

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
