package com.qa.hubspot.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.CommonUtil;
import com.qa.hubspot.util.ElementActions;

public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementActions elementactions;
	ContactDetailsPage contactDetailsPage;

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
	By contactsMainMenu = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By contactsSubMenu = By.xpath("//div[@aria-label='Contacts']//a[contains(text(),'Contacts')]");
	By emailAddressList = By.xpath("//tbody/tr/td[@class='string-cell column-email']");
	By selectAllChkBox = By.xpath("//thead/tr/th/div[@data-selenium-test='DataGridHeaderRow__select-all-checkbox']");
	By permanentDeleteLnk = By.xpath("//span[contains(text(),'Permanently delete')]");

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

		contactDetailsPage = new ContactDetailsPage(driver);

		contactDetailsPage.navigateToContactsPage();

		CommonUtil.mediumWait();

		int updated_contactCount = elementactions.getContactsCount(contactsCount);

		Assert.assertEquals((updated_contactCount - initial_contactCount), 1);

	}

	public void createContact_newapproach(String emailAddress, String fname, String lname, String job_title) {

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

		contactDetailsPage = new ContactDetailsPage(driver);

		contactDetailsPage.navigateToContactsPage();

		CommonUtil.mediumWait();

		List<WebElement> test1 = elementactions.getEmailAddressList(emailAddressList);
		String test2;
		List<String> test3 = new ArrayList<String>();

		for (int i = 0; i < test1.size(); i++) {
			System.out.println(test1.get(i).getText());
			test2 = test1.get(i).getText();
			test3.add(test2);

			if (test3.contains(emailAddress)) {
				Assert.assertTrue(true);
			}

			else {
				Assert.assertTrue(false);
			}

		}

	}

}
