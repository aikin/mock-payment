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
    public String handleWithdrawRequest(Map withdrawRequest) {

        logger.debug("*** in handle withdraw request ***" + withdrawRequest);

        List items = (List) withdrawRequest.get("items");

        // TODO: need refactor
        String customerId  = (String) withdrawRequest.get("customerId");
        String batchNo = (String) withdrawRequest.get("batchNo");


        for (int i = 0; i < items.size(); i++) {

            Map item = (Map) items.get(i);
            WithdrawOrder withdrawOrder = new WithdrawOrder(
                batchNo,
                customerId,
                item.get("id").toString(),
                item.get("userName").toString(),
                item.get("bankCode").toString(),
                item.get("bankCardNo").toString(),
                item.get("bankName").toString(),
                item.get("amount").toString()
            );
            String responseCode =  BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(withdrawOrder.getBankCardNo());
            WithdrawResponseCode withdrawResponseCode = WithdrawResponseCode.codeOf(responseCode);

            if (withdrawResponseCode == null) {
                logger.debug("*** input bankCardNo not match status deposits response code ***" + withdrawOrder.getBankCardNo());
                withdrawResponseCode = WithdrawResponseCode.SUCCESS;
            }

        }
        return null;
    }
}
