package com.thoughtworks.mockpayment.service;

import com.thoughtworks.mockpayment.entity.bankCardAuth.BankAuthResponseCode;
import com.thoughtworks.mockpayment.entity.bankCardAuth.BankCardAuthResult;
import com.thoughtworks.mockpayment.entity.bankCardAuth.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultBankCardAuthService implements BankCardAuthService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBankCardAuthService.class);

    @Override
    public String handleBankCardAuthRequest(Map<String, String> request) {

        BankCardAuthResult result = this.generateAuthResult(request);
        return Json.toJSON(result);
    }

    private BankCardAuthResult generateAuthResult(Map<String, String> request) {

         BankCardAuthResult response = new BankCardAuthResult(
            request.get("cmd"),
            request.get("customerId"),
            request.get("orderId"),
            request.get("expandInfo")
        );
        String responseCode =  BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(request.get("bankCardNo"));
        BankAuthResponseCode bankAuthResponseCode = BankAuthResponseCode.codeOf(responseCode);

        if (bankAuthResponseCode == null) {
            logger.debug("*** input bankCardNo not match status ***" + request.get("bankCardNo"));
            bankAuthResponseCode = BankAuthResponseCode.SUCCESS;
        }
        response.setAuthStatus(bankAuthResponseCode.getStatus());
        response.setAuthMsg(bankAuthResponseCode.getDescription());
        response.setResponseCode(bankAuthResponseCode.getCode());
        return response;
    }


}
