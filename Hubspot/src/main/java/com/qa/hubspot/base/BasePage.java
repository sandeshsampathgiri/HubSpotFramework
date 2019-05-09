package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.qa.hubspot.commons.Constants;

public class BasePage {

	WebDriver driver;
	Properties prop;

	public WebDriver initialize_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("start-maximized");
			options.setAcceptInsecureCerts(true);

			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		}

		else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\chromedriver_win32\\chromedriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("start-maximized");
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		} else if (browserName.equalsIgnoreCase("Internet Explorer")) {
			System.setProperty("webdriver.ie.driver", "E:\\chromedriver_win32\\chromedriver.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			/*
			 * options.a options.addArguments("--incognito");
			 * options.addArguments("--disable-notifications");
			 * options.addArguments("--disable-infobars");
			 * options.addArguments("start-maximized");
			 * options.setAcceptInsecureCerts(true);
			 * 
			 */
			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		} else {
			System.out.println("No browser is defined");
		}
		return driver;

	}

	public Properties initialize_properties() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Lenovo\\git\\HubSpotFramework\\Hubspot\\src\\main\\java\\com\\qa\\hubspot\\configuration\\config.properties");

			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}
}