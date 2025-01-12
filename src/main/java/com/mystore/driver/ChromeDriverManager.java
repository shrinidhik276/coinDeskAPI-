package com.mystore.driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.mystore.driver.WebDriverManage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements WebDriverManage {
	
	@Override
	public WebDriver createDriver()
	{
		 // Use WebDriverManager to automatically manage the driver binary
		WebDriverManager.chromedriver().setup();
		// Set ChromeOptions (optional, for headless mode, etc.)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Start Chrome in maximized mode
        
        // Create and return a new ChromeDriver instance with the specified options
        return new ChromeDriver(options);
	}

}
