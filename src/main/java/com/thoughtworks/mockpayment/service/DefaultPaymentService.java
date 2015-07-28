package com.thoughtworks.mockpayment.service;

import com.thoughtworks.mockpayment.entity.payment.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.payment.DepositQueryResult;
import com.thoughtworks.mockpayment.entity.payment.DepositsResponseCode;
import com.thoughtworks.mockpayment.entity.payment.DepositsResult;
import com.thoughtworks.mockpayment.persistence.mapper.DepositsOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;
import com.thoughtworks.mockpayment.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;

public class DefaultPaymentService implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultPaymentService.class);
    private static final int WAIT_RESPONSE_DEPOSITS_RESULT = 1000;

    @Inject
    private DepositsOrderMapper depositsOrderMapper;

    @Override
    public String handleDepositsRequest(Map<String, String> depositsRequest) {

        logger.debug("*** in handle deposits request ***" + depositsRequest);

        DepositsOrder depositsOrder = new DepositsOrder(depositsRequest);
        depositsOrderMapper.insertNewOrder(depositsOrder);
        DepositsResult depositsResult = this.generateDepositsResult(depositsOrder);

        return Json.toJSON(depositsResult);
    }

    @Override
    public String handleDepositsQueryRequest(Map<String, String> queryRequest) {
        logger.debug("*** in handle withdraw request ***" + queryRequest);

        String orderId = queryRequest.get("orderId");
        String flowId = queryRequest.get("flowId");
        DepositsOrder depositOrder = depositsOrderMapper.findOrderByFlowId(flowId);
        DepositQueryResult queryResult = new DepositQueryResult(depositOrder);

        return Json.toJSON(queryResult);
    }

    private DepositsResult generateDepositsResult(DepositsOrder depositsOrder) {

        String responseCode =  BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(depositsOrder.getBankCardNo());
        DepositsResponseCode depositsResponseCode = DepositsResponseCode.codeOf(responseCode);

        if (depositsResponseCode == null) {
            logger.debug("*** input bankCardNo not match status deposits response code ***" + depositsOrder.getBankCardNo());
            depositsResponseCode = DepositsResponseCode.SUCCESS;
        }

        DepositsResult depositsResult = new DepositsResult(depositsOrder, depositsResponseCode);
        this.handleDepositsOrder(depositsResult, depositsResponseCode);
        return depositsResult;
    }

    private void handleDepositsOrder(DepositsResult depositsResult, DepositsResponseCode depositsResponseCode) {

        try {
            Thread.sleep(WAIT_RESPONSE_DEPOSITS_RESULT);
        } catch (InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        }

        this.depositsOrderMapper.updateDepositsStatus(
            depositsResult.getDepositsFlowId(),
            depositsResponseCode.getStatus(),
            depositsResponseCode.getDescription(),
            depositsResponseCode.getCode(),
            depositsResult.getDepositsAt(),
            depositsResult.getBankSerialNo()
        );
    }
}
