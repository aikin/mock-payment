package com.thoughtworks.mockpayment.service;

import java.util.Map;


public interface DepositService {
    String handleDepositRequest(Map<String, Object> request);

    String handleDepositQueryRequest(Map<String, Object> request);
}
