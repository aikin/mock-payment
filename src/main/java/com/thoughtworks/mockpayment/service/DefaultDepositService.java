package com.thoughtworks.mockpayment.service;

import com.thoughtworks.mockpayment.entity.deposit.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.deposit.DepositQueryResult;
import com.thoughtworks.mockpayment.entity.deposit.DepositResponseCode;
import com.thoughtworks.mockpayment.entity.deposit.DepositResult;
import com.thoughtworks.mockpayment.persistence.mapper.DepositOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.DepositOrder;
import com.thoughtworks.mockpayment.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Map;
import java.util.Objects;

public class DefaultDepositService implements DepositService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultDepositService.class);

    @Inject
    private DepositOrderMapper depositOrderMapper;

    @Override
    public String handleDepositRequest(Map<String, String> depositRequest) {
        logger.debug("*** in handle deposit request ***" + depositRequest);

        DepositOrder depositOrder = depositOrderMapper.findOrderByOrderIdAndCustomerId(
            depositRequest.get("orderId"),
            depositRequest.get("customerId"));

        if (Objects.isNull(depositOrder)) {
            depositOrder = new DepositOrder(depositRequest);
            depositOrderMapper.insertNewOrder(depositOrder);
        }

        DepositResult depositResult = this.generateDepositResult(depositOrder);

        return Json.toJSON(depositResult);
    }

    @Override
    public String handleDepositQueryRequest(Map<String, String> queryRequest) {
        logger.debug("*** in handle withdraw request ***" + queryRequest);

        String flowId = queryRequest.get("flowId");
        String customerId = queryRequest.get("customerId");
        DepositOrder depositOrder = depositOrderMapper.findOrderByFlowIdAndCustomerId(flowId, customerId);
        DepositQueryResult queryResult = new DepositQueryResult(depositOrder);

        return Json.toJSON(queryResult);
    }

    private DepositResult generateDepositResult(DepositOrder depositOrder) {
        String responseCode = BankCardNoAndResponseCodeMap.fetchStatusCodeByBankCardNo(depositOrder.getBankCardNo());
        DepositResponseCode depositResponseCode = DepositResponseCode.codeOf(responseCode);

        if (Objects.isNull(depositResponseCode)) {
            logger.debug("*** input bankCardNo not match status deposit response code ***" + depositOrder.getBankCardNo());
            depositResponseCode = DepositResponseCode.SUCCESS;
        }

        DepositResult depositResult = new DepositResult(depositOrder, depositResponseCode);
        this.handleDepositOrder(depositResult, depositResponseCode);
        return depositResult;
    }

    private void handleDepositOrder(DepositResult depositResult, DepositResponseCode depositResponseCode) {
        this.depositOrderMapper.updateDepositStatus(
            depositResult.getFlowId(),
            depositResponseCode.getStatus(),
            depositResponseCode.getDescription(),
            depositResponseCode.getCode(),
            depositResult.getDepositAt()
        );
    }
}
