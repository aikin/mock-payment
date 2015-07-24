package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("payment")
public class PaymentResource {

    private static final Logger logger = LoggerFactory.getLogger(PaymentResource.class);

    @Inject
    private PaymentService paymentService;

    @POST
    @Path("deposits")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposits(Map request) {

        String result = paymentService.handleDepositsRequest((Map<String, String>)request);
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
