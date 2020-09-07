package com.teksenz.reactstore.pages;

import com.teksenz.reactstore.lib.PageBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.stream.IntStream;

public class Store extends PageBase {
    protected static final Logger log = LoggerFactory.getLogger(Store.class);
    private String xpProductName = "//*[@Class='card card-body']/p[text()='XXX']";
    private String xpPrice = xpProductName+"/following-sibling::h3[position()=1]";
    private String xpAddToCart = xpProductName+ "/following-sibling::div[position()=1]/button";

    public Store(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Navigate to Store page")
    public Store navigate(){
        new Home(driver).clickMenuLink(MenuItem.Store);
        log.debug("Navigated to Store page");
        return this;
    }
    @Step("Add product to cart")
    public Store addToCart(String product, int qty){
        IntStream.of(qty).forEach(i->{
            By by = By.xpath(xpAddToCart.replace("XXX",product));
            click(by);
            sleep(100);
        });
        log.debug("Added a product to cart");
        return this;
    }
    @Step("Verify the text on 'Add To Cart' button")
    public Store verifyAddToCartTitle(String product,String expText){
        By by = By.xpath(xpAddToCart.replace("XXX",product));
        String actText = getText(by);
        Assert.assertEquals(actText,expText,"Add to cart button text is incorrect");
        log.debug("verified AddToCartTitle");
        return this;
    }

}
