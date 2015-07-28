package com.thoughtworks.mockpayment.persistence.mapper;

import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;
import com.thoughtworks.mockpayment.persistence.model.DepositsOrder.DepositsStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface DepositsOrderMapper {

     void insertNewOrder(@Param("order") DepositsOrder newOrder);

     void updateDepositsStatus(@Param("depositsFlowId") String depositsFlowId,
                               @Param("depositsStatus") DepositsStatus depositsStatus,
                               @Param("depositsMessage") String depositsMessage,
                               @Param("responseCode") String responseCode,
                               @Param("depositsAt") Date depositsAt,
                               @Param("bankSerialNo") String bankSerialNo);

    DepositsOrder findOrderByFlowId(@Param("depositsFlowId") String depositsFlowId);
}
