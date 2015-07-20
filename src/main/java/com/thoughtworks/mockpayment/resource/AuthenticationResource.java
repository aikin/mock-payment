package com.thoughtworks.mockpayment.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("authenticate")
public class AuthenticationResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationResource.class);

    @GET
    public String helloworld(@QueryParam("user") String user) {
        logger.info("in hell world" + user);
        return "helloworld";
    }
}
