package com.thoughtworks.mockpayment.resource;

import com.google.inject.Injector;
import com.thoughtworks.mockpayment.entity.deposit.BankCardNoAndResponseCodeMap;
import com.thoughtworks.mockpayment.entity.deposit.DepositResponseCode;
import com.thoughtworks.mockpayment.persistence.mapper.DepositOrderMapper;
import com.thoughtworks.mockpayment.persistence.model.DepositOrder;
import com.thoughtworks.mockpayment.util.Json;
import com.thoughtworks.mockpayment.util.MockPaymentResourceRunner;
import com.thoughtworks.mockpayment.util.ResourceTest;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockPaymentResourceRunner.class)
public class DepositQueryResourceTest extends ResourceTest {

    protected WebTarget authTarget;
    protected Map<String, Object> baseDepositOrderData = new HashMap<>();

    @Inject
    protected Injector injector;

    @Inject
    private DepositOrderMapper depositOrderMapper;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        authTarget = target().path("/mockpayment/deposit-query");
        baseDepositOrderData.put("customerId", "809080908090");
        baseDepositOrderData.put("name", "aikin");
        baseDepositOrderData.put("pin", "362329199103120018");
        baseDepositOrderData.put("bankCode", "CCB");
        baseDepositOrderData.put("bankName", "www");
        baseDepositOrderData.put("cardNo", "123232");
        baseDepositOrderData.put("amount", "100");
        baseDepositOrderData.put("orderId", "121121121121");
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        authTarget = null;
        baseDepositOrderData = null;
    }

    @Test
    public void should_client_response_success_when_query_deposit() {

        DepositOrder depositOrder = new DepositOrder(baseDepositOrderData);
        depositOrder.setBankCardNo(BankCardNoAndResponseCodeMap.SHORT_BALANCE.getBankCardNo());
        depositOrder.setDepositStatus(DepositResponseCode.SHORT_BALANCE.getStatus());
        depositOrder.setResponseCode(DepositResponseCode.SHORT_BALANCE.getCode());
        depositOrder.setDepositMessage(DepositResponseCode.SHORT_BALANCE.getDescription());
        depositOrder.setDepositAt(DateTime.now().toDate());
        depositOrderMapper.insertNewFullOrder(depositOrder);

        final Map<String, String> request = new HashMap<>();
        request.put("customerId", depositOrder.getCustomerId());
        request.put("flowId", depositOrder.getFlowId());

        Response response = authTarget
            .request()
            .post(Entity.entity(Json.toJSON(request), MediaType.APPLICATION_JSON));

        HashMap respondMap = response.readEntity(HashMap.class);

        assertThat(respondMap.get("message"), is(DepositResponseCode.SHORT_BALANCE.getDescription()));
        assertThat(respondMap.get("responseCode"), is(DepositResponseCode.SHORT_BALANCE.getCode()));
        assertThat(respondMap.get("orderId"), is(depositOrder.getOrderId()));
        assertThat(respondMap.get("customerId"), is(depositOrder.getCustomerId()));
        assertThat(respondMap.get("flowId"), is(depositOrder.getFlowId()));
    }
}
