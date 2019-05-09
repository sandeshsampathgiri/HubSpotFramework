package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;

public class HomePageUsingPageFactory extends BasePage {

	WebDriver driver;

	@FindBy(xpath = "//h1[@class='private-page__title']")
	WebElement homepageHeader;

	@FindBy(xpath = "//span[@class='account-name ']")
	WebElement accountName;

	public HomePageUsingPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

	public String getHomePageHeaderValue() {
		return homepageHeader.getText();
	}

	public String getLoggedInAccountName() {
		return accountName.getText();
	}
}
