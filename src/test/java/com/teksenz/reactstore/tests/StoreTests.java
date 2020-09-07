package com.teksenz.reactstore.tests;

import com.teksenz.reactstore.lib.TestBase;
import com.teksenz.reactstore.pages.Store;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
@Epic("Epic-001")
@Feature("Feature1")
public class StoreTests extends TestBase {
    @Test(description = "Verify the title name transition from ADD TO CART to ADD MORE")
    @Story("Button name change in AddToCart")
    @Severity(SeverityLevel.BLOCKER)
    public void AddToCart(){
        String product = "Buffalo - Striploin";
        Assert.assertEquals(driver.getTitle(),"Store - React Boilerplate");
        Store store = new Store(driver);
        store.navigate()
                .verifyAddToCartTitle(product,"ADD TO CART")
                .addToCart(product,1)
                .verifyAddToCartTitle(product,"ADD MORE");

    }

//    @Test
//    @Description("Demo test to see a failure")
//    @Story("Check for a failure")
//    @Step("Test to make a failure")
//    @Severity(SeverityLevel.CRITICAL)
//    public void demoTest(){
//        Assert.fail("Failed the test");
//    }

    @Test
    public void registration(){
        throw new SkipException("Skipping the test");
    }


}
