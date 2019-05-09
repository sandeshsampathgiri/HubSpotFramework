package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class HomePageUsingBy extends BasePage {

	WebDriver driver;
	
	public HomePageUsingBy(WebDriver driver)
	{
		this.driver = driver;
	}
}
