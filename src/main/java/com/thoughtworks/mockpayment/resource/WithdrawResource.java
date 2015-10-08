package com.thoughtworks.mockpayment.resource;


import com.thoughtworks.mockpayment.service.WithdrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/")
public class WithdrawResource {

    private static final Logger logger = LoggerFactory.getLogger(DepositResource.class);

    @Inject
    private WithdrawService withdrawService;

    @POST
    @Path("/withdraw")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdraw(Map request) {
        String result = withdrawService.handleWithdrawRequest(request);
        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/withdraw-query")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdrawQuery(Map request) {

        String result = withdrawService.handleWithdrawQueryRequest(request);
        return  Response.ok().entity(result).build();
    }

}
