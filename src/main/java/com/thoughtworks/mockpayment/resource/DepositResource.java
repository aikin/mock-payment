package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("mockpayment")
public class DepositResource {

    private static final Logger logger = LoggerFactory.getLogger(DepositResource.class);

    @Inject
    private DepositService depositService;

    @POST
    @Path("deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(Map request) {
        String result = depositService.handleDepositRequest((Map<String, String>) request);
        return Response.ok().entity(result).build();
    }


    @POST
    @Path("deposit-query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(Map request) {
        String result = depositService.handleDepositQueryRequest((Map<String, String>) request);
        return Response.ok().entity(result).build();
    }
}
