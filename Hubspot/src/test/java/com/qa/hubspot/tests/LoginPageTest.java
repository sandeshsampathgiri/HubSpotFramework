package com.qa.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.CommonUtil;

public class LoginPageTest {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	// LoginPageUsingPageFactory loginPage;
	LoginPage loginPage;
	Logger log;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		log = Logger.getLogger(BasePage.class);
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		log.info("----Opened Hubspot application---");
		CommonUtil.mediumWait();
		// loginPage = new LoginPageUsingPageFactory(driver);
		loginPage = new LoginPage(driver);
		CommonUtil.mediumWait();

	}

	@Test
	public void verifyLoginPageTitleTest() {

		String loginPageTitle = loginPage.getLoginPageTitle();
		log.info("Fetching title of login page");
		System.out.println("Login page title is: " + loginPageTitle);
		Assert.assertEquals(loginPageTitle, Constants.LOGINPAGE_TITLE, "Login page title is incorrect");
	}

	@Test
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "Signup Link is not displayed");
		log.info("Verifying if signup link is displayed on the login page");
	}

	@Test
	public void verifyLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		log.info("Logging into Hubspot application");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Closing "+prop.getProperty("browser")+" browser");
	}

}
