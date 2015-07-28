package com.thoughtworks.mockpayment.service;

import java.util.Map;


public interface DepositService {
    String handleDepositRequest(Map<String, String> request);

    String handleDepositQueryRequest(Map<String, String> request);
}
