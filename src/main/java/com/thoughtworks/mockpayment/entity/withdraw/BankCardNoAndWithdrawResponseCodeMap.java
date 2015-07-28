package com.thoughtworks.mockpayment.entity.withdraw;

//TODO: rename name
public enum BankCardNoAndWithdrawResponseCodeMap {

    SUCCESS("001001001001001", WithdrawResponseCode.SUCCESS.getCode()),
    WITHDRAW_ORDER_ID_REPEAT("002002002002002", WithdrawResponseCode.WITHDRAW_ORDER_ID_REPEAT.getCode()),
    WITHDRAW_PENDING("003003003003003", WithdrawResponseCode.WITHDRAW_PENDING.getCode()),
    WITHDRAW_FAILURE_BANK_REJECT("004004004004004", WithdrawResponseCode.WITHDRAW_FAILURE_BANK_REJECT.getCode()),
    WITHDRAW_SYSTEM_ERROR("005005005005005", WithdrawResponseCode.WITHDRAW_SYSTEM_ERROR.getCode());

    private String bankCardNo;
    private String responseCode;

    BankCardNoAndWithdrawResponseCodeMap(String bankCardNo, String responseCode) {

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

        for (BankCardNoAndWithdrawResponseCodeMap bankCardNoAndWithdrawResponseCodeMap : BankCardNoAndWithdrawResponseCodeMap.values()) {
            if (bankCardNoAndWithdrawResponseCodeMap.getBankCardNo().equals(bankCardNo)) {
                return bankCardNoAndWithdrawResponseCodeMap.getResponseCode();
            }
        }
        return null;
    }
}
