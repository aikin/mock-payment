package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.entity.withdraw.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResponseCode;
import com.thoughtworks.mockpayment.util.Json;
import com.thoughtworks.mockpayment.util.MockPaymentResourceRunner;
import com.thoughtworks.mockpayment.util.ResourceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockPaymentResourceRunner.class)
public class WithdrawResourceTest extends ResourceTest {


    protected WebTarget authTarget;
    protected Map<String, String> requestData = new HashMap<>();

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target().path("/withdraw");
        requestData.put("cmd", "withdraw");
        requestData.put("customerId", "809080908090");
        requestData.put("orderId", "121121121121");
        requestData.put("bankCode", "CCB");
        requestData.put("bankName", "www");
        requestData.put("userName", "aikin");
        requestData.put("amount", "100");
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        authTarget = null;
        requestData = null;
    }

    @Test
    public void should_client_response_success_when_bankCardNo_be_not_match() {

        final String UN_MATCH_BANK_CARD_NO = "123456789012345";
        requestData.put("bankCardNo", UN_MATCH_BANK_CARD_NO);
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.SUCCESS.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.SUCCESS.getCode()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_match_WITHDRAW_ORDER_ID_REPEATBALANCE() {

        requestData.put("bankCardNo", BankCardNoAndResponseCodeMap.WITHDRAW_ORDER_ID_REPEAT.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.WITHDRAW_ORDER_ID_REPEAT.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.WITHDRAW_ORDER_ID_REPEAT.getCode()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_match_WITHDRAW_PROCESSING() {

        requestData.put("bankCardNo", BankCardNoAndResponseCodeMap.WITHDRAW_PROCESSING.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.WITHDRAW_PROCESSING.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.WITHDRAW_PROCESSING.getCode()));
    }

}
