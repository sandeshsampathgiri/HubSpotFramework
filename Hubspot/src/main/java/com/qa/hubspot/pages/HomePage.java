package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementActions;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementActions elementactions;

	By homepageHeader = By.xpath("//h1[@class='private-page__title']");
	By accountName = By.xpath("//span[@class='account-name ']");
	By contactsMainMenu = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By contactsSubMenu = By.xpath("//div[@aria-label='Contacts']//a[contains(text(),'Contacts')]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementactions = new ElementActions(driver);
	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String getHomePageHeaderValue() {

		return elementactions.getElement(homepageHeader).getText();

	}

	public String getLoggedInAccountName() {
		return elementactions.getElement(accountName).getText();
	}

	public ContactsPage navigateToContactsPage()
	{
		elementactions.clickOnElement(contactsMainMenu);
		elementactions.clickOnElement(contactsSubMenu);
		return new ContactsPage(driver);
	}
	
	public void testMethod()
	{
		
	}
}

