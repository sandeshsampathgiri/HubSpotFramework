package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.CommonUtil;

public class ContactsPageTest {

	BasePage basePage;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		driver.get(prop.getProperty("url"));
		CommonUtil.mediumWait();
		loginPage = new LoginPage(driver);
		CommonUtil.mediumWait();
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		CommonUtil.mediumWait();
		contactsPage = homePage.navigateToContactsPage();
		CommonUtil.mediumWait();
	}

	@Test
	public void verifyPageTitleTest() {
		String contactsPageTitle = driver.getTitle();
		Assert.assertEquals(contactsPageTitle, Constants.CONTACTSPAGE_TITLE);
	}
	
	@Test
	public void verifyPageHeader()
	{
		String contactPageHeader = contactsPage.getHomePageHeaderValue();
		Assert.assertEquals(contactPageHeader,Constants.CONTACTSPAGE_HEADER);
	}
	
	@Test
	public void createContact()
	{
		contactsPage.createContact("test@test.com", "Firstname", "Lastname", "QA");
		
	}
	
	@DataProvider(name="dataforcontactcreation")
	public void getDataForContactCreation()
	{
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
