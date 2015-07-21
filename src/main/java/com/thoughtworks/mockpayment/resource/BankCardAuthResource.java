package com.thoughtworks.mockpayment.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thoughtworks.mockpayment.service.BankCardAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("bank-card-auth")
public class BankCardAuthResource {

    private static final Logger logger = LoggerFactory.getLogger(BankCardAuthResource.class);

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
                                 ) throws JsonProcessingException {

        logger.info("*** The bank code auth GET request ***");

        MultivaluedMap<String, String> request = new MultivaluedHashMap<>();
        request.add("cmd", cmd);
        request.add("customerId", customerId);
        request.add("orderId", orderId);
        request.add("name", name);
        request.add("idCardNo", idCardNo);
        request.add("bankCode", bankCode);
        request.add("bankCardNo", bankCardNo);
        request.add("bankName", bankName);
        request.add("expandInfo", expandInfo);

        return new BankCardAuthService().handleBankCardAuthRequest(request);
    }

    @POST
    public Response authenticate(MultivaluedMap request) throws JsonProcessingException {
        logger.info("*** The bank code auth POST request ***" + request);

        return new BankCardAuthService().handleBankCardAuthRequest(request);
    }
}
