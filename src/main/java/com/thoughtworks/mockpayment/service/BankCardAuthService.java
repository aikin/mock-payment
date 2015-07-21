package com.thoughtworks.mockpayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mockpayment.entity.AuthStatusCode;
import com.thoughtworks.mockpayment.entity.BankCardAuthResponse;
import com.thoughtworks.mockpayment.entity.BankCardNoAndStatusCodeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class BankCardAuthService {

    private static final Logger logger = LoggerFactory.getLogger(BankCardAuthService.class);

    public Response handleBankCardAuthRequest(MultivaluedMap<String, String> request) throws JsonProcessingException {

        BankCardAuthResponse result =  this.generateAuthResult(request);
        return Response.ok().entity(this.toJSON(result)).build();
    }

    private BankCardAuthResponse generateAuthResult(MultivaluedMap<String, String> request) {

         BankCardAuthResponse response = new BankCardAuthResponse(
            request.getFirst("cmd"),
            request.getFirst("customerId"),
            request.getFirst("orderId"),
            request.getFirst("expandInfo")
        );
        String responseCode =  BankCardNoAndStatusCodeMap.fetchStatusCodeByBankCardNo(request.getFirst("bankCardNo"));
        AuthStatusCode authStatusCode  = AuthStatusCode.codeOf(responseCode);

        if (authStatusCode == null) {
            logger.error("*** input bankCardNo not match status ***" + request.getFirst("bankCardNo"));
            authStatusCode = AuthStatusCode.SUCCESS;
        }
        response.setAuthStatus(authStatusCode.getStatus());
        response.setAuthMsg(authStatusCode.getDescription());
        response.setResponseCode(authStatusCode.getCode());
        return response;
    }

    private String toJSON(BankCardAuthResponse result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
}
