package Academy.E2EProject;

import org.testng.ITestListener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import resources.ExtentReporterNG;

public class Listeners extends Base implements ITestListener {

	ExtentReporterNG erng = new ExtentReporterNG();
	ExtentReports extent = erng.getReportObject();// Now this object "extent" is available for onTestStart() method
	
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTestVariable =  new ThreadLocal<ExtentTest>(); //This is for executing TCs in Parallel
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		//extent.createTest(null) is now placed here and it is mandatory for every TC
				test = extent.createTest(result.getMethod().getMethodName()); //This "test" is an object of this class ExtentTest
				//This result.getMethod().getMethodName() will catch a name of a method
				
				extentTestVariable.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.PASS, "Test has Passed");
		
		extentTestVariable.get().log(Status.PASS, "Test has Passed"); //This is now for Parallel
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		//test.fail(result.getThrowable());
		
		extentTestVariable.get().fail(result.getThrowable()); //This is now for Parallel
		
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName(); //We are catching a name of a Method that is about to fail
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()); //This is to get the "driver" variable from an actual TC, since the driver in base.java is not alive
		} catch (Exception e)
		{
			
		}

		try {
			extentTestVariable.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), result.getMethod().getMethodName()); //Screenshots in the Extent report
			//This addScreenCaptureFromPath() uses our screenshot Method and the name of a class in TC
			//That's why this line below is not needed anymore FOR PARALLEL
			//getScreenShotPath(testMethodName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extent.flush(); //This goes at the end to notify that test has been done
		
		
	}

}
