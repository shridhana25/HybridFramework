package com.vTiger.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommonActions {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest logger;
	
	public CommonActions(WebDriver driver,  ExtentTest logger)
	{
		this.driver=driver;
		this.logger= logger;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public void TypeText(WebElement elm, String val, String msg)
	{
		
		try {
		wait.until(ExpectedConditions.visibilityOfAllElements(elm));
		elm.clear();
		elm.sendKeys(val);
		logger.pass(msg+"          "+"<a href='"+getScreenShot()+"'><i class='material-icons'>Screenshot</i></a>");
		}
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        	logger.fail("text did not entered due to error"+e.getMessage());
        }
	}
	
	public void click(WebElement elm, String msg)
	{
		try {
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		elm.click();
		logger.pass(msg+"         "+"<a href='"+getScreenShot()+"'><i class='material-icons'>Screenshot</i></a>");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("unable to click due to error"+e.getMessage());
		}
	}
	
	public void SelectText(WebElement elm, String val, String msg)
	{
		try {
		wait.until(ExpectedConditions.visibilityOf(elm));
		Select sel = new Select(elm);
		sel.selectByVisibleText(val);
		logger.pass(msg+"            "+"<a href='"+getScreenShot()+"'><i class='material-icons'>Screenshot</i></a>");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("unable to select due to error"+e.getMessage());
		}
	}
	
	public String getScreenShot() throws IOException
	{
		Date d = new Date();
		DateFormat ft= new SimpleDateFormat("ddmmyyyyhhmmss");
		String filename = ft.format(d);
		String path = System.getProperty("user.dir")+"/src/test/java/com/vTiger/reports/screenshot"+filename+".png";
		TakesScreenshot ts = ((TakesScreenshot)driver);
		File srcfile = ts.getScreenshotAs(OutputType.FILE);
		File desfile = new File(path);
		FileUtils.copyFile(srcfile, desfile);
		return path;
	}
	
}
