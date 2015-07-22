package com.thoughtworks.mockpayment.service;


import javax.ws.rs.core.Response;
import java.util.Map;

public interface BankCardAuthService {
    Response handleBankCardAuthRequest(Map<String, String> request);
}
