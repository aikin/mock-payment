package com.thoughtworks.mockpayment.resource;

import com.google.inject.Inject;
import com.thoughtworks.mockpayment.entity.withdraw.BankCardNoAndQueryResponseCodeMap;
import com.thoughtworks.mockpayment.entity.withdraw.BankCardNoAndWithdrawResponseCodeMap;
import com.thoughtworks.mockpayment.entity.withdraw.QueryResponseCode;
import com.thoughtworks.mockpayment.entity.withdraw.WithdrawResponseCode;
import com.thoughtworks.mockpayment.persistence.mapper.WithdrawOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.WithdrawOrder;
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

    @Inject
    private WithdrawOrderMapper withdrawOrderMapper;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target().path("/mockpayment/withdraw");
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

        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowId((String)respondMap.get("withdrawFlowId"));

        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.SUCCESS.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.SUCCESS.getCode()));
        assertThat(withdrawOrder.getQueryResponseCode(), is(QueryResponseCode.SUCCESS.getCode()));
        assertThat(withdrawOrder.getQueryMessage(), is(QueryResponseCode.SUCCESS.getDescription()));
    }

    @Test
    public void should_client_response_repeat_when_bankCardNo_be_match_WITHDRAW_ORDER_ID_REPEAT() {

        requestData.put("bankCardNo", BankCardNoAndWithdrawResponseCodeMap.WITHDRAW_ORDER_ID_REPEAT.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowId((String) respondMap.get("withdrawFlowId"));

        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.WITHDRAW_ORDER_ID_REPEAT.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.WITHDRAW_ORDER_ID_REPEAT.getCode()));
        assertThat(withdrawOrder.getQueryResponseCode(), is(QueryResponseCode.SUCCESS.getCode()));
        assertThat(withdrawOrder.getQueryMessage(), is(QueryResponseCode.SUCCESS.getDescription()));
    }

    @Test
    public void should_client_response_pending_when_bankCardNo_be_match_WITHDRAW_PENDING() {

        requestData.put("bankCardNo", BankCardNoAndWithdrawResponseCodeMap.WITHDRAW_PENDING.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowId((String) respondMap.get("withdrawFlowId"));

        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.WITHDRAW_PENDING.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.WITHDRAW_PENDING.getCode()));
        assertThat(withdrawOrder.getQueryResponseCode(), is(QueryResponseCode.FAILURE_WITHDRAW.getCode()));
        assertThat(withdrawOrder.getQueryMessage(), is(QueryResponseCode.FAILURE_WITHDRAW.getDescription()));
    }

    @Test
    public void should_client_response_success_when_bankCardNo_be_match_QUERY_SUCCESS() {

        requestData.put("bankCardNo", BankCardNoAndQueryResponseCodeMap.QUERY_SUCCESS.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowId((String) respondMap.get("withdrawFlowId"));

        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.SUCCESS.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.SUCCESS.getCode()));
        assertThat(withdrawOrder.getQueryResponseCode(), is(QueryResponseCode.SUCCESS.getCode()));
        assertThat(withdrawOrder.getQueryMessage(), is(QueryResponseCode.SUCCESS.getDescription()));
    }

    @Test
    public void should_client_response_success_when_bankCardNo_be_match_QUERY_FAILURE_BANK_REJECT() {

        requestData.put("bankCardNo", BankCardNoAndQueryResponseCodeMap.QUERY_FAILURE_BANK_REJECT.getBankCardNo());
        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(requestData), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);
        WithdrawOrder withdrawOrder = withdrawOrderMapper.findOrderByFlowId((String) respondMap.get("withdrawFlowId"));

        assertThat(respondMap.get("withdrawMessage"), is(WithdrawResponseCode.SUCCESS.getDescription()));
        assertThat(respondMap.get("responseCode"), is(WithdrawResponseCode.SUCCESS.getCode()));
        assertThat(withdrawOrder.getQueryResponseCode(), is(QueryResponseCode.QUERY_FAILURE_BANK_REJECT.getCode()));
        assertThat(withdrawOrder.getQueryMessage(), is(QueryResponseCode.QUERY_FAILURE_BANK_REJECT.getDescription()));
    }

}
