package com.thoughtworks.mockpayment.service;

import javax.ws.rs.core.Response;
import java.util.Map;


public interface PaymentService {
    Response handleDepositsRequest(Map<String, String> request);
}
