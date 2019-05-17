package framework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.LogStatus;

import framework.Utils.Utils;

public class SupportedLibrary extends  Utils
{
	public static Properties Repository = new Properties();
	private static String directory = System.getProperty("user.dir");	
	private static FileInputStream fis;
	private static File f;
	public static WebDriver driver; 

	/*
	 * to load all the properties files in PageLocatorsFolder
	 */
	public static void loadPropertiesFile() 
	{	
		//Target PageLOcators folder and get the list of files present on PageLocators folder
		f = new File(directory);
		
			try
				{
					fis = new FileInputStream(directory+ "//GlobalSettings.properties");
					Repository.load(fis);
				}
			catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
			catch(IOException e)
				{
					e.printStackTrace();
				}
		}

	
	/*
	 * Launch Browser
	 */
	public static void launchBrowser()
	{
		String browser = Repository.getProperty("BrowserName");
		if(browser.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
			Reporter.updatelog("FireFOx Browser Launch Successfull", LogStatus.INFO);
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", Repository.getProperty("ChromeDriverpath"));
			driver = new ChromeDriver();
			Reporter.updatelog("Chrome Browser Launch Successfull", LogStatus.INFO);
		}
		else if(browser.equalsIgnoreCase("InternetExplorer"))
		{
			System.setProperty("webdriver.ie.driver",Repository.getProperty("IEBDriverpath"));
			driver = new InternetExplorerDriver();
			Reporter.updatelog("IE Browser Launch Successfull", LogStatus.INFO);
		}
		else
		{
			Reporter.updatelog("Enter Valid browser Name", LogStatus.ERROR);
		}
		driver.manage().window().maximize();
	}
	/*
	 * Launch Application
	 */
	public static void launchApplication()
	{
		String applicationURL=Repository.getProperty("ApplicationURL");
		if(applicationURL != null)
		{
			driver.navigate().to(applicationURL);
			Reporter.updatelog("Application Launch Successfull", LogStatus.INFO);
		}
	}
	/*
	 * Quit Browser abnormal termination
	 */
	public static void quitBrowser()
	{
		driver.quit();
		Reporter.updatelog("Application is CLosed Successfully", LogStatus.INFO);
	}
	/*
	 * Start executioon
	 */
	public static void startExecution() 
	{
		Reporter.startReport();
		invoke_RunManager();
		getScenarioNames();
		try
		{
		for(String secnme:scenarioNames)
		 {
			invokeDataSheet(secnme);
			getTestCaseNames(secnme);
		    for(String testcsenme:testCaseNames)
		    {    
		    	Reporter.startTestCaseReport(testcsenme);
		    	//launch browser
				launchBrowser();
				//launch Application
				launchApplication();
		    	TestCaseName=testcsenme;
		    	
		    	ArrayList<String> keywords = getKeywords(testcsenme);
		    	try
		    	{
		    		fetchAndInvokeKeyWord(keywords);
		    	}
		    	catch(Exception e)
		    	{
		    		
		    		Reporter.updatelog(e, LogStatus.FAIL);
		    		continue;
		    	}
		    	catch(Error e)
		    	{
		    		Reporter.updatelog(e, LogStatus.FAIL);
		    		continue;
		    	}
		    	finally
		    	{
		    		quitBrowser();
			    	Reporter.endTestCaseReport();		    		
		    	}
		    	
		    }	
		 }
		
		}
		finally
		{
			Reporter.closeReport();
			Reporter.openReport();	
		}
	}
	
	
}

