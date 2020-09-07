package com.teksenz.reactstore.lib;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static com.teksenz.reactstore.lib.Configuration.getProperty;

public class TestBase {
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    protected String browser;
    protected RemoteWebDriver driver;
    DriverService services;
    protected URL gridUrl;
    protected String baseUrl;
    DesiredCapabilities capabilities;
    public static final String DEF_SITE_URL="http://localhost:3000/";



    @Parameters({"browser", "capabilities"})
    @BeforeTest
    public void beforeTest(@Optional("chrome") String browser,
                           @Optional("/capabilities-local/capabilities-chrome.properties") String capFile) throws IOException {
        baseUrl = System.getProperty("site.url",DEF_SITE_URL);
        log.info("baseUrl : " + baseUrl);
        log.info("Browser : "+ browser);
        this.browser = browser.toLowerCase();
        log.info("Capability file : " + capFile);

        capabilities = getCapabilities(capFile);
        String grdUrl = System.getProperty("grid-url","noGrid");
        log.info("Grid Url : "+ grdUrl);
        if(grdUrl.equalsIgnoreCase("noGrid")){
            startDriverService();
            gridUrl = services.getUrl();
            log.info("Browser service URL : "+services.getUrl().toString());
        }else {
            gridUrl = new URL(grdUrl);
        }

    }
    @AfterTest
    public void stopServices() {
        String grdUrl = System.getProperty("grid-url","noGrid");
        if(grdUrl.equalsIgnoreCase("noGrid")){
            // Killing local browser service
            services.stop();
        }
    }

    private DesiredCapabilities getCapabilities(String capabilitiesFile) throws IOException {
        Properties capsProps = new Properties();
        capsProps.load(getClass().getResourceAsStream(capabilitiesFile));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String name : capsProps.stringPropertyNames()) {
            String value = capsProps.getProperty(name);
            if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
                capabilities.setCapability(name, Boolean.valueOf(value));
            } else if (value.startsWith("file:")) {
                capabilities.setCapability(name, new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
            } else {
                capabilities.setCapability(name, value);
            }
        }

        return capabilities;
    }

    private void startDriverService() throws IOException {

        switch (this.browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                services = new ChromeDriverService.Builder()//.usingDriverExecutable(new File("./exefiles/chromedriver.exe"))
                        .usingAnyFreePort().build();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                services = new GeckoDriverService.Builder()//.usingDriverExecutable(new File("./exefiles/chromedriver.exe"))
                        .usingAnyFreePort().build();
                break;
            case "safari":
                services = new SafariDriverService.Builder()//.usingDriverExecutable(new File("./exefiles/chromedriver.exe"))
                        .usingAnyFreePort().build();
                break;

        }

        services.start();
    }




    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {


        driver = new RemoteWebDriver(gridUrl, capabilities);
        baseUrl = getProperty("site.url");
        log.info("baseUrl : "+ baseUrl);
        driver.get(baseUrl);

    }
    @AfterMethod
    public void killBrowser() {
        // Killing browser
        driver.quit();
    }
}
