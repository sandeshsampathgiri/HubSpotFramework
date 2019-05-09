package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementActions;

public class LoginPageUsingBy extends BasePage {
	
	WebDriver driver;
	ElementActions elementactions;

	// 1. Define page objects with the help of By locator
	// 2. Page actions/methods of the feature

	By username = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	public LoginPageUsingBy(WebDriver driver)
	{
		this.driver = driver;
		elementactions = new ElementActions(driver);
	}
	
	//Step 3: Define your actions/methods
		public String getLoginPageTitle()
		{
			return elementactions.getTitle();
		}
		
		public boolean verifySignUpLink()
		{
			//return signUpLink.isDisplayed();
			return elementactions.getElement(signUpLink).isDisplayed();
		}

		public  HomePageUsingBy doLogin(String un, String pwd)
		{
			//username.sendKeys(un);
			//password.sendKeys(pwd);
			//loginButton.click();
			//return new HomePageUsingPageFactory(driver);
			
			elementactions.getElement(username).sendKeys(un);
			elementactions.sendKeysElement(username, un);
			elementactions.sendKeysElement(password, pwd);
			elementactions.clickOnElement(loginBtn);
			return new HomePageUsingBy(driver);
		}

}
