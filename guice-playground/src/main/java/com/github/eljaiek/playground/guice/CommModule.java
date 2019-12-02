package com.github.eljaiek.playground.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;

class CommModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(Communicator.class).to(DefaultCommunicatorImpl.class).in(Scopes.SINGLETON);
        bind(Communication.class).toInstance(new Communication(true));

        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(LogMessage.class),
                new MessageLoggerInterceptor()
        );
    }
}
