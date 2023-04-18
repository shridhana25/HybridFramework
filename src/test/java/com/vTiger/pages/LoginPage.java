package com.vTiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vTiger.common.CommonActions;

public class LoginPage {

	// page factory
	private WebDriver driver;
	public CommonActions ca;
	public ExtentTest logger;
	
	public LoginPage(WebDriver driver,ExtentTest logger)
	{
		
		this.driver= driver;
		this.logger= logger;
		PageFactory.initElements(driver, this);
		 ca = new CommonActions(driver, logger);
		
	}
	
	@FindBy(xpath="//input[@name='user_name']")
	WebElement userid;
	
	@FindBy(xpath="//input[@name='user_password']")
	WebElement pwd;
	
	@FindBy(xpath="//input[@name='Login']")
	WebElement login;
	
	public void login(String uid, String pass)
	{
		ca.TypeText(userid, uid, uid+"= has been entered into username field");
		ca.TypeText(pwd, pass, pass+"= has been entered into password field");
		ca.click(login, "Login done successfully");
		
	}
	/* page object model
	public WebDriver driver;
	
	By userid = By.xpath("//input[@name='user_name']");
	By pwd = By.xpath("//input[@name='user_password']");
	By login = By.xpath("//input[@name='Login']");
	
	public void login()
	{
		driver.findElement(userid).clear();
		driver.findElement(userid).sendKeys("admin");
		driver.findElement(pwd).clear();
		driver.findElement(pwd).sendKeys("admin");
		driver.findElement(login).click();
	}
	
	public void invalidlogin()
	{
		
		driver.findElement(userid).sendKeys("admin12");
		driver.findElement(pwd).sendKeys("admin56");
		driver.findElement(login).click();
	}
*/
}
