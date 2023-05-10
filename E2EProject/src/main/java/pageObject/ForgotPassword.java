package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {

	
	public WebDriver driver;
	
	By userEmailForgotPass = By.id("user_email");
	By sendInstructions = By.name("commit");

	
	
	
	
	public ForgotPassword(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver; //this.driver is from "public WebDriver driver;" and the =driver is from public LandingPage(WebDriver driver)
	}

	
	public WebElement userEmailForgotPassMethod()
	{
		return driver.findElement(userEmailForgotPass);
	}
	
	
	public WebElement sendInstructionsMethod()
	{
		return driver.findElement(userEmailForgotPass);
	}
	
	
}
