package com.thoughtworks.mockpayment.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("payment")
public class PaymentResource {

    @POST
    @Path("pay")
    public String pay() {
        return "paying";
    }

    @GET
    @Path("query")
    public String query() {
        return "quering";
    }
}
