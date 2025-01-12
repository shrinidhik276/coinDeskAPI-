package com.mystore.driver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.mystore.driver.WebDriverManage;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class FirefoxDriverManager implements WebDriverManage {
	@Override
	public WebDriver createDriver()
	{
		 // Use WebDriverManager to automatically manage the driver binary
		WebDriverManager.firefoxdriver().setup();
		// Set ChromeOptions (optional, for headless mode, etc.)
		FirefoxOptions  options = new FirefoxOptions();
        options.addArguments("--start-maximized"); // Start Chrome in maximized mode
        
        // Create and return a new ChromeDriver instance with the specified options
        return new FirefoxDriver(options);
	}
	

}
