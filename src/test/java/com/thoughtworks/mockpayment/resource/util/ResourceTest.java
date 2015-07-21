package com.thoughtworks.mockpayment.resource.util;

import com.google.inject.Injector;
import com.thoughtworks.mockpayment.app.MockPaymentResourceConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

import javax.inject.Inject;
import javax.ws.rs.core.Application;

public class ResourceTest extends JerseyTest {


    @Inject
    protected Injector injector;

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        return ResourceConfig.forApplicationClass(MockPaymentResourceConfig.class).register(new AbstractBinder() {
            @Override
            protected void configure() {

            }
        });
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

    }

}
