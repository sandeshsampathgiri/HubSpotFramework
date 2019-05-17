package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.hubspot.commons.Constants;

public class BasePage {

	WebDriver driver;
	Properties prop;
	
	public WebDriver initialize_driver(Properties prop) {
		
		Logger log = Logger.getLogger(BasePage.class);
		
		
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome"))
		{
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path + "//exeFiles//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("start-maximized");
			options.setAcceptInsecureCerts(true);

			driver = new ChromeDriver(options);
			log.info("---Starting Google Chrome browser---");
			
			driver.manage().timeouts().implicitlyWait(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		}

		else if (browserName.equalsIgnoreCase("Firefox")) {
			
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver", path + "//exeFiles//geckodriver.exe");
			
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("start-maximized");
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(options);
			log.info("---Starting Firefox browser---");
			driver.manage().timeouts().implicitlyWait(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		} 
		   else {
			System.out.println("No browser is defined");
		}
		return driver;

	}

	public Properties initialize_properties() {

		try {
			prop = new Properties();
			String configPath = System.getProperty("user.dir");
			
			FileInputStream ip = new FileInputStream("C:\\Users\\Lenovo\\git\\HubSpotFramework\\Hubspot\\src\\main\\java\\com\\qa\\hubspot\\configuration\\config.properties");

			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}
	
	
}
