package com.thoughtworks.mockpayment.resource;


import com.thoughtworks.mockpayment.service.WithdrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("withdraw")
public class WithdrawResource {

    private static final Logger logger = LoggerFactory.getLogger(PaymentResource.class);

    @Inject
    private WithdrawService withdrawService;

//    @GET
//    public Response withdraw(@QueryParam("cmd") String cmd,
//                             @QueryParam("batchNo") String batchNo,
//                             @QueryParam("customerId") String customerId,
//                             @QueryParam("orderId") String orderId,
//                             @QueryParam("bankCode") String bankCode,
//                             @QueryParam("bankCardNo") String bankCardNo,
//                             @QueryParam("bankName") String bankName,
//                             @QueryParam("userName") String userName,
//                             @QueryParam("amount") String amount
//    ) {
//
//        Map<String, String> request = new HashMap<>();
//        request.put("command", cmd);
//        request.put("batchNo", batchNo);
//        request.put("customerId", customerId);
//        request.put("orderId", orderId);
//        request.put("bankCode", bankCode);
//        request.put("bankCardNo", bankCardNo);
//        request.put("bankName", bankName);
//        request.put("userName", userName);
//        request.put("amount", amount);
//
//        String result = withdrawService.handleWithdrawRequest(request);
//        return Response.ok().entity(result).build();
//    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdraw(Map request) {

        String result = withdrawService.handleWithdrawRequest(request);
        return Response.ok().entity(result).build();
    }

}
