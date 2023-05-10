package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	
	public WebDriver driver;
	
	By userEmail = By.id("user_email");
	By password = By.id("user_password");
	By loginButton = By.name("commit");
	By forgetPass = By.className("link-below-button");
	
	
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver; //this.driver is from "public WebDriver driver;" and the =driver is from public LandingPage(WebDriver driver)
	}

	
	public WebElement userEmailMethod()
	{
		return driver.findElement(userEmail);
	}
	
	public WebElement passwordMethod()
	{
		return driver.findElement(password);
	}
	
	public WebElement loginButtonMethod()
	{
		return driver.findElement(loginButton);
	}
	
	public ForgotPassword forgetPassMethod()
	{
		driver.findElement(forgetPass).click();
		ForgotPassword fp = new ForgotPassword(driver);
		return fp;
	}
	
	public WebElement forgetPassPresenceMethod()
	{
		 return driver.findElement(forgetPass);
	}
	
}
