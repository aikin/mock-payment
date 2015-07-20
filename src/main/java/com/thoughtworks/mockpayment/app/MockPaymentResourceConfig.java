package com.thoughtworks.mockpayment.app;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;

import javax.inject.Inject;

public class MockPaymentResourceConfig extends ResourceConfig {
    @Inject
    public MockPaymentResourceConfig(ServiceLocator serviceLocator) throws Exception {

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        property(org.glassfish.jersey.server.ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        packages("com.thoughtworks.mockpayment");
    }

}