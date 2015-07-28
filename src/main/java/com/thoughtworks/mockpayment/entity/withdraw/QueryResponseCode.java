package com.thoughtworks.mockpayment.entity.withdraw;


import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder.QueryStatus;

public enum QueryResponseCode {

    SUCCESS("0001", QueryStatus.SUCCESS, "取现成功"),
    QUERY_PENDING("0002", QueryStatus.PENDING, "银行处理中"),
    QUERY_FAILURE_BANK_REJECT("0003", QueryStatus.FAILURE, "银行拒绝"),
    QUERY_SYSTEM_ERROR("0004", QueryStatus.FAILURE, "取款失败"),
    FAILURE_WITHDRAW("0005", QueryStatus.FAILURE, "查询前取现已经失败");

    private String code;
    private QueryStatus status;
    private String description;

    QueryResponseCode(String code, QueryStatus status, String description) {

        this.code = code;
        this.status = status;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public QueryStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return this.description;
    }

    public static QueryResponseCode codeOf(String code) {

        for (QueryResponseCode responseCode : QueryResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }
}
