package com.thoughtworks.mockpayment.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thoughtworks.mockpayment.persistence.PersistenceModule;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import javax.inject.Inject;

import static com.thoughtworks.mockpayment.util.DatabaseHelper.cleanDatabase;

public class MockPaymentResourceRunner extends BlockJUnit4ClassRunner {
    private Injector injector;

    @Inject
    private SqlSessionManager sqlSessionManager;

    public MockPaymentResourceRunner(final Class<?> clazz) throws InitializationError {
        super(clazz);
        this.injector = Guice.createInjector(
                new PersistenceModule("development")
        );
        injector.injectMembers(this);
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        final Statement beforeStatement = super.withBefores(method, target, statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                cleanDatabase(sqlSessionManager);
                beforeStatement.evaluate();
            }
        };
    }

    @Override
    protected Statement withAfters(FrameworkMethod method, Object target, Statement statement) {
        final Statement afterStatement = super.withAfters(method, target, statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                afterStatement.evaluate();
//                cleanDatabase(sqlSessionManager);
            }
        };
    }

    @Override
    protected Object createTest() throws Exception {
        Object testClass = super.createTest();
        injector.injectMembers(testClass);
        return testClass;
    }
}
