package com.thoughtworks.mock.authentication.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("helloworld")
public class AuthenticationResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationResource.class);

    @GET
    public String helloworld(){
        return "helloworld";
    }

}
