package com.thoughtworks.mockpayment.persistence.handler;

import com.thoughtworks.mockpayment.persistence.mapper.PayOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.PayOrder;

import javax.inject.Inject;
import java.util.Map;

public class PaymentHandler {

    @Inject
    private PayOrderMapper payOrderMapper;


    public void insertPayOrder(Map<String, String> depositsRequest) {

        PayOrder payOrder = new PayOrder(
            depositsRequest.get("customerId"),
            depositsRequest.get("orderId"),
            depositsRequest.get("userName"),
            depositsRequest.get("idCardNo"),
            depositsRequest.get("bankCode"),
            depositsRequest.get("bankCardNo"),
            depositsRequest.get("bankName"),
            depositsRequest.get("expandInfo"),
            depositsRequest.get("amount"),
            depositsRequest.get("currency")
        );

        payOrderMapper.insertNewOrder(payOrder);
    }
}
