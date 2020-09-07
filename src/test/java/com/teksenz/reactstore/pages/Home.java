package com.teksenz.reactstore.pages;

import com.teksenz.reactstore.lib.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Home extends PageBase {
    String xpMenuLink = "//header/a[text()='XXX']";
    public Home(RemoteWebDriver driver) {
        super(driver);
    }
    public Home clickMenuLink(String menu){
        click(By.xpath(xpMenuLink.replace("XXX",menu)));
        return this;
    }

}
