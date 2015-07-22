package com.thoughtworks.mockpayment.entity.bankCardAuth;

public enum AuthStatusCode {

    SUCCESS("1", "SUCCESS", "认证成功"),
    BANK_CARD_NO_ILLEGAL("2", "FAILURE", "卡号不合法"),
    UNSUPPORTED_BANK_CARD("3", "FAILURE", "不支持此银行卡"),
    BANK_CODE_NOT_MATCH_BANK_CARD_NO("4", "FAILURE", "银行编码和银行卡号不匹配"),
    ID_CARD_NO_ILLEGAL("5", "FAILURE", "非法的身份证号"),
    INACTIVE_BANK_CARD_NO("6", "FAILURE", "银行卡未激活"),
    LIMITED_BANK_CARD_NO("7", "FAILURE", "银行卡被限制，详情请咨询发卡行"),
    SHORT_BALANCE_BANK_CARD_NO("8", "FAILURE", "银行卡余额不足，不支持认证请求"),
    EXPIRED_BALANCE_BANK_CARD_NO("9", "FAILURE", "银行卡过期"),
    INVALID_BANK_CARD_NO("10", "FAILURE", "银行卡号无效"),
    BANK_CARD_NO_NOT_MATCH_NAME("11", "FAILURE", "银行卡与姓名不符"),
    BANK_CARD_NO_NOT_MATCH_ID_CARD("12", "FAILURE", "银行卡与证件不符"),
    AUTH_SYSTEM_ERROR("13", "FAILURE", "认证系统异常");


    private String code;
    private String description;
    private String status;

    AuthStatusCode(String code, String status, String description) {

        this.code = code;
        this.status = status;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public static AuthStatusCode codeOf(String code) {

        for (AuthStatusCode offerStatus : AuthStatusCode.values()) {
            if (offerStatus.getCode().equals(code)) {
                return offerStatus;
            }
        }
        return null;
    }
}





/* 卡号不合法  B0021
 * 不支持此银行卡 B0022
 * 银行编码和银行卡号不匹配 B0023
 * 非法的身份证号 B0032
 * 证件类型与证件号码不符 B0037
 * 银行卡号无效 B0039
 * 银行卡未激活 B0041
 * 银行卡被限制,详情请咨询发卡行 B0042
 * 银行卡余额不足,不支持认证请求 B0043
 * 您输入的证件号或姓名有误 B0049
 * 证件类型与卡号不符 B0050
 *
 * 银行卡过期 B0044
 * 银行处理失败  B0045
 *
 *
 *
 * 认证系统异常 B0007
 * 不支持银行异常  B0008
 * 客户不存在异常 B0011
 * 系统异常 S0001
 * 未知系统异常 S9999
 * 不支持的认证模式 B0010
 * 易宝账户不可用 B0025
 * 商户未激活  B0030
 * 认证参数被篡改 B0033
 * 商户订单号已存在 B0034
 *
 *
 * 该银行卡未开通银联在线支付业务 B0038
 * 您已连续多次输入错误密码 B0040
 * 银行返回为空 B0046
 * 未知银行系统错误 B0047
 *
 */
