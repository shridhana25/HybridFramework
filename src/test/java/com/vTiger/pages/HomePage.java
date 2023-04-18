package com.vTiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vTiger.common.CommonActions;

public class HomePage {

	private WebDriver driver;
	public ExtentTest logger;
	public CommonActions ca;
	
	public HomePage(WebDriver driver,ExtentTest logger )
	{
		this.driver= driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
		ca= new CommonActions(driver,logger);
	}
	
	@FindBy(xpath="//a[text()='Logout']")
	WebElement logout;
	
	public void logout()
	{
		ca.click(logout, "Clicked on logout link");
		
	}
}
