package com.github.eljaiek.playground.guice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@RequiredArgsConstructor
public class Communication {

    private final boolean keepRecords;
    private final Communicator communicator;

    @PostConstruct
    public void init() {

        if (keepRecords) {
            log.info("Message logging enabled");
        }

    }

    @PreDestroy
    public void close() {
        log.info("Communication is closed");
    }

    public boolean sendMessage(String message) {
        return communicator.sendMessage(message);
    }

}