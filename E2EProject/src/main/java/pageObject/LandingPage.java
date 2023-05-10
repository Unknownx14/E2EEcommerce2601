package pageObject;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

	
	public WebDriver driver;
	
	By noThx = By.xpath("//button[text()='NO THANKS']");
	By loginLink = By.xpath("//span[text()='Login']");
	By featuredCourses = By.xpath("//h2[text()='Featured Courses']");
	By navigationBar = By.cssSelector(".nav.navbar-nav.navbar-right");
	
	By relativeLoc01 = By.xpath("//h3[text()='An Academy to learn Everything about Testing']");
	

	
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver; //this.driver is from "public WebDriver driver;" and the =driver is from public LandingPage(WebDriver driver)
	}

	public WebElement noThxMethod()
	{
		
		return driver.findElement(noThx);
	}
	
	public WebElement loginLinkMethod()
	{
		return driver.findElement(loginLink);
	}
	
	public WebElement featuredCoursesMethod()
	{
		return driver.findElement(featuredCourses);
	}
	
	public WebElement navigationBarMethod()
	{
		return driver.findElement(navigationBar);
	}
	
	public WebElement relativeLoc01Method()
	{
		return driver.findElement(with(By.tagName("p")).below(relativeLoc01));
	}
	
	
	
}
