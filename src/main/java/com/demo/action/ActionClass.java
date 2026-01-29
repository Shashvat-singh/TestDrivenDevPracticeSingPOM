package com.demo.action;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.base.BaseClass;
import com.demo.utility.ExtentManager;
import com.demo.utility.LoggerManger;

public class ActionClass {
	private WebDriver driver;
	private static final Logger logger = LoggerManger.getLogger(ActionClass.class);
	private WebDriverWait wait;
	
	public ActionClass(WebDriver driver) {
		this.driver = BaseClass.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		logger.info("ActionClass-Constructor is running.....");
	}
	
	// Method to click an element
		public void click(By by) {
			String elementDescription = getElementDescription(by);
			try {
				waitForElementToBeClickable(by);
				driver.findElement(by).click();
				ExtentManager.logStep("clicked an element: "+elementDescription);
				logger.info("clicked an element-->" + elementDescription);
			} catch (Exception e) {
				System.out.println("Unable to click element:" + e.getMessage());
				ExtentManager.logFailure(BaseClass.getDriver(), "Unable to click element:", elementDescription+"_unable to click");
				logger.error("unable to click element");
			}
		}

		// Method to enter text into an input field --
		public void enterText(By by, String value) {
			try {
				waitForElementToBeVisible(by);
				WebElement element = driver.findElement(by);
				element.clear();
				element.sendKeys(value);
				logger.info("Entered text on " + getElementDescription(by) + "-->" + value);
			} catch (Exception e) {
				logger.error("Unable to enter the value:" + e.getMessage());
			}
		}
		
		// Method to get text from an input field
		public String getText(By by) {
			try {
				waitForElementToBeVisible(by);
				return driver.findElement(by).getText();
			} catch (Exception e) {
				logger.error("Unable to get the text:" + e.getMessage());
				return "";
			}
		}
		
		
		// Method to compare Two text -- changed the return type
		public boolean compareText(By by, String expectedText) {
			try {
				waitForElementToBeVisible(by);
				String actualText = driver.findElement(by).getText();
				if (expectedText.equals(actualText)) {
					logger.info("Texts are Matching:" + actualText + " equals " + expectedText);
					ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Texts are Matching:", "Texts are Matching"+getElementDescription(by));
					return true;
				} else {
					logger.error("Texts are not Matching:" + actualText + " not equals " + expectedText);
					ExtentManager.logFailure(BaseClass.getDriver(), "Text Comparison Failed!", "Text Comparison Failed! "+actualText+ " not equals "+expectedText);
					return false;
				}
			} catch (Exception e) {
				logger.error("Unable to compare Texts:" + e.getMessage());
			}
			return false;
		}
		
		// Wait for Element to be clickable
		private void waitForElementToBeClickable(By by) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(by));
				ExtentManager.logStep("Wait to element should be clickable..."+getElementDescription(by));
			} catch (Exception e) {
				logger.error("element is not clickable: " + e.getMessage());
				ExtentManager.logFailure(BaseClass.getDriver(),"Element is not clickable","Element is not clickable  :"+getElementDescription(by));
			}
		}
		
		// Wait for Element to be Visible
		private void waitForElementToBeVisible(By by) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				ExtentManager.logStep("Wait to element should be visible...");
			} catch (Exception e) {
				logger.error("Element is not visible:" + e.getMessage());
				ExtentManager.logFailure(BaseClass.getDriver(),"Element is not visible:","Element is not visible:..."+getElementDescription(by));
				
			}
		}
		
		// Method to get the description of an element using By locator
		public String getElementDescription(By locator) {
			// Check for null driver or locator to avoid NullPointerException
			if (driver == null) {
				return "Driver is not initialized.";
			}
			if (locator == null) {
				return "Locator is null.";
			}

			try {
				// Find the element using the locator
				WebElement element = driver.findElement(locator);

				// Get element attributes
				String name = element.getDomProperty("name");
				String id = element.getDomProperty("id");
				String text = element.getText();
				String className = element.getDomProperty("class");
				String placeholder = element.getDomProperty("placeholder");

				// Return a description based on available attributes
				if (isNotEmpty(name)) {
					return "Element with name: " + name;
				} else if (isNotEmpty(id)) {
					return "Element with ID: " + id;
				} else if (isNotEmpty(text)) {
					return "Element with text: "+text;
				} else if (isNotEmpty(className)) {
					return "Element with class: " + className;
				} else if (isNotEmpty(placeholder)) {
					return "Element with placeholder: " + placeholder;
				} else {
					return "Element located using: " + locator.toString();
				}
			} catch (Exception e) {
				// Log exception for debugging
				e.printStackTrace(); // Replace with a logger in a real-world scenario
				return "Unable to describe element due to error: " + e.getMessage();
			}
		}

		// Utility method to check if a string is not null or empty
		private boolean isNotEmpty(String value) {
			return value != null && !value.isEmpty();
		}
	
	
	

}
