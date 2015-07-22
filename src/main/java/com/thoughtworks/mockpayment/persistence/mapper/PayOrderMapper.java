package com.thoughtworks.mockpayment.persistence.mapper;

import com.thoughtworks.mockpayment.persistence.model.PayOrder;
import com.thoughtworks.mockpayment.persistence.model.PayOrder.PayStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface PayOrderMapper {

     void insertNewOrder(@Param("order") PayOrder newOrder);

     void updatePayStatus(@Param("orderId") String orderId,
                          @Param("payStatus") PayStatus payStatus,
                          @Param("payMessage") String payMessage,
                          @Param("payAt") Date payAt);
}
