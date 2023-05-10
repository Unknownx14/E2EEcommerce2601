package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
//import cucumber.api.junit.Cucumber;
import io.cucumber.junit.Cucumber; //This is needed to be imported
import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.Base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@RunWith(Cucumber.class)
public class StepDefinitions extends Base {

    @Given("^Initialize the browser with chrome in a maximum window size$")
    public void initialize_the_browser_with_chrome_in_a_maximum_window_size() throws Throwable {
    	driver = initializeDriver(); //Method form Base class
    	driver.manage().window().maximize();
    	
    }

    @When("^User logs in into the application with an \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_logs_in_into_the_application_with_an_something_and_something(String strArg1, String strArg2) throws Throwable {
    	
    	Logger log = LogManager.getLogger(Base.class.getName());
    	
    	LoginPage lop = new LoginPage(driver);
		lop.userEmailMethod().sendKeys(strArg1);
		log.info(strArg1);
		lop.passwordMethod().sendKeys(strArg2);
		log.info(strArg2);
    }
    
    
    @When("^User logs in into the application with an (.+) and (.+)$")
    public void user_logs_in_into_the_application_with_an_and(String username, String password) throws Throwable {
Logger log = LogManager.getLogger(Base.class.getName());
    	
    	LoginPage lop = new LoginPage(driver);
		lop.userEmailMethod().sendKeys(username);
		log.info(username);
		lop.passwordMethod().sendKeys(password);
		log.info(password);
    }

    @Then("^Verify that a user is successfully logged in$")
    public void verify_that_a_user_is_successfully_logged_in() throws Throwable {
    	LoginPage lop = new LoginPage(driver);
    	Assert.assertTrue(lop.forgetPassPresenceMethod().isDisplayed());
    	System.out.println("Malo sam se smorio");
    }

    @And("^Navigate to the \"([^\"]*)\" website as a Home page$")
    public void navigate_to_the_something_website_as_a_home_page(String strArg1) throws Throwable {
    	//driver.get(prop.getProperty("url"));
    	driver.get(strArg1);
    }

    @And("^Define the explicit wait$")
    public void define_the_explicit_wait() throws Throwable {
    	//Explicit wait - define the object of the class
    			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
    			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='NO THANKS']")));
    }

    

    @And("^Click on the \"([^\"]*)\" button$")
    public void click_on_the_something_button(String strArg1) throws Throwable {
    	LandingPage lp = new LandingPage(driver);
		lp.noThxMethod().click();
    }

    @And("^Click on the Login link in order to get to the Login page$")
    public void click_on_the_login_link_in_order_to_get_to_the_login_page() throws Throwable {
    	LandingPage lp = new LandingPage(driver);
    	lp.loginLinkMethod().click();
    }

    @And("^Clicks on the Login button$")
    public void clicks_on_the_login_button() throws Throwable {
    	LoginPage lop = new LoginPage(driver);
    	lop.loginButtonMethod().click();
    	
    }
    
    
    @And("^Close borwsers$")
    public void close_borwsers() throws Throwable {
    	driver.close();
    }

}