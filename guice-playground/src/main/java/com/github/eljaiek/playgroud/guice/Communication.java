package com.github.eljaiek.playgroud.guice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
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

    public boolean sendMessage(String message) {
        return communicator.sendMessage(message);
    }

}