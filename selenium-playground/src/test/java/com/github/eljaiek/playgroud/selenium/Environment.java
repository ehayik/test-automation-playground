package com.github.eljaiek.playgroud.selenium;

import com.netflix.governator.annotations.Configuration;
import lombok.Getter;

public class Environment {

    @Getter
    @Configuration("app.url")
    private String googleUrl;

    @Getter
    @Configuration("app.youtube.url")
    private String youtubeUrl;
}
