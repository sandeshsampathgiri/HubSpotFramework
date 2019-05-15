package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementActions;

public class ContactDetailsPage extends BasePage{

	WebDriver driver;
	ElementActions elementactions;
	
	By contactsMainMenu = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By contactsSubMenu = By.xpath("//div[@aria-label='Contacts']//a[contains(text(),'Contacts')]");

	public ContactDetailsPage(WebDriver driver) {
		this.driver = driver;
		elementactions = new ElementActions(driver);
	}
	
	public String getContactsDetailsPageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage navigateToContactsPage() {
		elementactions.clickOnElement(contactsMainMenu);
		elementactions.clickOnElement(contactsSubMenu);
		return new ContactsPage(driver);
	}
	
	
	

	
}
