package com.github.eljaiek.playgroud.guice;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import ru.vyarus.guice.ext.log.Log;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@RequiredArgsConstructor
public class Communication {

    private final boolean keepRecords;

    @Inject
    private Communicator communicator;

    @Log
    private Logger log;

    @PostConstruct
    public void init() {
    //    log.info("Message logging enabled");
    }
  
    public boolean sendMessage(String message) {
        return communicator.sendMessage(message);
    }
 
}