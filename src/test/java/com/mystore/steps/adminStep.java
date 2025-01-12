package com.mystore.steps;


//import com.mystore.RetryFailedCases.RetryAnalyzer;

import com.mystore.base.WebDriverFactory;
import com.mystore.hooks.Hook;
import com.mystore.pages.AdminPage;
import com.mystore.pages.BasePage;
import com.mystore.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class adminStep {
private WebDriver driver;

    private AdminPage adminPage;
    private LoginPage  loginPage;
    public adminStep() {
        // Get the WebDriver from WebDriverFactory
        driver = Hook.getDriver();
        adminPage = new AdminPage(driver);// Pass driver to LoginPage
//        adminPage = BasePage.getInstance(driver, AdminPage.class); // Get the Singleton LoginPage instance
    }
//    @Test(retryAnalyzer = RetryAnalyzer.class)
//    @Given("the user has launched {string}")
//    public void theUserHasLaunchedUrl(String url) {
//        loginPage.login(url);
////        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");
//
//    }

//    @And("user enter username and password")
//    public void userEnterUsernameAndPassword() {
//        adminPage.entercredentials();
//
//    }

    @And("user naviagtes to adminpage")
    public void userNaviagtesToAdminpage() {
        adminPage.entercredentials();

    }
}

