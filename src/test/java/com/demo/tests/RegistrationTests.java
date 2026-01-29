package com.demo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.demo.base.BaseClass;
import com.demo.pages.HomePage;
import com.demo.pages.RegistrationPage;
import com.demo.utility.ExtentManager;

public class RegistrationTests extends BaseClass{
private HomePage homePage;
private RegistrationPage registrationPage;
	
	@BeforeMethod
	public void setUp() {
		homePage = new HomePage(getDriver());
		registrationPage = new RegistrationPage(getDriver());
	}
	
	@Test
	public void validLandingOnRegisterPageTest() {
		ExtentManager.startTest("validLandingOnRegisterPageTest");
		homePage.moveToRegistrationPage();
		ExtentManager.logStep("Moved on the Registration Page");
		Assert.assertTrue(registrationPage.validateRegistrationPageOpen("Register"));
	}
	
	@Test
	public void inValidLandingOnRegisterPageTest() {
		ExtentManager.startTest("inValidLandingOnRegisterPageTest");
		homePage.moveToRegistrationPage();
		ExtentManager.logStep("Moved on the Registration Page");
		Assert.assertTrue(registrationPage.validateRegistrationPageOpen("Registe"));
	}
}
