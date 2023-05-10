package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class) //This not needed when the Cucumber Testng dependency is imported into the pom.xml file
@CucumberOptions(
		features = "src/test/java/features/Login.feature", //All feature files in this package are to be run, src/test/java/features/Login.feature for a single file
		glue = "stepDefinition" //The package where stepdefinition files are located
		)

public class TestRunner extends AbstractTestNGCucumberTests { //This extends AbstractTestNGCucumberTests is for using a Cucumber test as a TestNG

}
