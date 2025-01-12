package com.mystore.steps;

import com.mystore.customException.MissingConfigurationException;
import com.mystore.hooks.Hook;
import com.mystore.pages.CartPage;
import com.mystore.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;


public class addCart {

    private WebDriver driver;

    private CartPage cartPage;
    public addCart() {
        // Get the WebDriver from WebDriverFactory
        driver = Hook.getDriver();
        cartPage = new CartPage(driver);// Pass driver to LoginPage
//        loginPage = BasePage.getInstance(driver, LoginPage.class); // Get the Singleton LoginPage instance
    }
    @Given("I open the {string}")
    public void iOpenTheBrowser(String url) throws MissingConfigurationException {
        cartPage.iOpenTheBrowser(url);


    }

    @When("I navigate to eBay homepage")
    public void iNavigateToEBayHomepage() {

    }

    @And("I search for {string}")
    public void iSearchFor(String book) throws InterruptedException {
        cartPage.iSearchFor(book);

    }

    @And("I click on the first book in the list")
    public void iClickOnTheFirstBookInTheList() throws InterruptedException {
        cartPage.iClickOnTheFirstBookInTheList();
//ul[@class='srp-results srp-list clearfix']/li[1]
    }

    @And("I click on {string}")
    public void iClickOn(String actions) {
        cartPage.ClickOnItem(actions);

    }

    @Then("the cart should be updated and display the number of items")
    public void theCartShouldBeUpdatedAndDisplayTheNumberOfItems() {
        cartPage.countCartItem();
    }
}
