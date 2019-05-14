package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementActions;

public class AboutContactPage extends BasePage {

	WebDriver driver;
	ElementActions elementactions;
	AboutContactPage abtContactPage;

	By contactsMainMenu = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By contactsSubMenu = By.xpath("//div[@aria-label='Contacts']//a[contains(text(),'Contacts')]");

	public AboutContactPage(WebDriver driver) {
		this.driver = driver;
		elementactions = new ElementActions(driver);
		abtContactPage = new AboutContactPage(driver);
	}

	public String getAboutContactPageTitle() {
		return driver.getTitle();
	}

	public ContactsPage navigateToContactsPage() {

		System.out.println("Driver= " + driver);
		elementactions.clickOnElement(contactsMainMenu);
		elementactions.clickOnElement(contactsSubMenu);
		return new ContactsPage(driver);
	}

}
