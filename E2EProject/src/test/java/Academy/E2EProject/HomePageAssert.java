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


import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.Base;

public class HomePageAssert extends Base { //This extends Base allows to use all Methods from this class Base

	//This is only if we have parallel execution of these test in testng.xml file (video 202)
	public WebDriver driver;
	
	//Creating this Log object for this LogManager API
		private static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void intializeBrowser() throws IOException
	{
		//All this is moved from @Test into @BeforeTest
		driver = initializeDriver(); //Method form Base class
		log.info("Driver is initialized");
		driver.manage().window().maximize();
		log.info("Window is maximized");
		
	}
	
	
	@Test
	public void basePageNavigation() throws IOException
	{
		/*driver = initializeDriver(); //Method form Base class
		driver.manage().window().maximize();*/
		driver.get(prop.getProperty("url"));
		
		//Explicit wait - define the object of the class
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// Creating an object of this class
		LandingPage lp = new LandingPage(driver);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='NO THANKS']")));
		lp.noThxMethod().click();
		Assert.assertEquals(lp.featuredCoursesMethod().getText(), "FEATURED COURSES1");
		log.info("Successfully validated h2 text");
		Assert.assertTrue(lp.navigationBarMethod().isDisplayed());
		log.info("Navigation bar is displayed");
		

		
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
	
	
	
}
