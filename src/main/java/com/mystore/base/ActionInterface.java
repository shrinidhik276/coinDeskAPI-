/**
 * 
 */
package com.mystore.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 
 */
public interface ActionInterface {
	// Click on an element
    void click(WebElement locator);

    // Type text into an element
    void type(By locator, String text);

    // Get the text from an element
    String getText(By locator);

    // Navigate to a URL
    void navigateTo(String url);
    void executeJSByElementID(String scriptToExecute, WebElement element);
    // Wait for an element to be visible
    void waitForElementToBeVisible(By locator);

}
