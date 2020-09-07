package com.teksenz.reactstore.tests;

import com.teksenz.reactstore.lib.TestBase;
import com.teksenz.reactstore.pages.Store;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreTests extends TestBase {
    @Test(testName = "Verify the title name transition from ADD TO CART to ADD MORE")
    public void AddToCart(){
        String product = "Buffalo - Striploin";
        Assert.assertEquals(driver.getTitle(),"Store - React Boilerplate");
        Store store = new Store(driver);
        store.navigate()
                .verifyAddToCartTitle(product,"ADD TO CART")
                .addToCart(product,1)
                .verifyAddToCartTitle(product,"ADD MORE");

    }


}
