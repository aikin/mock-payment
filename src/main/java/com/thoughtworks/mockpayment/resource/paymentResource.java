package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.service.DefaultPaymentService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("payment")
public class PaymentResource {


    @GET
    @Path("deposits")
    public Response pay(@QueryParam("cmd") String cmd,
                        @QueryParam("customerId") String customerId,
                        @QueryParam("orderId") String orderId,
                        @QueryParam("userName") String userName,
                        @QueryParam("idCardNo") String idCardNo,
                        @QueryParam("bankCode") String bankCode,
                        @QueryParam("bankCardNo") String bankCodeNo,
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
        request.put("bankCodeNo", bankCodeNo);
        request.put("bankName", bankName);
        request.put("amount", amount);
        request.put("currency", expandInfo);

        return new DefaultPaymentService().handleDepositsRequest(request);
    }


    @POST
    @Path("deposits")
    public Response pay(Map request) {
        return Response.ok().entity("ok").build();
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
