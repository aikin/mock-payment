package com.thoughtworks.mockpayment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.Map;

public class DefaultPaymentService implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBankCardAuthService.class);

    @Override
    public Response handleDepositsRequest(Map<String, String> request) {

        return null;
    }
}
