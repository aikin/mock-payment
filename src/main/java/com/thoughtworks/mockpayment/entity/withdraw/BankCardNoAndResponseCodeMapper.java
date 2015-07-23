package com.thoughtworks.mockpayment.entity.withdraw;


public enum BankCardNoAndResponseCodeMapper {

    SUCCESS("001001001001001", "001"),
    WITHDRAW_ORDER_ID_REPEAT("002002002002002", "002"),
    WITHDRAW_PROCESSING("003003003003003", "003"),
    WITHDRAW_FAILURE_BANK_REJECT("004004004004004", "004"),
    WITHDRAW_SYSTEM_ERROR("005005005005005", "005");

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
