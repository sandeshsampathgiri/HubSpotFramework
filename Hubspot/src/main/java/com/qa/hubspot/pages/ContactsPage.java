package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.CommonUtil;
import com.qa.hubspot.util.ElementActions;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementActions elementactions;
	AboutContactPage abtContactPage;

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementactions = new ElementActions(driver);
	}

	By contactsCount = By.xpath("//td[@class='checkbox-cell p-x-3 column-hs__multi_checkbox']");
	By contactsPageHeader = By.xpath("//h1[@class='private-header__heading']");
	By createContactPrimaryBtn = By.xpath("//span[contains(text(),'Create contact')]");
	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//div[@class='private-form__input-wrapper']/input[@id='uid-ctrl-2']");
	By lastName = By.xpath("//div[@class='private-form__input-wrapper']/input[@id='uid-ctrl-3']");
	By jobTitle = By.xpath("//div[@class='private-form__input-wrapper']/input[@id='uid-ctrl-5']");
	By createContactSecondaryBtn = By.xpath("//button[@data-confirm-button='accept']");
	

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String getHomePageHeaderValue() {

		return elementactions.getElement(contactsPageHeader).getText();

	}

	public void createContact(String emailAddress, String fname, String lname, String job_title) {

		int initial_contactCount = elementactions.getContactsCount(contactsCount);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(createContactPrimaryBtn));
		elementactions.clickOnElement(createContactPrimaryBtn);

		wait.until(ExpectedConditions.elementToBeClickable(email));
		elementactions.sendKeysElement(email, emailAddress);

		wait.until(ExpectedConditions.elementToBeClickable(firstName));
		elementactions.sendKeysElement(firstName, fname);

		wait.until(ExpectedConditions.elementToBeClickable(lastName));
		elementactions.sendKeysElement(lastName, lname);

		wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
		elementactions.sendKeysElement(jobTitle, job_title);

		wait.until(ExpectedConditions.elementToBeClickable(createContactSecondaryBtn));
		elementactions.clickOnElement(createContactSecondaryBtn);

		CommonUtil.mediumWait();

		abtContactPage.navigateToContactsPage();
		
		CommonUtil.mediumWait();
		
		wait.until(ExpectedConditions.elementToBeClickable(contactsCount));

		int updated_contactCount = elementactions.getContactsCount(contactsCount);

		Assert.assertEquals(updated_contactCount - initial_contactCount, 1);

	}

}
