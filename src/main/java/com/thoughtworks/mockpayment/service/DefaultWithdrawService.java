package com.thoughtworks.mockpayment.service;


import com.google.inject.Inject;
import com.thoughtworks.mockpayment.entity.withdraw.*;
import com.thoughtworks.mockpayment.persistence.mapper.WithdrawOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder.WithdrawStatus;
import com.thoughtworks.mockpayment.util.Json;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

public class DefaultWithdrawService implements WithdrawService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWithdrawService.class);
    private static final int WAIT_RESPONSE_WITHDRAW_RESULT = 1000;

    @Inject
    private WithdrawOrderMapper withdrawOrderMapper;

    @Override
    public String handleWithdrawRequest(Map<String, String> withdrawRequest) {
        logger.debug("*** in handle withdraw request ***" + withdrawRequest);

        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByOrderIdAndCustomerId(
            withdrawRequest.get("orderId"),
            withdrawRequest.get("customerId"));

        if (Objects.isNull(withdrawOrder)) {
            withdrawOrder = new WithdrawOrder(withdrawRequest);
            withdrawOrderMapper.insertNewOrder(withdrawOrder);
        }
        WithdrawResult withdrawResult = this.generateWithdrawResult(withdrawOrder);
        return Json.toJSON(withdrawResult);
    }

    @Override
    public String handleWithdrawQueryRequest (Map<String, String> queryRequest) {
        logger.debug("*** in handle withdraw request ***" + queryRequest);

        String flowId = queryRequest.get("flowId");
        String customerId = queryRequest.get("customerId");
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowIdAndCustomerId(flowId, customerId);
        WithdrawQueryResult queryResult = new WithdrawQueryResult(withdrawOrder);

        return Json.toJSON(queryResult);
    }

    private WithdrawResult generateWithdrawResult(WithdrawOrder withdrawOrder) {

        String withdrawStatusCode = BankCardNoAndWithdrawResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawOrder.getBankCardNo());
        WithdrawResponseCode withdrawResponseCode = WithdrawResponseCode.codeOf(withdrawStatusCode);

        String queryStatusCode = BankCardNoAndQueryResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawOrder.getBankCardNo());
        QueryResponseCode queryResponseCode = QueryResponseCode.codeOf(queryStatusCode);

        if (Objects.isNull(withdrawResponseCode) && Objects.isNull(queryResponseCode)) {
            withdrawResponseCode = WithdrawResponseCode.SUCCESS;
            queryResponseCode = QueryResponseCode.SUCCESS;
        }
        if (!Objects.isNull(withdrawResponseCode) && withdrawResponseCode.getStatus() == WithdrawStatus.SUCCESS) {
            queryResponseCode = QueryResponseCode.SUCCESS;
        }
        if (!Objects.isNull(withdrawResponseCode) && withdrawResponseCode.getStatus() != WithdrawStatus.SUCCESS) {
            queryResponseCode = QueryResponseCode.FAILURE_WITHDRAW;
        }
        if (Objects.isNull(withdrawResponseCode)) {
            withdrawResponseCode = WithdrawResponseCode.SUCCESS;
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
