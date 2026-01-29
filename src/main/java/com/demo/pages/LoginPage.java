package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.action.ActionClass;
import com.demo.base.BaseClass;

public class LoginPage {
	private ActionClass actionDriver;
	
	
	public LoginPage(WebDriver driver) {
		actionDriver = BaseClass.getActionDriver();
		System.out.println("LoginPage .....");
	}
	
	By loginPageHeader = By.xpath("//h1[contains(text(), 'Welcome, Please Sign In!')]");
	
	
	public boolean validateLoginPageOpen(String expectedText) {
		return actionDriver.compareText(loginPageHeader, expectedText);
	}
	


}
