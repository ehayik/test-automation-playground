package com.github.eljaiek.playground.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import lombok.SneakyThrows;
import lombok.val;

import javax.inject.Named;
import java.util.Properties;

public class CommModule extends AbstractModule {

    @Override
    @SneakyThrows
    protected void configure() {
        bind(Communicator.class).to(DefaultCommunicatorImpl.class).in(Scopes.SINGLETON);
        val props = new Properties();
        props.load(CommModule.class.getResourceAsStream("/application.properties"));
        Names.bindProperties(binder(), props);

        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(LogMessage.class),
                new MessageLoggerInterceptor()
        );
    }

    @Provides
    public Communication communication(@Named("communication.keep-records") boolean keepRecords,
                                       Communicator communicator) {
        return new Communication(keepRecords, communicator);
    }
}
