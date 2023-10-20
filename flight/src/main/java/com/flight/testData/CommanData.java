package com.flight.testData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class CommanData {

public static Properties properties;
	
	public CommanData() {
		properties = new Properties();
		File propFile = new File("src/main/java/com/flight/resources/config.properties");
		try {
			FileInputStream file = new FileInputStream(propFile);
			properties.load(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@BeforeTest
	@Parameters({"browser"})
	public void setUpBrowser(@Optional String browser) {
		properties.setProperty("browser", browser);
	}
}
