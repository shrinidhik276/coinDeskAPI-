package com.mystore.pages;

import com.mystore.customException.MissingConfigurationException;
import com.mystore.utility.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CartPage extends BasePage {
    private WebDriver driver;

    @FindBy(id = "gh-ac")
    private WebElement searchBox;

    @FindBy(id = "gh-btn")
    private WebElement searchBtn;
    @FindBy(xpath = "//ul[@class='srp-results srp-list clearfix']/li[1]")
    private WebElement firstItem;

    @FindBy(xpath = "//span[@class='ux-call-to-action__text' and text()='Add to cart']")
    private WebElement addToCartButton;

   @FindBy(id="gh-cart-n")
   private WebElement cartItemCount;



    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
       // Assign WebDriver instance passed from step definition
    }


    public void iOpenTheBrowser(String url) throws MissingConfigurationException {
        try {
            url = ConfigurationManager.getProperty("url");
            navigateTo(url);
        } catch (MissingConfigurationException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for MissingConfigurationException
            throw e;  // Rethrow the exception if necessary
        }

    }

    public void iSearchFor(String book) throws InterruptedException {
        searchBox.sendKeys(book);
        searchBtn.click();
        Thread.sleep(1000);


    }

    public void iClickOnTheFirstBookInTheList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement firstItemElement = wait.until(ExpectedConditions.elementToBeClickable(firstItem));

        String title=firstItem.getText().split("\n")[0];
        System.out.println(title);
        driver.findElement(By.xpath("//span[contains(text(), '"+title+"')]")).click();
//        executeJSByElementID("arguments[0].click();", firstItemElement);
        Thread.sleep(2000);
        System.out.println("I clicked");


    }

    public void ClickOnItem(String actions) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        if(actions.equals("Add to cart")){
            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartButton.click();
        }
    }

    public int countCartItem() {
        String cartText = cartItemCount.getText();
        String itemCount = cartText.replaceAll("[^0-9]", "");
        System.out.println("no of items"+ itemCount);// Extracts numeric part
        return Integer.parseInt(itemCount);

    }
}
