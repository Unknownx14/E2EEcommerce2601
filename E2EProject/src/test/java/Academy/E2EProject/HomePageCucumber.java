package Academy.E2EProject;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.ForgotPassword;
import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.Base;

public class HomePageCucumber extends Base { //This extends Base allows to use all Methods from this class Base

	
	//This is only if we have parallel execution of these test in testng.xml file (video 202)
		public WebDriver driver;
		
	//Creating this Log object for this LogManager API
			private static Logger log = LogManager.getLogger(Base.class.getName());
			

	
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String Username, String Password) throws IOException
	{
		driver = initializeDriver(); //Method form Base class
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// Creating an object of this class
		LandingPage lp = new LandingPage(driver);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='NO THANKS']")));
		lp.noThxMethod().click();
		lp.loginLinkMethod().click();
		
		LoginPage lop = new LoginPage(driver);
		lop.userEmailMethod().sendKeys(Username);
		log.info(Username);
		lop.passwordMethod().sendKeys(Password);
		log.info(Password);
		lop.loginButtonMethod().click();
		
		Assert.assertTrue(lop.forgetPassPresenceMethod().isDisplayed());
		
		
	}
	
	
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		
		Object[][] data = new Object[2][2]; //There are 2 rows and 2 columns (index starts from 0, so in order to have 0 and 1, there must be [2])
		data[0][0] = "private@spotter.com";
		data[0][1] = "privatePassword";
		
		data[1][0] = "enterprise@spotter.com";
		data[1][1] = "enterprisePassword";

		
		return data;
	}
	
}
