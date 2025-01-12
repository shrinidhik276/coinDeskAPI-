/**
 *
 */
package com.mystore.hooks;
import com.aventstack.extentreports.service.ExtentService;


//import com.aventstack.extentreports.service.ExtentService;
//import driver.driverManager.CreateDriver;
import com.mystore.base.WebDriverFactory;
import io.cucumber.java.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hook {
    private WebDriver driver;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    @Before
    public void beforeScenario(Scenario scenario) {
        WebDriver driver = WebDriverFactory.getDriver(); // Initialize WebDriver
        driverThreadLocal.set(driver);
        System.out.println("Starting the scenario: " + scenario.getName());
        System.out.println("Driver initialized: " + (driver != null));
        ExtentService.getInstance().setSystemInfo("os", "windows");
    }
    // Method to retrieve WebDriver instance
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        System.out.println("Retrieving driver from ThreadLocal: " + (driver != null));
        return driver;
    }
    @After
    public void afterScenario(Scenario scenario) {
        // Get the WebDriver instance from the current thread
//        WebDriver driver = driverThreadLocal.get();
//        if (driver != null) {
//            try {
//                System.out.println("Quitting the WebDriver...");
//                WebDriverFactory.quitDriver();  // Quit the WebDriver
//            } catch (Exception e) {
//                System.out.println("Error quitting WebDriver: " + e.getMessage());
//            } finally {
//                // Ensure the ThreadLocal driver is removed
//                System.out.println("WebDriver quit and cleaned up.");
//            }
//        } else {
//            System.out.println("No WebDriver to quit.");
//        }
//        System.out.println("Finished the scenario: " + scenario.getName());
    }
    @AfterStep()
    public  void actionPostEachStep(Scenario scenario) {
        if(scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", screenshot.toString());
        }
    }
}