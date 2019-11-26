package com.github.eljaiek.playgroud.guice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

@Slf4j
@RequiredArgsConstructor
public class Communication {

    private final boolean keepRecords;

    @Inject
    private Communicator communicator;

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