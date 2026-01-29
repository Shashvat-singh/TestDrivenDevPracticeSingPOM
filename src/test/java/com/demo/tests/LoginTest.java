package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.demo.base.BaseClass;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.utility.DataProviders;
import com.demo.utility.ExtentManager;

public class LoginTest extends BaseClass {
	private HomePage homePage;
	private LoginPage loginPage;

	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(getDriver());
		loginPage = new LoginPage(getDriver());
	}

	@Test
	public void validLandingOnLoginTest() {
		ExtentManager.startTest("validLandingOnLoginTest");
		homePage.moveToLoginPage();
		ExtentManager.logStep("Navigated HomePage to LoginPage");
		Assert.assertTrue(loginPage.validateLoginPageOpen("Welcome, Please Sign In!"));
	}

	@Test(dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
	public void inValidLandingOnLoginTest(String username, String password) {
		ExtentManager.startTest("inValidLandingOnLoginTest");
		homePage.moveToLoginPage();
		ExtentManager.logStep("Navigated HomePage to LoginPage");
		System.out.println(username);
		System.out.println(password);
		Assert.assertTrue(loginPage.validateLoginPageOpen("Welcome"));
		
	}
	
	@Test
	public void inValidLandingOnLoginTestSS() {
		ExtentManager.startTest("inValidLandingOnLoginTestSS");
		homePage.moveToLoginPage();
		ExtentManager.takeScreenshot(getDriver(), "ShashvatSS");
		//Assert.assertTrue(loginPage.validateLoginPageOpen("Welcome"));
	}

}
