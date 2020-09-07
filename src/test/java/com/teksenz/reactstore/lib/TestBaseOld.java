package com.teksenz.reactstore.lib;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static com.teksenz.reactstore.lib.Configuration.getProperty;

public class TestBaseOld {
    private static final Logger log = LoggerFactory.getLogger(TestBaseOld.class);
    protected String browser;
    protected RemoteWebDriver driver;
    protected String baseUrl;

    @BeforeTest
    public void beforeTest(){
        baseUrl = System.getProperty("site.url");
    }

    @Parameters({"browser", "capabilities"})
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browser,
                             @Optional("/capabilities-local/capabilities-chrome.properties") String capFile) throws MalformedURLException {

        log.info("Browser : "+ browser);
        this.browser = browser.toLowerCase();
        log.info("Capability file : " + capFile);
        String gridUrl = System.getProperty("grid-url","noGrid");
        log.info("Grid : "+ gridUrl);
        if(gridUrl.equalsIgnoreCase("noGrid")) {
            switch (this.browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = Configuration.getCapabilities(capFile, ChromeOptions.class);
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = Configuration.getCapabilities(capFile, FirefoxOptions.class);
                    driver = new FirefoxDriver(firefoxOptions);
//                driver = new RemoteWebDriver(new URL(""),firefoxOptions);
                    break;
                case "safari":
                    SafariOptions safariOptions = Configuration.getCapabilities(capFile, SafariOptions.class);
                    driver = new SafariDriver(safariOptions);
                    break;

            }
        }else {
            driver = new RemoteWebDriver(new URL(gridUrl),Configuration.getRemoteCapabilities(capFile));
        }
        baseUrl = getProperty("site.url");
        log.info("baseUrl : "+ baseUrl);
        driver.get(baseUrl);

    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
