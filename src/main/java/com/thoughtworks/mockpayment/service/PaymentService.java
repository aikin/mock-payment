package com.thoughtworks.mockpayment.service;

import java.util.Map;


public interface PaymentService {
    String handleDepositsRequest(Map<String, String> request);

    String handleDepositsQueryRequest(Map<String, String> request);
}
