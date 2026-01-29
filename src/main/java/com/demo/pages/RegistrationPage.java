package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.action.ActionClass;
import com.demo.base.BaseClass;

public class RegistrationPage {
	private ActionClass actionDriver;

	public RegistrationPage(WebDriver driver) {
		actionDriver = BaseClass.getActionDriver();
		System.out.println("LoginPage .....");
	}

	By registerPageHeader = By.xpath("//h1[contains(text(), 'Register')]");

	public boolean validateRegistrationPageOpen(String expectedText) {
		return actionDriver.compareText(registerPageHeader, expectedText);
	}

}
