package org.acme.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "config")
public interface Config {
    String location();
    String userLocation();
}
