package com.thoughtworks.mockpayment.resource.resources;

import com.google.gson.Gson;
import com.thoughtworks.mockpayment.entity.bankCardAuth.AuthStatusCode;
import com.thoughtworks.mockpayment.resource.util.ResourceTest;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(ConcurrentRunner.class)
public class BankCardAuthResourceTest extends ResourceTest {

    protected WebTarget authTarget;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target()
            .path("/bank-card-auth")
            .queryParam("cmd", "mockAuthentication")
            .queryParam("customerId", "809080908090")
            .queryParam("name", "lulaijin")
            .queryParam("idCardNo","362329199103120018")
            .queryParam("bankCode", "CCB")
            .queryParam("bankName", "")
            .queryParam("orderId", "test")
            .queryParam("expandInfo", "test");
    }

    @After
    public void tearDown() throws IOException {
        authTarget = null;
    }

    @Test
    public void should_client_response_success_when_bankCardNo_be_1() {
        String respondContent = authTarget
            .queryParam("bankCardNo", "111111111111111")
            .request("text/json")
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(AuthStatusCode.SUCCESS.getStatus()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_2() {
        String respondContent = authTarget
            .queryParam("bankCardNo", "222222222222222")
            .request("text/json")
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(AuthStatusCode.BANK_CARD_NO_ILLEGAL.getStatus()));
        assertThat(respondMap.get("authMsg"), is(AuthStatusCode.BANK_CARD_NO_ILLEGAL.getDescription()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_11() {
        String respondContent = authTarget
            .queryParam("bankCardNo", "111111111111110")
            .request("text/json")
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(AuthStatusCode.BANK_CARD_NO_NOT_MATCH_NAME.getStatus()));
        assertThat(respondMap.get("authMsg"), is(AuthStatusCode.BANK_CARD_NO_NOT_MATCH_NAME.getDescription()));
    }
}
