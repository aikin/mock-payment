package com.thoughtworks.mockpayment.service;


import com.google.inject.Inject;
import com.thoughtworks.mockpayment.entity.withdraw.*;
import com.thoughtworks.mockpayment.persistence.mapper.WithdrawOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import com.thoughtworks.mockpayment.util.Json;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultWithdrawService implements WithdrawService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWithdrawService.class);
    private static final int WAIT_RESPONSE_WITHDRAW_RESULT = 1000;

    @Inject
    private WithdrawOrderMapper withdrawOrderMapper;

    @Override
    public String handleWithdrawRequest(Map<String, String> withdrawRequest) {
        logger.debug("*** in handle withdraw request ***" + withdrawRequest);

        WithdrawOrder withdrawOrder = new WithdrawOrder(withdrawRequest);
        withdrawOrderMapper.insertNewOrder(withdrawOrder);

        WithdrawResult withdrawResult = this.generateWithdrawResult(withdrawOrder);
        return Json.toJSON(withdrawResult);
    }

    @Override
    public String handleWithdrawQueryRequest (Map<String, String> queryRequest) {
        logger.debug("*** in handle withdraw request ***" + queryRequest);

        String orderId = queryRequest.get("orderId");
        String flowId = queryRequest.get("flowId");
//        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByOrderId(orderId);
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowId(flowId);

        return Json.toJSON(withdrawOrder);
    }

    private WithdrawResult generateWithdrawResult(WithdrawOrder withdrawOrder) {

        String responseCode =  BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawOrder.getBankCardNo());
        WithdrawResponseCode withdrawResponseCode = WithdrawResponseCode.codeOf(responseCode);

        if (withdrawResponseCode == null) {
            logger.debug("*** input bankCardNo not match status withdraw response code ***" + withdrawOrder.getBankCardNo());
            withdrawResponseCode = WithdrawResponseCode.SUCCESS;
        }

        String statusCode =  BankCodeAndQueryResponseCodeMap.fetchStatusCodeByBankCode(withdrawOrder.getBankCode());
        QueryResponseCode queryResponseCode = QueryResponseCode.codeOf(statusCode);

        if (queryResponseCode == null) {
            logger.debug("*** input bankCardNo not match status withdraw response code ***" + withdrawOrder.getBankCardNo());
            queryResponseCode = QueryResponseCode.SUCCESS;
        }

        WithdrawResult withdrawResult = new WithdrawResult(withdrawOrder, withdrawResponseCode);
        this.handleWithdrawOrder(withdrawResult, withdrawResponseCode, queryResponseCode);
        return withdrawResult;
    }

    private void handleWithdrawOrder(WithdrawResult withdrawResult, WithdrawResponseCode withdrawResponseCode, QueryResponseCode queryResponseCode) {
        try {
            Thread.sleep(WAIT_RESPONSE_WITHDRAW_RESULT);
        } catch (InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        }

        this.withdrawOrderMapper.updateWithdrawStatus(
            withdrawResult.getWithdrawFlowId(),
            withdrawResult.getWithdrawAt(),
            withdrawResponseCode.getStatus(),
            withdrawResponseCode.getDescription(),
            withdrawResponseCode.getCode()
        );

        this.withdrawOrderMapper.updateQueryStatus(
            withdrawResult.getWithdrawFlowId(),
            DateTime.now().toDate(),
            queryResponseCode.getStatus(),
            queryResponseCode.getDescription(),
            queryResponseCode.getCode()
        );
    }
}
