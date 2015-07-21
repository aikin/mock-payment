package com.thoughtworks.mockpayment.app;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;

public class MockPaymentResourceConfig extends ResourceConfig {
    @Inject
    public MockPaymentResourceConfig(ServiceLocator serviceLocator) throws Exception {

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
//        Injector injector = Guice.createInjector(
//            new PersistenceModule(System.getProperty("env", "withdraw")),
//            new AbstractModule() {
//                @Override
//                protected void configure() {
//                }
//            }
//        );

        property(org.glassfish.jersey.server.ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
//        guiceBridge.bridgeGuiceInjector(injector);
        packages("com.thoughtworks.mockpayment");

    }

}
