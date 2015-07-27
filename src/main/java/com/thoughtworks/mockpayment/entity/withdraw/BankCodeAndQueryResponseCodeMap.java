package com.thoughtworks.mockpayment.entity.withdraw;


public enum BankCodeAndQueryResponseCodeMap {

    SUCCESS("PBC", QueryResponseCode.SUCCESS.getCode()),
    QUERY_PROCESSING("BOC", QueryResponseCode.QUERY_PROCESSING.getCode()),
    QUERY_FAILURE_BANK_REJECT("ICBC", QueryResponseCode.QUERY_FAILURE_BANK_REJECT.getCode()),
    QUERY_SYSTEM_ERROR("CCB", QueryResponseCode.QUERY_SYSTEM_ERROR.getCode()),
    UN_SET_QUERY("UN_USE_BANK_CODE", QueryResponseCode.UN_SET_QUERY.getCode());

    private String bankCode;
    private String responseCode;

    BankCodeAndQueryResponseCodeMap(String bankCode, String responseCode) {

        this.bankCode = bankCode;
        this.responseCode = responseCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public static String fetchStatusCodeByBankCode(String bankCode) {

        for (BankCodeAndQueryResponseCodeMap bankCodeAndQueryResponseCodeMap : BankCodeAndQueryResponseCodeMap.values()) {
            if (bankCodeAndQueryResponseCodeMap.getBankCode().equals(bankCode)) {
                return bankCodeAndQueryResponseCodeMap.getResponseCode();
            }
        }
        return null;
    }
}
