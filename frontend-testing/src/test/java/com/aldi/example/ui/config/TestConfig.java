package com.aldi.example.ui.config;

import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    private static final String ENV = System.getProperty("env", "dev");
    private static final Properties properties = new Properties();

    static {
        try {
            String configFile = ".env";
            properties.load(TestConfig.class.getClassLoader().getResourceAsStream(configFile));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment data", e);
        }
    }

    public static String getBaseUrl() {
        return switch (ENV) {
            case "test" -> properties.getProperty("BASE_URL_TEST");
            case "staging" -> properties.getProperty("BASE_URL_STAGING");
            default -> properties.getProperty("BASE_URL_DEV");
        };
    }

    public static String getUsername() {
        return properties.getProperty("USERNAME");
    }

    public static String getPassword() {
        return properties.getProperty("PASSWORD");
    }
}