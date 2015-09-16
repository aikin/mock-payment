package com.thoughtworks.mockpayment.service;


import java.util.Map;

public interface WithdrawService {

    String handleWithdrawRequest(Map<String, Object> request);

    String handleWithdrawQueryRequest(Map<String, Object> request);
}
