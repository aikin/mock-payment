package com.thoughtworks.mockpayment.persistence.mapper;

import com.thoughtworks.mockpayment.persistence.model.DepositOrder;
import com.thoughtworks.mockpayment.persistence.model.DepositOrder.DepositStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface DepositOrderMapper {

    void insertNewOrder(@Param("order") DepositOrder newOrder);

    void insertNewFullOrder(@Param("order") DepositOrder newOrder);

    void updateDepositStatus(@Param("depositFlowId") String depositFlowId,
                              @Param("depositStatus") DepositStatus depositStatus,
                              @Param("depositMessage") String depositMessage,
                              @Param("responseCode") String responseCode,
                              @Param("depositAt") Date depositAt,
                              @Param("bankSerialNo") String bankSerialNo);

    DepositOrder findOrderByFlowIdAndCustomerId(@Param("depositFlowId") String depositFlowId,
                                                @Param("customerId") String customerId);


    DepositOrder findOrderByOrderIdAndCustomerId(@Param("orderId") String orderId,
                                                 @Param("customerId") String customerId);
}
