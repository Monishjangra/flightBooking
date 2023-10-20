package com.flight.stepdefinition;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.flight.testData.Comman;
import com.flight.testData.CommanData;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SearchStepDef {

	public WebDriver driver = Comman.driver;

	public Properties properties = CommanData.properties;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	@Given("we are on website page")
	public void we_are_on_website_page() {
		String expected = "QAClickJet - Flight Booking for Domestic and International, Cheap Air Tickets";
		String actualString = driver.getTitle();
//		Assert.assertTrue(actualString.equalsIgnoreCase(expected));
		Assert.assertEquals(actualString, expected);
	}

	@Then("select departure city")
	public void select_departure_city() {
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class ='search_options_menucontent']//li"));
		for (WebElement element : elements) {
			if (element.getText().contains(properties.getProperty("from"))) {
				element.click();
				break;
			}
		}
	}

	@Then("select arrival city")
	public void select_arrival_city() {
		driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		List<WebElement> elements = driver.findElements(By.xpath(
				"//div[@id = 'glsctl00_mainContent_ddl_destinationStation1_CTNR']//div[@class = 'search_options_menucontent']//li"));
		for (WebElement element : elements) {
			if (element.getText().contains(properties.getProperty("to"))) {
				element.click();
				break;
			}
		}
	}

	@Then("select date")
	public void select_date() throws InterruptedException {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
		int inputIndex = 0;
		int currentIndex = 0;
		WebElement nextElement = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']//span[text()= 'Next']"));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String currentDate = driver.findElement(By.xpath(
				"//div[@class = 'ui-datepicker-group ui-datepicker-group-first']//child::div[@class = 'ui-datepicker-title']"))
				.getText();
		String[] date = currentDate.split(" ");
		String currentMonth = date[0];
		String currentYear = date[1];
		String[] inputDate = properties.getProperty("date").split(" ");
		String inputDay = inputDate[0];
		String inputMonth = inputDate[1];
		String inputYear = inputDate[2];
		String[] totalMonth = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		for (int i = 0; i < totalMonth.length; i++) {
			if (totalMonth[i].equalsIgnoreCase(inputMonth)) {
				inputIndex = i;
			}
			if (totalMonth[i].equalsIgnoreCase(currentMonth)) {
				currentIndex = i;
			}
		}
		if (Integer.parseInt(inputYear) > Integer.parseInt(currentYear)) {
			while (!(currentMonth.equalsIgnoreCase(inputMonth) && currentYear.equalsIgnoreCase(inputYear))) {
//					wait.until(ExpectedConditions
//							.visibilityOfElementLocated(By.xpath("//*[@data-testid = 'rightArrow']")));
				jsExecutor.executeScript("arguments[0].click()", nextElement);
				currentDate = driver.findElement(By.xpath(
						"//div[@class = 'ui-datepicker-group ui-datepicker-group-first']//child::div[@class = 'ui-datepicker-title']"))
						.getText();
				date = currentDate.split(" ");
				currentMonth = date[0];
				currentYear = date[1];
			}
		} else if (Integer.parseInt(inputYear) == Integer.parseInt(currentYear)) {
			if (inputIndex > currentIndex) {
				System.out.println(inputIndex - currentIndex);
				for (int i = 0; i < inputIndex - currentIndex; i++) {
					jsExecutor.executeScript("arguments[0].click()", nextElement);
					System.out.println("line 99");
				}
			}
		}
		List<WebElement> date1 = driver.findElements(By.xpath(
				"//div[@class = 'ui-datepicker-group ui-datepicker-group-first']/table[@class = 'ui-datepicker-calendar']//td[@data-handler = 'selectDay']/a"));
		for (WebElement dateEnter : date1) {
			if (dateEnter.getText().contains(inputDay)) {
				dateEnter.findElement(By.xpath("..")).click();
				break;
			}
		}
	}

	@Then("select Number of Passengers")
	public void select_number_of_passengers() {
		int numberOfAdult = Integer.parseInt(properties.getProperty("adultPassenger"));
		int numberOfChild = Integer.parseInt(properties.getProperty("childPassenger"));
		int numberOfInfant = Integer.parseInt(properties.getProperty("infantPassenger"));
		if (numberOfAdult != 1) {
			driver.findElement(By.id("divpaxinfo")).click();
			for (int i = 1; i < numberOfAdult; i++) {
				driver.findElement(By.xpath("//div[@id = 'divAdult']//span[@id = 'hrefIncAdt']")).click();
			}
		}
		for (int i = 0; i < numberOfChild; i++) {
			driver.findElement(By.xpath("//div[@id = 'divChild']//span[@id = 'hrefIncChd']")).click();
		}
		for (int i = 0; i < numberOfInfant; i++) {
			driver.findElement(By.xpath("//div[@id = 'divInfant']//span[@id = 'hrefIncInf']")).click();
		}
		driver.findElement(By.id("btnclosepaxoption")).click();

	}

	@Then("select currency")
	public void select_currency() {
		driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")).click();
		;
		List<WebElement> currencies = driver
				.findElements(By.xpath("//select[@id= 'ctl00_mainContent_DropDownListCurrency']/option"));
		for (WebElement currency : currencies) {
			if (properties.getProperty("currency").equalsIgnoreCase(currency.getText())) {
				driver.findElement(By.xpath("//select[@id= 'ctl00_mainContent_DropDownListCurrency']/option[@value = '"
						+ properties.getProperty("currency") + "']")).click();
			}
		}
	}

	@Then("click on search button")
	public void click_on_search_button() throws InterruptedException {
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
	}

}
