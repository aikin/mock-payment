package com.thoughtworks.mockpayment.resource;

import com.google.gson.Gson;
import com.thoughtworks.mockpayment.entity.payment.BankCardNoAndResponseCodeMapper;
import com.thoughtworks.mockpayment.entity.payment.DepositsResponseCode;
import com.thoughtworks.mockpayment.util.MockPaymentResourceRunner;
import com.thoughtworks.mockpayment.util.ResourceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.WebTarget;
import java.util.HashMap;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockPaymentResourceRunner.class)
public class PaymentResourceTest extends ResourceTest {

    protected WebTarget authTarget;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target()
            .path("/payment/deposits")
            .queryParam("cmd", "deposits")
            .queryParam("customerId", "809080908090")
            .queryParam("userName", "aikin")
            .queryParam("idCardNo","362329199103120018")
            .queryParam("bankCode", "CCB")
            .queryParam("bankName", "www")
            .queryParam("amount", "100")
            .queryParam("currency", "CNY")
            .queryParam("orderId", UUID.randomUUID().toString())
            .queryParam("expandInfo", "testExpandInfo");
    }

    @After
    @Override
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
        assertThat(respondMap.get("depositsMessage"), is(DepositsResponseCode.SUCCESS.getDescription()));
        assertThat(respondMap.get("responseCode"), is(DepositsResponseCode.SUCCESS.getCode()));
    }


    @Test
    public void should_client_response_failure_when_bankCardNo_be_match_SHORT_BALANCE() {

        String respondContent = authTarget
            .queryParam("bankCardNo", BankCardNoAndResponseCodeMapper.SHORT_BALANCE.getBankCardNo())
            .request()
            .get(String.class);
        Gson gson = new Gson();
        HashMap respondMap = gson.fromJson(respondContent, HashMap.class);
        assertThat(respondMap.get("depositsMessage"), is(DepositsResponseCode.SHORT_BALANCE.getDescription()));
        assertThat(respondMap.get("responseCode"), is(DepositsResponseCode.SHORT_BALANCE.getCode()));
    }
}
