package com.thoughtworks.mockpayment.service;

import com.thoughtworks.mockpayment.entity.payment.BankCardNoAndResponseCodeMapper;
import com.thoughtworks.mockpayment.entity.payment.DepositsResponseCode;
import com.thoughtworks.mockpayment.entity.payment.DepositsResult;
import com.thoughtworks.mockpayment.persistence.handler.PaymentHandler;
import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;
import com.thoughtworks.mockpayment.util.Json;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class DefaultPaymentService implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultBankCardAuthService.class);

    @Inject
    private PaymentHandler paymentHandler;


    @Override
    public String handleDepositsRequest(Map<String, String> depositsRequest) {

        logger.debug("handle deposits" + depositsRequest);


        DepositsOrder depositsOrder = this.paymentHandler.insertPayOrder(depositsRequest);
        DepositsResult depositsResult = this.generateDepositsResult(depositsOrder);

        return Json.toJSON(depositsResult);
    }

    private DepositsResult generateDepositsResult(DepositsOrder depositsOrder) {

        DepositsResult depositsResult = new DepositsResult(
            depositsOrder.getDepositsFlowId(),
            depositsOrder.getCustomerId(),
            depositsOrder.getOrderId(),
            depositsOrder.getAmount(),
            depositsOrder.getCurrency(),
            depositsOrder.getExpandInfo()
        );

        String responseCode =  BankCardNoAndResponseCodeMapper.fetchStatusCodeByBankCardNo(depositsOrder.getBankCardNo());
        DepositsResponseCode depositsResponseCode = DepositsResponseCode.codeOf(responseCode);

        if (depositsResponseCode == null) {
            logger.debug("*** input bankCardNo not match status ***" + depositsOrder.getBankCardNo());
            depositsResponseCode = DepositsResponseCode.SUCCESS;
        }

       return this.handleDepositsOrder(depositsResult, depositsResponseCode);
    }

    private DepositsResult handleDepositsOrder(DepositsResult depositsResult, DepositsResponseCode depositsResponseCode) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        }

        String code = depositsResponseCode.getCode();
        String depositsMessage = depositsResponseCode.getDescription();
        Date depositsAt = DateTime.now().toDate();
        String bankSerialNo = DateTime.now().toDate().toString();

        depositsResult.setResponseCode(code);
        depositsResult.setDepositsMessage(depositsMessage);
        depositsResult.setBankSerialNo(bankSerialNo);
        depositsResult.setDepositsAt(depositsAt.toString());

        this.paymentHandler.updateDepositsStatus(
            depositsResult.getDepositsFlowId(),
            depositsResponseCode.getStatus(),
            code,
            depositsMessage,
            depositsAt,
            bankSerialNo
        );

        return depositsResult;
    }
}
