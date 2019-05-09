package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.CommonUtil;

public class HomePageTest {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.mediumWait();
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		CommonUtil.longWait();
	}

	@Test
	public void verifyHomePageTitle() {
		String homePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, Constants.HOMEPAGE_TITLE);
	}

	@Test
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeaderValue();
		System.out.println("Home page header is " + header);
		Assert.assertEquals(header, Constants.HOMEPAGE_HEADER);
	}

	@Test
	public void verifyLoggedInAccountNameTest() {
		String accountName = homePage.getLoggedInAccountName();
		System.out.println("Logged in account name is: " + accountName);
		Assert.assertEquals(accountName, prop.getProperty("loggedinname"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
