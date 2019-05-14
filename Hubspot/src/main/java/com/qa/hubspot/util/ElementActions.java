package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.commons.Constants;

public class ElementActions extends BasePage {

	WebDriver driver;
	WebElement element = null;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By selector) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector));

			element = driver.findElement(selector);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public void sendKeysElement(By selector, String value) {
		getElement(selector).sendKeys(value);
	}

	public void clickOnElement(By selector) {
		getElement(selector).click();

	}

	public String getText(By selector) {
		return getElement(selector).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	
	public int getContactsCount(By selector)
	{
		int count = driver.findElements(selector).size();
		return count;
	}

}
