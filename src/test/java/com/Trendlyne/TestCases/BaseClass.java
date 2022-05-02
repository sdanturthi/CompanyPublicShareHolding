package com.Trendlyne.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.Trendlyne.TestUtilities.ReadConfig;

public class BaseClass {
	WebDriver driver;
	ReadConfig rc =  new ReadConfig();	

	
	@BeforeClass
	public void Setup() {

		String chromeDriverPath = rc.getChromeDriverPath();
		String chromePath = System.getProperty("user.dir") + chromeDriverPath;
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void Teardown() {
		//driver.quit();
	}
	

}
