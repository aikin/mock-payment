package com.thoughtworks.mockpayment.service;


import com.google.inject.Inject;
import com.thoughtworks.mockpayment.entity.withdraw.BankCardNoAndWithdrawResponseCodeMap;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawQueryResult;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResponseCode;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResult;
import com.thoughtworks.mockpayment.persistence.mapper.WithdrawOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import com.thoughtworks.mockpayment.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

public class DefaultWithdrawService implements WithdrawService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultWithdrawService.class);

    @Inject
    private WithdrawOrderMapper withdrawOrderMapper;

    @Override
    public String handleWithdrawRequest(Map<String, Object> withdrawRequest) {
        logger.debug("*** in handle withdraw request ***" + withdrawRequest);

        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByOrderIdAndCustomerId(
            withdrawRequest.get("orderId").toString(),
            withdrawRequest.get("customerId").toString());

        if (Objects.isNull(withdrawOrder)) {
            withdrawOrder = new WithdrawOrder(withdrawRequest);
            withdrawOrderMapper.insertNewOrder(withdrawOrder);
        }
        WithdrawResult withdrawResult = this.generateWithdrawResult(withdrawOrder);
        return Json.toJSON(withdrawResult);
    }

    @Override
    public String handleWithdrawQueryRequest(Map<String, Object> queryRequest) {
        logger.debug("*** in handle withdraw request ***" + queryRequest);

        String flowId = queryRequest.get("flowId").toString();
        String customerId = queryRequest.get("customerId").toString();
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowIdAndCustomerId(flowId, customerId);
        WithdrawQueryResult queryResult = new WithdrawQueryResult(withdrawOrder);

        return Json.toJSON(queryResult);
    }

    private WithdrawResult generateWithdrawResult(WithdrawOrder withdrawOrder) {

        String withdrawStatusCode = BankCardNoAndWithdrawResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawOrder.getBankCardNo());
        WithdrawResponseCode withdrawResponseCode = WithdrawResponseCode.codeOf(withdrawStatusCode);

        if (Objects.isNull(withdrawResponseCode)) {
            withdrawResponseCode = WithdrawResponseCode.SUCCESS;
        }

        WithdrawResult withdrawResult = new WithdrawResult(withdrawOrder, withdrawResponseCode);
        this.handleWithdrawOrder(withdrawResult, withdrawResponseCode);
        return withdrawResult;
    }

    private void handleWithdrawOrder(WithdrawResult withdrawResult, WithdrawResponseCode withdrawResponseCode) {
        this.withdrawOrderMapper.updateWithdrawStatus(
            withdrawResult.getFlowId(),
            withdrawResult.getWithdrawAt(),
            withdrawResponseCode.getStatus(),
            withdrawResponseCode.getDescription(),
            withdrawResponseCode.getCode()
        );
    }
}
