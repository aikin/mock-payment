package com.thoughtworks.mockpayment.service;

import com.thoughtworks.mockpayment.persistence.handler.PaymentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

public class DefaultPaymentService implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBankCardAuthService.class);

    @Inject
    private PaymentHandler paymentHandler;

    @Override
    public String handleDepositsRequest(Map<String, String> depositsRequest) {

        paymentHandler.insertPayOrder(depositsRequest);
        logger.debug("handle deposits" + depositsRequest);
        return null;
    }
}
