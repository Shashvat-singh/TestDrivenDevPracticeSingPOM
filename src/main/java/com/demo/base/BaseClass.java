package com.demo.base;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import com.demo.utility.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.demo.action.ActionClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected static Properties prop;
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected static ThreadLocal<ActionClass> actionDriver = new ThreadLocal<>() ;
	private static final Logger logger = LoggerManger.getLogger(BaseClass.class);
	
	@BeforeSuite
	public void loadConfigPropFile() throws IOException{
		prop = new Properties();
		File file = new File("C:\\Users\\shash\\eclipse-workspace\\TestDrivenDevPracticeSingPOM\\src\\main\\resources\\config.properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		logger.info("Propertires file loaded.....");
		
		ExtentManager.getReporter();
	}
	
	@BeforeMethod
	public synchronized void runBrowserUrl() {
//		WebDriverManager.chromiumdriver().setup();
//		driver.set(new ChromeDriver());
//		ExtentManager.registerDriver(getDriver());
		
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver());
		ExtentManager.registerDriver(getDriver());
		
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		actionDriver.set(new ActionClass(getDriver()));
		System.out.println("Id of Thread"+Thread.currentThread().getId());
		
		
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static ActionClass getActionDriver() {
		return actionDriver.get();
	}
	
	@AfterMethod
	public void tesrDown() {
		if(getDriver() != null) {
			getDriver().quit();
		}
		driver.remove();
		actionDriver.remove();
		ExtentManager.endTest();
	}

}
