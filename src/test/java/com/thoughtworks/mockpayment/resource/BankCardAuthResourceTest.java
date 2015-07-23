package com.thoughtworks.mockpayment.resource;

import com.google.gson.Gson;
import com.thoughtworks.mockpayment.entity.bankCardAuth.BankAuthResponseCode;
import com.thoughtworks.mockpayment.entity.bankCardAuth.BankCardNoAndResponseCodeMapper;
import com.thoughtworks.mockpayment.util.ResourceTest;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.WebTarget;
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
    public void tearDown() throws Exception {
        super.tearDown();
        authTarget = null;
    }

    @Test
    public void should_client_response_success_when_bankCardNo_be_not_match() {

        final String UN_MATCH_BANK_CARD_NO = "123456789012345";
        String respondContent = authTarget
            .queryParam("bankCardNo", UN_MATCH_BANK_CARD_NO)
            .request()
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(BankAuthResponseCode.SUCCESS.getStatus()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_BANK_CARD_NO_ILLEGAL() {

        String respondContent = authTarget
            .queryParam("bankCardNo", BankCardNoAndResponseCodeMapper.BANK_CARD_NO_ILLEGAL.getBankCardNo())
            .request()
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(BankAuthResponseCode.BANK_CARD_NO_ILLEGAL.getStatus()));
        assertThat(respondMap.get("authMsg"), is(BankAuthResponseCode.BANK_CARD_NO_ILLEGAL.getDescription()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_BANK_CARD_NO_NOT_MATCH_NAME() {
        String respondContent = authTarget
            .queryParam("bankCardNo", BankCardNoAndResponseCodeMapper.BANK_CARD_NO_NOT_MATCH_NAME.getBankCardNo())
            .request()
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("authStatus"), is(BankAuthResponseCode.BANK_CARD_NO_NOT_MATCH_NAME.getStatus()));
        assertThat(respondMap.get("authMsg"), is(BankAuthResponseCode.BANK_CARD_NO_NOT_MATCH_NAME.getDescription()));
    }
}
