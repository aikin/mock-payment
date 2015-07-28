package com.thoughtworks.mockpayment.entity.withdraw;


public enum BankCardNoAndQueryResponseCodeMap {

    QUERY_SUCCESS("000100010001000", QueryResponseCode.SUCCESS.getCode()),
    QUERY_PENDING("000200020002000", QueryResponseCode.QUERY_PENDING.getCode()),
    QUERY_FAILURE_BANK_REJECT("000300030003000", QueryResponseCode.QUERY_FAILURE_BANK_REJECT.getCode()),
    QUERY_SYSTEM_ERROR("0004000400040004000", QueryResponseCode.QUERY_SYSTEM_ERROR.getCode());

    private String bankCardNo;
    private String responseCode;

    BankCardNoAndQueryResponseCodeMap(String bankCardNo, String responseCode) {

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

        for (BankCardNoAndQueryResponseCodeMap bankCardNoAndQueryResponseCodeMap : BankCardNoAndQueryResponseCodeMap.values()) {
            if (bankCardNoAndQueryResponseCodeMap.getBankCardNo().equals(bankCardNo)) {
                return bankCardNoAndQueryResponseCodeMap.getResponseCode();
            }
        }
        return null;
    }
}
