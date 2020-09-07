package com.teksenz.reactstore.lib;

import com.google.common.collect.Iterables;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Configuration {
    private static final Logger log = LoggerFactory.getLogger(TestBaseOld.class);
    public static <T extends MutableCapabilities> T getCapabilities(String capabilitiesFile, Class<T> optionType) {
        Properties properties = new Properties();
        try {
            properties.load(Configuration.class.getResourceAsStream(capabilitiesFile));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Capabilities properties file could not be read - "+ capabilitiesFile);
        }
        T options = null;
        String optionsClass = Iterables.getLast(Arrays.asList(optionType.getName().split("[.]")));
        switch (optionsClass){
            case "ChromeOptions" :
                options = optionType.cast(new ChromeOptions());
                break;
            case "FirefoxOptions" :
                options = optionType.cast(new FirefoxOptions());
                break;
            case "SafariOptions":
                options = optionType.cast(new SafariOptions());
                break;
            case "EdgeOptions":
                options = optionType.cast(new EdgeOptions());
                break;
        }

        for (String name : properties.stringPropertyNames()) {
            String value = properties.getProperty(name);
            if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
                options.setCapability(name, Boolean.valueOf(value));
            } else if (value.startsWith("file:")) {
                try {
                    options.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                options.setCapability(name, value);
            }
        }
        return optionType.cast(options);
    }
    public static String getProperty(String key){
        Properties properties = new Properties();
        try {
            properties.load(Configuration.class.getResourceAsStream(System.getProperty("application.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static Capabilities getRemoteCapabilities(String capFile) {
        return new DesiredCapabilities();
    }
}
