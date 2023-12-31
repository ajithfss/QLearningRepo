package com.qlearning.uat.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qlearning.uat.base.Base;
import com.qlearning.uat.pages.HomePage;
import com.qlearning.uat.pages.RegisterPage;

public class RegisterTest extends Base{
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	RegisterPage registerPage;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.selectCreateAccount();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
    
	
	@Test(groups={"smoke"})
	public void verifyRegisterPageUrl() {
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url,"https://uat.qlearning.academy/create-account");
	}
    
    @Test(groups={"regression"})
	public void verifyRegisterationWithInvalidEmailId() {
		
    	
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedfgmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("UATinst@1234");
    	registerPage.clickOnCreateAccountOption();
    	String actualInvalidEmailWarning = registerPage.retrieveInvalidEmailWarningText();
		Assert.assertEquals(actualInvalidEmailWarning, "Invalid email address" );
	}
    
   
    
    @Test(groups={"smoke"})
    public void verifyRegistrationLeavingFirstNameField() {
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("UATinst@1234");
    	registerPage.clickOnCreateAccountOption();
    	String actualFirstNameWarning = registerPage.retrieveFirstNameRequiredWarning();
    	Assert.assertEquals(actualFirstNameWarning, "First name required");
    }
    
    @Test(groups={"smoke"})
    public void verifyRegistrationLeavingLastNameField() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("UATinst@1234");
    	registerPage.clickOnCreateAccountOption();
    	String actualLastNameWarning = registerPage.retrieveLastNameRequiredWarning();
    	Assert.assertEquals(actualLastNameWarning, "Last name required");
	
    }
    
    @Test(groups={"smoke"})
    public void verifyRegistrationLeavingEmailAddressField() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("UATinst@1234");
    	registerPage.clickOnCreateAccountOption();
    	String actualEmailWarning = registerPage.retrieveEmailRequiredWarning();
    	Assert.assertEquals(actualEmailWarning, "Please enter your email address");
    }
    @Test(groups={"smoke"})
    public void verifyRegistrationLeavingMobileNumberField() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterPassword("UATinst@1234");
    	registerPage.clickOnCreateAccountOption();
    	String actualMobileNumberWarning = registerPage.retrieveMobileNumberRequiredWarning();    	
    	Assert.assertEquals(actualMobileNumberWarning, "Please enter your mobile number");
    	
    }
    
    @Test(groups={"smoke"})
    public void verifyRegistrationLeavingPasswordField() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.clickOnCreateAccountOption();
    	String actualPasswordWarning = registerPage.retrievePasswordRequiredWarning();
    	Assert.assertEquals(actualPasswordWarning, "Please Enter Password");
    }
    
    @Test(groups={"regression"})
    public void verifyRegistrationWithInvalidMobileNumber() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterMobileNumber("87986");
    	registerPage.enterPassword("UATinst@1234");
    	registerPage.clickOnCreateAccountOption();
    	String actualInvalidMobileWarning = registerPage.retrieveinvalidMobileNumberWarning();
    	Assert.assertEquals(actualInvalidMobileWarning, "invalid mobile number");	
    }
    
    @Test(groups={"regression"})
    public void verifyShortPasswordWarning() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("UAT");
    	registerPage.clickOnCreateAccountOption();
    	String actualShortPasswordWarning = registerPage.retrieveShortPasswordWarning();
    	Assert.assertEquals(actualShortPasswordWarning, "It is Too short");
    	
    }
    
    @Test(groups={"regression"})
    public void verifyPasswordCombinationWarning() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("asdewedf@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("asdfghjsurt");
    	registerPage.clickOnCreateAccountOption();
    	String actualShortPasswordWarning = registerPage.retrievePasswordCombinationWarning();
    	Assert.assertEquals(actualShortPasswordWarning, "Please include combinations of Lowercase, Uppercase, Numbers and special characters.");	
    }
    
    @Test(groups={"regression"})
    public void verifyUserAlreadyExistWarning() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("qltestingteam382@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("Testing@123");
    	registerPage.clickOnCreateAccountOption();
    	String actualUserExistWarning = registerPage.retrieveUserAlreadyExistWarning();
    	Assert.assertEquals(actualUserExistWarning, "User already Exist,Please login");
    }
    
    @Test(groups={"regression"})
    public void verifyMobileNumberAlreadyExistWarning() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("qltestim382@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("Testing@123");
    	registerPage.clickOnCreateAccountOption();
    	String actualMobileNumberExistWarning = registerPage.retrieveMobileNumberExistWarningText();
		Assert.assertEquals(actualMobileNumberExistWarning, "User with same mobile number already exist" );
    	
    }
    
    @Test(groups={"smoke"})
    public void verifyRegistrationWithoutFillingAnyFields() {
    	registerPage.clickOnCreateAccountOption();
    	String actualFirstNameWarning = registerPage.retrieveFirstNameRequiredWarning();
    	Assert.assertEquals(actualFirstNameWarning, "First name required");
    	String actualLastNameWarning = registerPage.retrieveLastNameRequiredWarning();
    	Assert.assertEquals(actualLastNameWarning, "Last name required");
    	String actualEmailWarning = registerPage.retrieveEmailRequiredWarning();
    	Assert.assertEquals(actualEmailWarning, "Please enter your email address");
    	String actualMobileNumberWarning = registerPage.retrieveMobileNumberRequiredWarning();    	
    	Assert.assertEquals(actualMobileNumberWarning, "Please enter your mobile number");
    	String actualPasswordWarning = registerPage.retrievePasswordRequiredWarning();
    	Assert.assertEquals(actualPasswordWarning, "Please Enter Password");	
    }
    
    @Test(groups={"regression"})
    public void verifyRegistrationWithRegisteredStudentEmail() {
    	registerPage.enterFirstName("Qlearning");
    	registerPage.enterMiddleName("academy");
    	registerPage.enterLastName("Instructor");
    	registerPage.enterEmailAddress("ajithaadhav@gmail.com");
    	registerPage.enterMobileNumber("9626919255");
    	registerPage.enterPassword("Testing@123");
    	registerPage.clickOnCreateAccountOption();
    	String actualAnotherRoleExistWarning = registerPage.retrieveUserExistInAnotherRoleWarning();
    	Assert.assertEquals(actualAnotherRoleExistWarning, "User already Exist as another Role. kindly use different Email Id");
    	
    	
    }
    
    
    
    
    
    
    
    
}
