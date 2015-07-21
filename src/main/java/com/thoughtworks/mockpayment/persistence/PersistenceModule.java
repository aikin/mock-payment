package com.thoughtworks.mockpayment.persistence;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.*;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;
import org.mybatis.guice.mappers.MapperProvider;
import org.mybatis.guice.session.SqlSessionManagerProvider;
import org.mybatis.guice.transactional.Transactional;
import org.mybatis.guice.transactional.TransactionalMethodInterceptor;

import java.io.Reader;
import java.util.Collection;
import java.util.Properties;

import static com.google.inject.matcher.Matchers.*;
import static com.google.inject.name.Names.named;
import static com.google.inject.util.Providers.guicify;
import static org.apache.ibatis.io.Resources.getResourceAsReader;

public class PersistenceModule extends AbstractModule {
    private static final Logger LOGGER = Logger.getLogger(PersistenceModule.class);
    private static final String DEFAULT_CONFIG_RESOURCE = "withdraw-mybatis/mybatis-config.xml";

    private final String classPathResource;
    private final String environmentId;
    private final Properties properties;

    private ClassLoader resourcesClassLoader = getDefaultClassLoader();
    private ClassLoader driverClassLoader = getDefaultClassLoader();

    public PersistenceModule(String environment) {
        this(environment, DEFAULT_CONFIG_RESOURCE, new Properties());
    }

    public PersistenceModule(String environment, String classPathResource, Properties properties) {
        this.environmentId = environment;
        this.classPathResource = classPathResource;
        this.properties = properties;
    }

    @Override
    protected void configure() {
        try {
            bindSqlManager();
            bindTransactionalInterceptor();
            bindSqlSessionFactory();
            bind(ClassLoader.class)
                    .annotatedWith(named("JDBC.driverClassLoader"))
                    .toInstance(driverClassLoader);
        } finally {
            resourcesClassLoader = getDefaultClassLoader();
            driverClassLoader = getDefaultClassLoader();
        }
    }

    private void bindSqlManager() {
        bind(SqlSessionManager.class).toProvider(SqlSessionManagerProvider.class).in(Scopes.SINGLETON);
        bind(SqlSession.class).to(SqlSessionManager.class).in(Scopes.SINGLETON);
    }

    private void bindSqlSessionFactory() {
        try (Reader reader = getResourceAsReader(getResourceClassLoader(), classPathResource)) {
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader,
                    environmentId,
                    properties);
            bind(SqlSessionFactory.class).toInstance(sessionFactory);

            Configuration configuration = sessionFactory.getConfiguration();

            bindObjectFactory(configuration);
            bindMappers(configuration);
            bindTypeHandlers(configuration);
            bindInterceptors(configuration);
        } catch (Exception e) {
            addError("Impossible to read classpath resource '%s', see nested exceptions: %s",
                    classPathResource,
                    e.getMessage());

            LOGGER.error("Error when bind sqlSession Factory", e);
        }
    }

    private void bindTransactionalInterceptor() {
        TransactionalMethodInterceptor interceptor = new TransactionalMethodInterceptor();
        requestInjection(interceptor);
        bindInterceptor(any(), annotatedWith(Transactional.class), interceptor);
        bindInterceptor(annotatedWith(Transactional.class), not(annotatedWith(Transactional.class)), interceptor);
    }

    private void bindObjectFactory(Configuration configuration) {
        requestInjection(configuration.getObjectFactory());
    }

    private void bindInterceptors(Configuration configuration) {
        Collection<Interceptor> interceptors = configuration.getInterceptors();
        for (Interceptor interceptor : interceptors) {
            requestInjection(interceptor);
        }
    }

    private void bindTypeHandlers(Configuration configuration) {
        Collection<TypeHandler<?>> allTypeHandlers = configuration.getTypeHandlerRegistry().getTypeHandlers();
        for (TypeHandler<?> handler : allTypeHandlers) {
            requestInjection(handler);
        }
    }

    private void bindMappers(Configuration configuration) {
        Collection<Class<?>> mapperClasses = configuration.getMapperRegistry().getMappers();
        for (Class<?> mapperType : mapperClasses) {
            bindMapper(mapperType);
        }
    }


    final <T> void bindMapper(Class<T> mapperType) {
        bind(mapperType).toProvider(guicify(new MapperProvider<>(mapperType))).in(Scopes.SINGLETON);
    }

    protected final ClassLoader getResourceClassLoader() {
        return resourcesClassLoader;
    }

    private ClassLoader getDefaultClassLoader() {
        return getClass().getClassLoader();
    }
}
