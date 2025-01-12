package com.mystore.base;

import org.openqa.selenium.WebDriver;

import com.mystore.driver.ChromeDriverManager;
import com.mystore.driver.FirefoxDriverManager;
import com.mystore.driver.WebDriverManage;

//ThreadLocal allows multiple threads to have their own instances of WebDriver, ensuring thread-safety when running tests in parallel.
//Singleton pattern is applied as WebDriver is accessed via a single point, ensuring that only one instance is shared within the current thread.

public class WebDriverFactory {
    // ThreadLocal ensures each thread gets its own WebDriver instance (useful in parallel execution)
    private static ThreadLocal<WebDriver> threadLocalDriver= new ThreadLocal<>();

    private WebDriverFactory() {
        // Private constructor to prevent instantiation
    }

    public synchronized  static WebDriver getDriver() {
        WebDriver driver = threadLocalDriver.get();

        // If driver already exists, return it
        if (driver != null) {
            return driver;
        }

        // Read the browser name from system properties or default to "chrome"
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        // Create a new driver based on the browser type
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManage manager = new ChromeDriverManager();
            driver = manager.createDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManage manager = new FirefoxDriverManager();
            driver = manager.createDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        threadLocalDriver.set(driver); // Store the driver in ThreadLocal
        return driver;
    }

//    public static void setDriver(WebDriver driver) {
//        threadLocalDriver.set(driver);
//    }
    public static void quitDriver() {
        WebDriver driver = threadLocalDriver.get();
        if (driver != null) {
            try {
                System.out.println("Quitting WebDriver...");
                    driver.quit();  // Quit the browser session
            } catch (Exception e) {
                System.out.println("Error quitting WebDriver: " + e.getMessage());
            } finally {
                threadLocalDriver.remove();  // Clean up the thread-local driver reference
            }
        }
    }}

