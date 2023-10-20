package com.flight.testData;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Comman {

	public static WebDriver driver;
	public Properties properties = CommanData.properties;

	@Before
	public void onStart() {
		System.out.println("before");
		String browser = properties.getProperty("browser");
		if (browser.equalsIgnoreCase("Chrome")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName","chrome");
			driver = new RemoteWebDriver(capabilities);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName","MicrosoftEdge");
			options.addArguments("inprivate");
			capabilities.setCapability(EdgeOptions.CAPABILITY, options);
			driver = new RemoteWebDriver(capabilities);
		}else if (browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName","firefox");
			driver = new RemoteWebDriver(capabilities);
		}
		driver.get(properties.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@After
	public void tearDown() {
		System.out.println("after");
		driver.quit();
	}

//	@BeforeStep
//	public void beforeEach() {
//		System.out.println("before each");
//	}
//
//	@AfterStep
//	public void afterEach() {
//		System.out.println("after each");
//	}

}
