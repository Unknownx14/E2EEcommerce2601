package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import dev.failsafe.internal.util.Assert;


public class Base {

	public WebDriver driver; //Making this driver public and creating it only once
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		
		prop = new Properties(); //This is for Data Driven
		//We have to create an object (fis) that has an access to this excel file
		//System.getProperty("user.dir") use instead of C:\\Users\\joko2909\\SeleniumTraining\\E2EProject to get this path dynamically 
		//Like this FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		FileInputStream fis = new FileInputStream("C:\\Users\\joko2909\\SeleniumTraining\\E2EProject\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		String wantedBrowser = prop.getProperty("browser"); //This goes to the data.properties file and searches for browser
		
		//String wantedBrowser = System.getProperty("browser"); //This is for an execution from the Maven, comment out the line above
		
		if(wantedBrowser.equals("Chrome"))
		{
		System.setProperty("webdriver.chrome.driver", "C:\\JK\\chromedriver_win32\\chromedriver.exe");
		//webdriver.chrome.driver is a key value, a property
		driver = new ChromeDriver();
		}
		else if(wantedBrowser.equals("FireFox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\JK\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(wantedBrowser.equals("Edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\JK\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return driver; //This Method returns this driver
		
	}
	
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException //This Method is to capture a failed TC, testCaseName uses result.getMethod().getMethodName(); from Listeners
	{//This testCaseName we get from Listeners as String testMethodName, and this WebDriver driver as well
		//Now the driver in this MEthod is alive
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile)); //import this one [import org.apache.commons.io.FileUtils] after placing a dependency into pom.xml;
		
		return destinationFile;
	}
	
	
}
