package com.teksenz.reactstore.testdata;


import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataBank {
    @DataProvider(name = "shoppingList")
    public static Object[] getShoppingList(){
        return new ShoppingList[]{
                ShoppingList.builder()
                        .customerName("John")
                        .productName("Handwash")
                        .quantity("2")
                        .build(),
                ShoppingList.builder()
                        .customerName("Martin")
                        .productName("MacbookPro")
                        .quantity("1")
                        .build()
        };
    }

}
