package com.mystore.pages;
import com.mystore.base.ActionInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BasePage implements ActionInterface {
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    private static ThreadLocal<BasePage> pageInstance = new ThreadLocal<>();
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebElements for this page
        PageFactory.initElements(driver, this);  // Initialize elements using PageFactory
    }
    // Generic getInstance method for all page objects

    @Override
    public void click(WebElement element) {
//        waitForClickable(element);
        logger.info("Clicking on", element);
        element.click();
    }
    @Override
    public void navigateTo(String url) {
        driver.get(url);
    }

    @Override
    public void executeJSByElementID(String scriptToExecute, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(scriptToExecute, element);
    }

    @Override
    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
    @Override
    public void type(By locator, String text) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getText(By locator) {
        // TODO Auto-generated method stub
        return null;
    }

}
