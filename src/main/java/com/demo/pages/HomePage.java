package com.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.demo.action.ActionClass;
import com.demo.base.BaseClass;

public class HomePage {
	ActionClass actionDriver;
	
	public HomePage(WebDriver driver) {
		actionDriver = BaseClass.getActionDriver();
		System.out.println("HomePage is running......");
	}
	
	By loginBtnAtHomePage = By.xpath("//a[text()='Log in']");
	By registrationBtnAtHomePage = By.xpath("//a[text()='Register']");
	
	public void moveToLoginPage() {
		actionDriver.click(loginBtnAtHomePage);
	}
	
	public void moveToRegistrationPage() {
		actionDriver.click(registrationBtnAtHomePage);
	}

}
