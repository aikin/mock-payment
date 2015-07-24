package com.thoughtworks.mockpayment.service;


import com.google.inject.Inject;
import com.thoughtworks.mockpayment.entity.withdraw.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResponseCode;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResult;
import com.thoughtworks.mockpayment.persistence.mapper.WithdrawOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import com.thoughtworks.mockpayment.util.Json;
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

    private WithdrawResult generateWithdrawResult(WithdrawOrder withdrawOrder) {

        String responseCode =  BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawOrder.getBankCardNo());
        WithdrawResponseCode withdrawResponseCode = WithdrawResponseCode.codeOf(responseCode);

        if (withdrawResponseCode == null) {
            logger.debug("*** input bankCardNo not match status withdraw response code ***" + withdrawOrder.getBankCardNo());
            withdrawResponseCode = WithdrawResponseCode.SUCCESS;
        }

        WithdrawResult withdrawResult = new WithdrawResult(withdrawOrder, withdrawResponseCode);
        this.handleWithdrawOrder(withdrawResult, withdrawResponseCode);
        return withdrawResult;
    }

    private void handleWithdrawOrder(WithdrawResult withdrawResult, WithdrawResponseCode withdrawResponseCode) {
        try {
            Thread.sleep(WAIT_RESPONSE_WITHDRAW_RESULT);
        } catch (InterruptedException e) {
            logger.error(e.getLocalizedMessage(), e);
        }

        this.withdrawOrderMapper.updateWithdrawStatus(
            withdrawResult.getWithdrawFlowId(),
            withdrawResult.getWithdrawAt(),
            withdrawResponseCode.getStatus(),
            withdrawResponseCode.getCode(),
            withdrawResponseCode.getDescription()
        );
    }
}
