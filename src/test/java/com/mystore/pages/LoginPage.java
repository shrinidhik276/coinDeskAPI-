package com.mystore.pages;
import com.mystore.customException.InvalidCredentialsException;
import com.mystore.customException.MissingConfigurationException;
import com.mystore.utility.FileUtils;
import com.mystore.utility.ConfigurationManager;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginPage extends BasePage  {
	
	 private WebDriver driver;
//	WebDriver driver = driver();
@FindBy(name = "username")
private WebElement txtusername;

@FindBy(name = "password")
	private WebElement txtpassword;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginbutton;
	@FindBy(xpath = "//input[@id='fromCity']")
	private WebElement fromCity;
	@FindBy(xpath = "//input[@id='toCity']")
	private WebElement toCity;
	@FindBy(xpath = "//ul[@class='react-autosuggest__suggestions-list']")
	private WebElement autosuggestFrom;

	    // Locators for the login page elements


public LoginPage(WebDriver driver) {
    super(driver);
    this.driver = driver;  // Assign WebDriver instance passed from step definition
}
	public void login(String url) {
	logger.info("launching url");

		driver.navigate().to(url);
//		Assert.assertTrue(false);
	}

	public void entercredentials(String filename) throws IOException, ParseException {
		HashMap<String,String>credentials=getCredentials(filename);
		// Get username and password
		String username= credentials.get("username");
		String password=credentials.get("password");
		try {
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				throw new InvalidCredentialsException("Username or password cannot be empty or null.");
			}
			txtusername.sendKeys(username);
			txtpassword.sendKeys(password);
			click(loginbutton);
		}
	catch (InvalidCredentialsException e) {
				System.out.println("Error during login: " + e.getMessage());
		// Assert the test fails if credentials are invalid
		Assert.fail("Invalid credentials: " + e.getMessage()); // This will fail the test
			}

	}

	public void userProvideUserDetailsFrom(String filename) throws IOException, ParseException {
	List<HashMap<String,String>>credentialsList= FileUtils.getexcelData(filename);
		// Iterate over each set of credentials
		for (HashMap<String, String> credentials : credentialsList) {
			String username = credentials.get("username");  // Get the username from the current row
			String password = credentials.get("password");
			try {
				if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
					throw new InvalidCredentialsException("Username or password cannot be empty or null.");
				}
				txtusername.sendKeys(username);
				txtpassword.sendKeys(password);
				click(loginbutton);
			}
			catch (InvalidCredentialsException e) {
				System.out.println("Error during login: " + e.getMessage());

			}
	}
}
	@Test(invocationCount = 3)
	public void theUserNavigatedTo(String url) throws MissingConfigurationException {
		try {
			url = ConfigurationManager.getProperty("url");
		navigateTo(url);
		} catch (MissingConfigurationException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();  // Print the full stack trace for MissingConfigurationException
			throw e;  // Rethrow the exception if necessary
		}
	}

	public void extractCityNames() {
	driver.findElement(By.xpath("//span[@data-cy='closeModal']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement fromCityField = wait.until(ExpectedConditions.elementToBeClickable(fromCity));
		fromCityField.click();
		// Wait for the list of city suggestions to load and extract the city nam
		List<WebElement> fromcityoptions=wait.until(ExpectedConditions.visibilityOfAllElements(autosuggestFrom));
		// Create a list to hold city names for "From" cities
		List<String> fromcitynames=new ArrayList<>();
		// Loop through the "From" city suggestions and store city names
		for(WebElement option :fromcityoptions){
			String city=option.getText();
		fromcitynames.add(city);
			// Print "From" cities
			System.out.println("From Cities:");
			for(String citiy:fromcitynames){
				System.out.println(city);

			}
		}


	}
}
