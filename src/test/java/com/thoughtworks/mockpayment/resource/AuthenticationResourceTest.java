package com.thoughtworks.mockpayment.resource;

import com.google.gson.Gson;
import com.thoughtworks.mockpayment.entity.AuthStatusCode;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(ConcurrentRunner.class)
public class AuthenticationResourceTest extends JerseyTest {

//    @Override
//    protected Application configure() {
//        enable(TestProperties.LOG_TRAFFIC);
//        enable(TestProperties.DUMP_ENTITY);
//
//        return ResourceConfig.forApplicationClass(MockPaymentResourceConfig.class).register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//
//            }
//        });
//    }

    protected WebTarget authTarget;

    @Override
    protected ResourceConfig configure() {

        enable(TestProperties.LOG_TRAFFIC);
        return new ResourceConfig(AuthenticationResource.class);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target()
            .path("/authenticate")
            .queryParam("cmd", "mockAuthentication")
            .queryParam("name", "lulaijin")
            .queryParam("idCardNo","362329199103120018")
            .queryParam("bankCode", "CCB")
            .queryParam("bankName", "")
            .queryParam("province", "")
            .queryParam("city", "")
            .queryParam("resDesc", "test")
            .queryParam("busId", "test");
    }

    @After
    public void tearDown() throws IOException {
        authTarget = null;
    }

    @Test
    public void should_connection_forbidden() {
        Response response = authTarget
            .queryParam("customerId", "un right customerId")
            .queryParam("bankCardNo", "1")
            .request()
            .get();

        assertThat(response.getStatus(), is(HttpURLConnection.HTTP_FORBIDDEN));
    }

    @Test
    public void should_client_response_success_when_bankCardNo_be_1() {
        String respondContent =  authTarget
            .queryParam("customerId", "809080908090")
            .queryParam("bankCardNo", "1")
            .request("text/json")
            .get(String.class);
        Gson gson = new Gson();
        HashMap<String, String> respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(AuthStatusCode.SUCCESS.getStatus()));
    }
}
