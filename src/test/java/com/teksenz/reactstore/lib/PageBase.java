package com.teksenz.reactstore.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {

    protected RemoteWebDriver driver;
    private final long WAIT_TIME = 5L;

    public PageBase(RemoteWebDriver driver) {
        this.driver = driver;
    }
    protected void click(By by) {
        new WebDriverWait(driver,WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(by))
                .click();
    }

    protected String getText(By by) {
        return new WebDriverWait(driver,WAIT_TIME)
                .until(ExpectedConditions
                        .elementToBeClickable(by))
                .getText();
    }
    protected boolean isElementPresent(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .presenceOfAllElementsLocatedBy(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }
    protected boolean isElementVisible(By by) {
        try {
            new WebDriverWait(driver,WAIT_TIME)
                    .until(ExpectedConditions
                            .visibilityOfAllElementsLocatedBy(by));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    protected void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
