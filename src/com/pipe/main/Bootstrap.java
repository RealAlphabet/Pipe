package com.pipe.main;

import java.security.Security;

public class Bootstrap {

    public static void main(String[] args) {
        Security.setProperty("networkaddress.cache.ttl", "30");
        Security.setProperty("networkaddress.cache.negative.ttl", "10");

        // Prefer IPV4
        System.setProperty("io.netty.selectorAutoRebuildThreshold", "0");
        System.setProperty("java.net.preferIPv4Stack", "true");

        // Start server
        new Server().start();
    }
}
