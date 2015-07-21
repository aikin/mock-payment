package com.thoughtworks.mockpayment.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("payment")
public class PaymentResource {

    @POST
    @Path("pay")
    public Response pay(MultivaluedMap request) {

        return Response.ok().entity("ok").build();
    }

    @GET
    @Path("pay")
    public Response pay(@QueryParam("cmd") String cmd,
                        @QueryParam("customerId") String customerId,
                        @QueryParam("orderId") String orderId,
                        @QueryParam("userName") String userName,
                        @QueryParam("idCardNo") String idCardNo,
                        @QueryParam("bankCode") String bankCode,
                        @QueryParam("bankCardNo") String bankCodeNo,
                        @QueryParam("bankName") String bankName,
                        @QueryParam("province") String province,
                        @QueryParam("city") String city,
                        @QueryParam("amount") double amount,
                        @QueryParam("privateArea") String privateArea,
                        @QueryParam("currency") String currency,
                        @QueryParam("notifyUrl") String notifyUrl
                        ) {
        return Response.ok().entity("ok").build();
    }

    @GET
    @Path("query")
    public String query() {
        return "quering";
    }
}
