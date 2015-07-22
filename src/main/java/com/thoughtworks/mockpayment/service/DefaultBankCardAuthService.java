package com.thoughtworks.mockpayment.service;

import com.thoughtworks.mockpayment.entity.bankCardAuth.AuthStatusCode;
import com.thoughtworks.mockpayment.entity.bankCardAuth.BankCardAuthResponse;
import com.thoughtworks.mockpayment.entity.bankCardAuth.BankCardNoAndStatusCodeMapper;
import com.thoughtworks.mockpayment.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultBankCardAuthService implements BankCardAuthService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBankCardAuthService.class);

    @Override
    public String handleBankCardAuthRequest(Map<String, String> request) {

        BankCardAuthResponse result = this.generateAuthResult(request);
        return Json.toJSON(result);
    }

    private BankCardAuthResponse generateAuthResult(Map<String, String> request) {

         BankCardAuthResponse response = new BankCardAuthResponse(
            request.get("cmd"),
            request.get("customerId"),
            request.get("orderId"),
            request.get("expandInfo")
        );
        String responseCode =  BankCardNoAndStatusCodeMapper.fetchStatusCodeByBankCardNo(request.get("bankCardNo"));
        AuthStatusCode authStatusCode  = AuthStatusCode.codeOf(responseCode);

        if (authStatusCode == null) {
            logger.debug("*** input bankCardNo not match status ***" + request.get("bankCardNo"));
            authStatusCode = AuthStatusCode.SUCCESS;
        }
        response.setAuthStatus(authStatusCode.getStatus());
        response.setAuthMsg(authStatusCode.getDescription());
        response.setResponseCode(authStatusCode.getCode());
        return response;
    }


}
