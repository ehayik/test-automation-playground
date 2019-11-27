package com.github.eljaiek.playgroud.guice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class DefaultCommunicatorImpl implements Communicator {
    public DefaultCommunicatorImpl() {
        log.info("CREATED");
    }

    @Override
    @LogMessage
    public boolean sendMessage(String message) {
        log.info("SMS message sent");
        return true;
    }
}
