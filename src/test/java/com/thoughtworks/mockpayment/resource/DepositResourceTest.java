package com.thoughtworks.mockpayment.resource;

import com.thoughtworks.mockpayment.entity.deposit.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.deposit.DepositResponseCode;
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
public class DepositResourceTest extends ResourceTest {

    protected WebTarget authTarget;
    protected Map<String, String> requestData = new HashMap<>();

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target().path("/mockpayment/deposit");
        requestData.put("customerId", "809080908090");
        requestData.put("name", "aikin");
        requestData.put("pin", "362329199103120018");
        requestData.put("bankCode", "CCB");
        requestData.put("bankName", "www");
        requestData.put("amount", "100");
        requestData.put("orderId", "121121121121");
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
        requestData.put("cardNo", UN_MATCH_BANK_CARD_NO);
        Response response = authTarget
            .request()
            .post(Entity.entity(requestData, MediaType.APPLICATION_JSON));

//        Gson gson = new Gson();
//        HashMap respondMap = gson.fromJson(response.readEntity(String.class), HashMap.class);
        HashMap respondMap = response.readEntity(HashMap.class);
        assertThat(respondMap.get("message"), is(DepositResponseCode.SUCCESS.getDescription()));
        assertThat(respondMap.get("responseCode"), is(DepositResponseCode.SUCCESS.getCode()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_match_SHORT_BALANCE() {

        requestData.put("cardNo", BankCardNoAndResponseCodeMap.SHORT_BALANCE.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(requestData, MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        assertThat(respondMap.get("message"), is(DepositResponseCode.SHORT_BALANCE.getDescription()));
        assertThat(respondMap.get("responseCode"), is(DepositResponseCode.SHORT_BALANCE.getCode()));
    }

    @Test
    public void should_client_response_failure_when_bankCardNo_be_match_DEPOSIT_PENDING() {

        requestData.put("cardNo", BankCardNoAndResponseCodeMap.DEPOSIT_PENDING.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(requestData, MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        assertThat(respondMap.get("message"), is(DepositResponseCode.DEPOSIT_PENDING.getDescription()));
        assertThat(respondMap.get("responseCode"), is(DepositResponseCode.DEPOSIT_PENDING.getCode()));
    }
}
