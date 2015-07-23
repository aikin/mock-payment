package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("payment")
public class PaymentResource {

    private static final Logger logger = LoggerFactory.getLogger(PaymentResource.class);

    @Inject
    private PaymentService paymentService;

    @GET
    @Path("deposits")
    public Response deposits(@QueryParam("cmd") String cmd,
                             @QueryParam("customerId") String customerId,
                             @QueryParam("orderId") String orderId,
                             @QueryParam("userName") String userName,
                             @QueryParam("idCardNo") String idCardNo,
                             @QueryParam("bankCode") String bankCode,
                             @QueryParam("bankCardNo") String bankCardNo,
                             @QueryParam("bankName") String bankName,
                             @QueryParam("amount") String amount,
                             @QueryParam("currency") String currency,
                             @QueryParam("expandInfo") String expandInfo
    ) {

        Map<String, String> request = new HashMap<>();
        request.put("command", cmd);
        request.put("customerId", customerId);
        request.put("orderId", orderId);
        request.put("userName", userName);
        request.put("idCardNo", idCardNo);
        request.put("bankCode", bankCode);
        request.put("bankCardNo", bankCardNo);
        request.put("bankName", bankName);
        request.put("amount", amount);
        request.put("currency", currency);
        request.put("expandInfo", expandInfo);

        String result = paymentService.handleDepositsRequest(request);
        return Response.ok().entity(result).build();
    }


    @POST
    @Path("deposits")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deposits(Map request) {

        String result = paymentService.handleDepositsRequest(request);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("query")
    public String query() {
        return "quering";
    }

    @POST
    @Path("query")
    public String query(Map request) {
        return "quering by post";
    }
}
