package com.github.eljaiek.playgroud.guice;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import javax.inject.Inject;

@Governator
//@Listeners(GovernatorListener.class)
public class GovernatorComunicationTest {

    @Inject
    private Communication comms;

    @Test
    public void testSendMessage() {
        Assertions.assertThat(comms.sendMessage("Hello World!")).isTrue();
    }
}
