package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	ExtentReports extent;
	public ExtentReports getReportObject()
	{
		
		
		//This ExtentSparkReporter class expects a path where this report should be created
				//This variable "path" creates a new folder and inside this index.html file
				String path = System.getProperty("user.dir")+"\\reports\\index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results JK"); //This will be the name of our report
				reporter.config().setDocumentTitle("Test Results JK"); //This will be the name of document in a browser
				
				//This ExtentReports is a main class
				extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Jovan Kovacevic");
				return extent;
		
	}
	
}
