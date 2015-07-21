package com.thoughtworks.mockpayment.app;

import com.google.inject.servlet.GuiceFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.FilterRegistration;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.net.URI;
import java.util.EnumSet;

public class Server {
    private static URI BASE_URI = URI.create("http://0.0.0.0:8090/");
    private static final Logger logger = LoggerFactory.getLogger(Server.class);


    public static void main(String[] args) {

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, false);
        final WebappContext context = new WebappContext("authenticatin mock webapp", "/mockpayment");

        ServletRegistration servletRegistration = context.addServlet("ServletContainer", ServletContainer.class);

        servletRegistration.addMapping("/*");
        servletRegistration.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        servletRegistration.setInitParameter("javax.ws.rs.Application", "com.thoughtworks.mockpayment.app.MockPaymentResourceConfig");
        servletRegistration.setInitParameter("jersey.config.server.provider.scanning.recursive", "true");

        FilterRegistration registration = context.addFilter("GuiceFilter", GuiceFilter.class);
        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), "/*");

        context.deploy(server);

        System.out.println(String.format("Application started.\nTry out %s\nStop the application using CTRL+C",
                BASE_URI));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.shutdownNow();
            }
        }));

        try {
            server.start();
            Thread.currentThread().join();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
