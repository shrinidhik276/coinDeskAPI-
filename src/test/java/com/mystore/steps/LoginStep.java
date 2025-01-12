package com.mystore.steps;


//import com.mystore.RetryFailedCases.RetryAnalyzer;
import com.mystore.customException.MissingConfigurationException;
import com.mystore.hooks.Hook;
import com.mystore.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
//import com.mystore.hooks.Hook;
import static org.testng.Assert.assertTrue;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import com.mystore.base.*;
import com.mystore.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import javax.naming.Context;
import java.io.IOException;

public class LoginStep  {
private WebDriver driver;

    private LoginPage loginPage;
    public LoginStep() {
        // Get the WebDriver from WebDriverFactory
        driver = Hook.getDriver();
        loginPage = new LoginPage(driver);// Pass driver to LoginPage
//        loginPage = BasePage.getInstance(driver, LoginPage.class); // Get the Singleton LoginPage instance
    }
//    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Given("the user has launched {string}")
    public void theUserHasLaunchedUrl(String url) {
        loginPage.login(url);
//        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");

    }

    @And("user enter username and password from {string}")
    public void userEnterUsernameAndPassword(String filename) throws IOException, ParseException {
        loginPage.entercredentials(filename);
    }

    @Then("user provide user details from {string}")
    public void userProvideUserDetailsFrom(String filename) throws IOException, ParseException {
        loginPage.userProvideUserDetailsFrom(filename);

    }
    @Test(invocationCount = 5)
    @Given("the user navigated to {string}")
    public void theUserNavigatedTo(String url) throws MissingConfigurationException {
        loginPage.theUserNavigatedTo(url);
    }

    @And("user extract city names")
    public void userExtractCityNames() {
        loginPage.extractCityNames();

    }

//    @And("user enter username and password")
//    public void userEnterUsernameAndPassword() {
//        loginPage.entercredentials();
//
//    }
}

