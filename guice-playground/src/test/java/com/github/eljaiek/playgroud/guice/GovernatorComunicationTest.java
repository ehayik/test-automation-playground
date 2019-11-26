package com.github.eljaiek.playgroud.guice;

import com.google.inject.Injector;
import com.netflix.governator.guice.LifecycleInjector;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GovernatorComunicationTest {

    private Injector injector;



    @BeforeMethod
    public void setUp() {
        injector = LifecycleInjector.builder()
                .withModules(new CommModule())
                .build()
                .createInjector();
    }

    @Test
    public void testSendMessage() {
        val comms = injector.getInstance(Communication.class);
        Assertions.assertThat(comms.sendMessage("Hello World!")).isTrue();
    }
}
