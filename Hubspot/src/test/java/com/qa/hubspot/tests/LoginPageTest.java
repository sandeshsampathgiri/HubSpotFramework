package com.qa.hubspot.tests;

import java.util.Properties;

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
	//LoginPageUsingPageFactory loginPage;
	LoginPage loginPage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.mediumWait();
		//loginPage = new LoginPageUsingPageFactory(driver);
		loginPage = new LoginPage(driver);
		

	}

	@Test
	public void verifyLoginPageTitleTest() {

		String loginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Login page title is: " + loginPageTitle);
		Assert.assertEquals(loginPageTitle, Constants.LOGINPAGE_TITLE, "Login page title is incorrect");
	}

	@Test
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink(), "Signup Link is not displayed");
	}

	@Test
	public void verifyLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
