package com.thoughtworks.mockpayment.persistence.handler;

import com.thoughtworks.mockpayment.persistence.mapper.DepositsOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;

import javax.inject.Inject;
import java.util.Map;

public class PaymentHandler {

    @Inject
    private DepositsOrderMapper depositsOrderMapper;


    public void insertPayOrder(Map<String, String> depositsRequest) {

        DepositsOrder depositsOrder = new DepositsOrder(
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



        depositsOrderMapper.insertNewOrder(depositsOrder);


    }
}
