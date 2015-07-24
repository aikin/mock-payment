package com.thoughtworks.mockpayment.service;


import com.thoughtworks.mockpayment.entity.withdraw.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResponseCode;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DefaultWithdrawService implements WithdrawService {


    private static final Logger logger = LoggerFactory.getLogger(DefaultWithdrawService.class);

    @Override
    public String handleWithdrawRequest(Map<String, String> withdrawRequest) {

        logger.debug("*** in handle withdraw request ***" + withdrawRequest);


        WithdrawOrder withdrawOrder = new WithdrawOrder(withdrawRequest);

        String responseCode =  BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawRequest.getBankCardNo());
        WithdrawResponseCode withdrawResponseCode = WithdrawResponseCode.codeOf(responseCode);


        if (withdrawResponseCode == null) {
            logger.debug("*** input bankCardNo not match status deposits response code ***" + withdrawOrder.getBankCardNo());
            withdrawResponseCode = WithdrawResponseCode.SUCCESS;
        }


        return null;
    }
}
