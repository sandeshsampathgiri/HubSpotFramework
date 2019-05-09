package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;

public class LoginPageUsingPageFactory extends BasePage {

	WebDriver driver;

	// 1. Define page objects with the help of PageFactory OR by using By locator
	// 2. Page actions/methods of the feature

	// Step 1: Define page objects using Page Objects

	@FindBy(id = "username")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "loginBtn")
	WebElement loginButton;

	@FindBy(linkText = "Sign up")
	WebElement signUpLink;

	// Step 2: Create a constructor of the page class and initialize all the page
	// objects with the driver

	public LoginPageUsingPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	//Step 3: Define your actions/methods
	public String getLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean verifySignUpLink()
	{
		return signUpLink.isDisplayed();
	}

	public HomePageUsingPageFactory doLogin(String un, String pwd)
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		return new HomePageUsingPageFactory(driver);
	}
}
