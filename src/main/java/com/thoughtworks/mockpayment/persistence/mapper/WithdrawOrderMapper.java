package com.thoughtworks.mockpayment.persistence.mapper;

import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder.WithdrawStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface WithdrawOrderMapper {

     void insertNewOrder(@Param("order") WithdrawOrder newOrder);

     void updateWithdrawStatus(@Param("flowId") String withdrawFlowId,
                               @Param("withdrawAt") Date withdrawAt,
                               @Param("withdrawStatus") WithdrawStatus withdrawStatus,
                               @Param("withdrawMessage") String withdrawMessage,
                               @Param("responseCode") String responseCode);

    WithdrawOrder findOrderByFlowIdAndCustomerId(@Param("flowId") String withdrawFlowId,
                                    @Param("customerId") String customerId);

    WithdrawOrder findOrderByOrderIdAndCustomerId(@Param("orderId") String orderId,
                                    @Param("customerId") String customerId);
}
