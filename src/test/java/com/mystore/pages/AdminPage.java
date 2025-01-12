package com.mystore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BasePage {
    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Locations')]")
    private WebElement linklocation;

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
       // Assign WebDriver instance passed from step definition
    }

//    public void usernavigatesadminpage() {
//        click(linklocation);
//
//    }

    public void entercredentials() {
        click(linklocation);
    }
}
