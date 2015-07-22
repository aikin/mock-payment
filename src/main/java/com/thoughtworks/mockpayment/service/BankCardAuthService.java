package com.thoughtworks.mockpayment.service;


import java.util.Map;

public interface BankCardAuthService {
    String handleBankCardAuthRequest(Map<String, String> request);
}
