package com.comcast.crem.basetest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.genericUtilities.DatabaseUtility;
import com.genericUtilities.ExcelUtility;
import com.genericUtilities.IConstantPath;
import com.genericUtilities.JavaUtility;
import com.genericUtilities.PropertiesFileUtility;
import com.genericUtilities.WebDriverUtility;

import implementaion.objectrepositoryutility.HomePage;
import implementaion.objectrepositoryutility.LoginPage;
import implementaion.objectrepositoryutility.PageObjectManager;

public class BaseClass {
	
	public WebDriverUtility dLib;
	public PropertiesFileUtility pLib;
	public ExcelUtility eLib;
	public JavaUtility jLib;
	public WebDriver driver;
	public DatabaseUtility dbLib;
	/**
	--> I'm creating the static webdriver why baseclass is a configuration annotation class we cannot
	    create object for this class. So in ListImpClass we can pass the driver(non-static) reference variable
	    So I can create a static(sdriver) variable I can use any where.
	--> But we can use to parallel execution for static variable. So i can create non static(driver) variable 
	*/
	public static WebDriver sdriver;
	public static JavaUtility sjutil;
	
	public PageObjectManager page;
	
	public LoginPage login;
	public HomePage home;
	
	
	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void suiteConfig() {
		dbLib = new DatabaseUtility();
		dbLib.getDBConnection("jdbc:mysql://localhost:3306/advsel", "root", "root");
		
	}
	
	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void classConfig(String browser) {
		dLib = new WebDriverUtility();
		pLib = new PropertiesFileUtility();
		eLib = new ExcelUtility();
		jLib = new JavaUtility();
		
		pLib.propertiesInit(IConstantPath.propertiesPath);
		
	
		String TIMEOUTS = pLib.getDataFromPropertiesFile("timeouts");
		long time = Long.parseLong(TIMEOUTS);
		
		driver = dLib.launchBrowser(browser);
		dLib.maximizeWindow();
		dLib.waitForBrowserLoad(time);
		
		sdriver = driver;
		sjutil = jLib;
		
		
	}
	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void methodConfig() {
		
		eLib.excelInit(IConstantPath.excelPath);
		
		String URL = pLib.getDataFromPropertiesFile("url");
		String USERNAME = pLib.getDataFromPropertiesFile("username");
		String PASSWORD = pLib.getDataFromPropertiesFile("password");
		
		page = new PageObjectManager(driver);
		login = page.getLogin();
		home = page.getHome();
		dLib.navigateApp(URL);
		
		login.loginToVtiger(USERNAME, PASSWORD);
		
		
		
	}
	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void methodTearDown() {
		
		home.signOutOfVtiger(dLib);
		eLib.saveToExcel();
		eLib.closeExcel();
		
	}
	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void classTearDown() {
		dLib.closeAllWindows();
	}
	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void suiteTearDown() {
		dbLib.closeDBconnection();
	}

}