package com.vTiger.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vTiger.common.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public Map<String,Map<String,String>> td;
	public Properties prop;
	
	@BeforeSuite
	public void initiation() throws IOException
	{
		prop= readPropeties();
		td= readTestData(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx","Sheet1");
		createExtentReport();
		launchapp();
	}
	
	
	
	@AfterSuite
	public void tierdown()
	{
		driver.quit();
	}
	
	public void launchapp()
	{
		if (prop.getProperty("Browser").equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		}
		else if(prop.getProperty("Browser").equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		else if(prop.getProperty("Browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(prop.getProperty("AppUrl"));
		int tm= Integer.parseInt(prop.getProperty("TimeOut"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(tm));
	}
	
	public void createExtentReport()
	{
		Date d = new Date();
		DateFormat ft= new SimpleDateFormat("ddmmyyyyhhmmss");
		String filename = ft.format(d);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vTiger/reports/ExtentReport"+filename+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	public Map<String,Map<String,String>> readTestData(String Path, String Sheet)
	{
		Xls_Reader xr= new Xls_Reader(Path);
		int rows = 	xr.getRowCount(Sheet);
		int colms = xr.getColumnCount(Sheet);
		Map<String,Map<String,String>> td = new HashMap<String,Map<String,String>>();
		for(int i=2; i<=rows; i++)
		{
			Map<String,String> rdt = new HashMap<String, String>();
			for(int j=1; j<=colms; j++)
			{
				String key = xr.getCellData(Sheet, j, 1).trim();
				String val= xr.getCellData(Sheet, j, i).trim();
				rdt.put(key, val);	
			}
			
			td.put(xr.getCellData(Sheet, 0, i), rdt);
		}
		return td;
	}
	
	public Properties readPropeties() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Config/settings.properties");
		prop.load(fis);
		return prop;
		
		
	}

}
