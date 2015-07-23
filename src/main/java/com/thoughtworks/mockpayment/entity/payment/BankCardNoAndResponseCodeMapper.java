package com.thoughtworks.mockpayment.entity.payment;

//TODO: 改名字
public enum BankCardNoAndResponseCodeMapper {

    SHORT_BALANCE("020202020202020", DepositsResponseCode.SHORT_BALANCE.getCode()),
    EXCEED_BALANCE("030303030303030", DepositsResponseCode.EXCEED_BALANCE.getCode()),
    LIMITED_BANK_CARD_NO("040404040404040", DepositsResponseCode.LIMITED_BANK_CARD_NO.getCode()),
    EXPIRED_BALANCE_BANK_CARD_NO("050505050505050", DepositsResponseCode.EXPIRED_BALANCE_BANK_CARD_NO.getCode()),
    DEPOSITS_PROCESSING("060606060606060", DepositsResponseCode.DEPOSITS_PROCESSING.getCode()),
    DEPOSITS_SYSTEM_ERROR("070707070707070", DepositsResponseCode.DEPOSITS_SYSTEM_ERROR.getCode());

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
