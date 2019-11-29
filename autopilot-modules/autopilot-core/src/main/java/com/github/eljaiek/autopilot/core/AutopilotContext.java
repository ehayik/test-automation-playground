package com.github.eljaiek.autopilot.core;

public interface AutopilotContext {

    void start();

    void close();

    <S> S getInstance(Class<S> type);
}
