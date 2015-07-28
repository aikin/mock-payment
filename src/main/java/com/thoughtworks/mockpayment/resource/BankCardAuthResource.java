package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.service.BankCardAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("mockpayment/bank-card-auth")
public class BankCardAuthResource {

    private static final Logger logger = LoggerFactory.getLogger(BankCardAuthResource.class);

    @Inject
    private BankCardAuthService bankCardAuthService;

    @GET
    public Response authenticate(@QueryParam("cmd") String cmd,
                                 @QueryParam("customerId") String customerId,
                                 @QueryParam("orderId") String orderId,
                                 @QueryParam("name") String name,
                                 @QueryParam("idCardNo") String idCardNo,
                                 @QueryParam("bankCode") String bankCode,
                                 @QueryParam("bankCardNo") String bankCardNo,
                                 @QueryParam("bankName") String bankName,
                                 @QueryParam("expandInfo") String expandInfo
    ) {

        logger.debug("*** The bank code auth GET request ***");

        Map<String, String> request = new HashMap<>();
        request.put("cmd", cmd);
        request.put("customerId", customerId);
        request.put("orderId", orderId);
        request.put("name", name);
        request.put("idCardNo", idCardNo);
        request.put("bankCode", bankCode);
        request.put("bankCardNo", bankCardNo);
        request.put("bankName", bankName);
        request.put("expandInfo", expandInfo);

        String result = bankCardAuthService.handleBankCardAuthRequest(request);
        return Response.ok().entity(result).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(Map request) {
        logger.debug("*** The bank code auth POST request ***" + request);

        String result = bankCardAuthService.handleBankCardAuthRequest(request);
        return Response.ok().entity(result).build();
    }
}
