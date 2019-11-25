package com.github.eljaiek.playgroud.guice;

import org.slf4j.Logger;
import ru.vyarus.guice.ext.log.Log;

class DefaultCommunicatorImpl implements Communicator {

    @Log
    private Logger logger;

    @Override
    @MessageSentLoggable
    public boolean sendMessage(String message) {
        logger.info("SMS message sent");
        return true;
    }
}
