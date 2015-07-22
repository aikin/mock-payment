package com.thoughtworks.mockpayment.persistence.mapper;

import com.thoughtworks.mockpayment.persistence.model.DepositsOrder;
import com.thoughtworks.mockpayment.persistence.model.DepositsOrder.DepositsStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface DepositsOrderMapper {

     void insertNewOrder(@Param("order") DepositsOrder newOrder);

     void updatePayStatus(@Param("orderId") String orderId,
                          @Param("payStatus") DepositsStatus payStatus,
                          @Param("payMessage") String payMessage,
                          @Param("payAt") Date payAt);
}
