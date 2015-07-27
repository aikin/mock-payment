package com.thoughtworks.mockpayment.persistence.mapper;

import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder.WithdrawStatus;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder.QueryStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface WithdrawOrderMapper {

     void insertNewOrder(@Param("order") WithdrawOrder newOrder);

     void updateWithdrawStatus(@Param("withdrawFlowId") String withdrawFlowId,
                               @Param("withdrawAt") Date withdrawAt,
                               @Param("withdrawStatus") WithdrawStatus withdrawStatus,
                               @Param("withdrawMessage") String withdrawMessage,
                               @Param("responseCode") String responseCode);

    void updateQueryStatus(@Param("withdrawFlowId") String withdrawFlowId,
                              @Param("queryAt") Date queryAt,
                              @Param("queryStatus") QueryStatus withdrawStatus,
                              @Param("queryMessage") String queryMessage,
                              @Param("queryResponseCode") String queryResponseCode);

    WithdrawOrder findOrderByFlowId(@Param("withdrawFlowId") String withdrawFlowId);
}
