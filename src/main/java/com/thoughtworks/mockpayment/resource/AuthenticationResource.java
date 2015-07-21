package com.thoughtworks.mockpayment.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.mockpayment.entity.AuthStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("authenticate")
public class AuthenticationResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationResource.class);

    @GET
    public Response authenticate(@QueryParam("cmd") String cmd,
                                 @QueryParam("customerId") String customerId,
                                 @QueryParam("name") String name,
                                 @QueryParam("idCardNo") String idCardNo,
                                 @QueryParam("bankCode") String bankCode,
                                 @QueryParam("bankCardNo") String bankCodeNo,
                                 @QueryParam("busId") String busId,
                                 @QueryParam("resDesc") String resDesc
                                 ) throws JsonProcessingException {

        logger.info("*** in authenticate bank code ***");

        // TODO: need extract, api level doesn't nedd know how to combination response
        Map<String, Object> map = new HashMap<>();
        AuthStatusCode authStatusCode  = AuthStatusCode.codeOf(bankCodeNo);
        map.put("command", cmd);
        map.put("customerId", customerId);
        map.put("busId", busId);
        map.put("resDesc", resDesc);
        map.put("flowId", "");

        if (authStatusCode == null) {
            logger.error("*** input bankCardNo not match status ***");
            authStatusCode = AuthStatusCode.BANK_CARD_NO_ILLEGAL;
        }
        map.put("authStatus", authStatusCode.getStatus());
        map.put("responseCode", authStatusCode.getCode());
        map.put("authMsg", authStatusCode.getDescription());
        ObjectMapper mapper = new ObjectMapper();
        String result =  mapper.writeValueAsString(map);
        return Response.ok().entity(result).build();
    }
}
