package com.github.eljaiek.playgroud.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import org.slf4j.LoggerFactory;
import ru.vyarus.guice.ext.ExtAnnotationsModule;

class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ExtAnnotationsModule());

        bind(Communicator.class).to(DefaultCommunicatorImpl.class).in(Scopes.SINGLETON);
        bind(Communication.class).toInstance(new Communication(true));

        bindInterceptor(
                Matchers.any(),
                Matchers.annotatedWith(MessageSentLoggable.class),
                new MessageLogger(LoggerFactory.getLogger(MessageLogger.class))
        );
    }
}
