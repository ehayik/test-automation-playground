package com.github.eljaiek.playground.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

@Slf4j
public class TestCase {

    @Test
    public void test() {
        log.info("Running test case");
        Assertions.assertThat(true).isTrue();
    }
}
