package com.teksenz.reactstore.tests;

import com.teksenz.reactstore.lib.TestBase;
import com.teksenz.reactstore.pages.Store;
import com.teksenz.reactstore.testdata.ShoppingList;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
@Epic("Purchase")
@Feature("Shopping cart")
@Story("Add a product to cart")
public class StoreTests extends TestBase {
    @Test(description = "Verify the title name transition from ADD TO CART to ADD MORE")

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

    @Test(description = "This is a skip demonstration test")
    public void registration(){
        throw new SkipException("Skipping the test");
    }

    @Test(dataProvider = "shoppingList",
            dataProviderClass = com.teksenz.reactstore.testdata.DataBank.class,
            description = "Parameterization demo"
    )
    public void parameterizedTest(ShoppingList shoppingList){
        System.out.println(shoppingList);

    }

}
