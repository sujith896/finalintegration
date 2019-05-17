package framework.Utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter extends SupportedLibrary
{
	static ExtentReports extent;
    static ExtentTest test;
    static String Executiondateandtime;
    static String testcasenme;
    public static void startReport()
	{
    	Executiondateandtime=getDateandTime();
		extent = new ExtentReports(System.getProperty("user.dir") +"\\Results\\"+Executiondateandtime+"\\Report.html", true);
        extent
        .addSystemInfo("Host Name", "sujith")
        .addSystemInfo("Environment", "QA")
        .addSystemInfo("User Name", "sujith Talamanchi");
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extend-config.xml"));
	}
	
    public static void closeReport()
	{
		extent.flush();
        extent.close();
	}
	
    public static void startTestCaseReport(String TestCaseName)
	{
    	testcasenme = TestCaseName;
		test=extent.startTest(TestCaseName);
	}
    public static void endTestCaseReport()
	{
		extent.endTest(test);
	}
	
	public static void updatelog(String StepDescription, LogStatus status)
	{
		if(status== LogStatus.PASS )
		{
		 test.log(status, StepDescription);
		 test.addScreenCapture(Reporter.CaptureScreenshot(StepDescription));
		}
		else if(status== LogStatus.FAIL)
		{
			test.log(status, StepDescription,test.addScreenCapture(Reporter.CaptureScreenshot(StepDescription)));
		
		}
		else
		{
			 test.log(status, StepDescription);
		}
	}
	public static void updatelog(Throwable throwable, LogStatus status)
	{
		test.log(status, throwable);
		 test.addScreenCapture(Reporter.CaptureScreenshot(status.toString()));
		
	}
	public static void updatelog(String StepName,String StepDescription, LogStatus status)
	{
		if(status== LogStatus.PASS )
		{
		 test.log(status,StepName, StepDescription);
		
		 test.addScreenCapture(Reporter.CaptureScreenshot(StepDescription));
		}
	}
	
	

	private static String getDateandTime()
	{
		Calendar calendar = Calendar.getInstance();
		Date datetime = calendar.getTime();
		String[] var= new String[3];
		var=datetime.toString().split(":");
		String dateandtime=  var[0]+"_"+var[1]+"_"+var[2];
		return dateandtime;
	}
	
	 public static void openReport()
	 {
	        //text file, should be opening in default text editor
	        File file = new File(System.getProperty("user.dir") +"\\Results\\"+Executiondateandtime+"\\Report.html");
	        
	        //first check if Desktop is supported by Platform or not
	        if(!Desktop.isDesktopSupported())
	        {
	            System.out.println("Desktop is not supported");
	            return;
	        }
	        
	        Desktop desktop = Desktop.getDesktop();
	        if(file.exists())
				try 
	        	{
					desktop.open(file);
				} 
	        	catch (IOException e) 
	        	{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	   }
	 
	 private static String CaptureScreenshot(String Screenshotname)
	 {
		 String screenshotpath=System.getProperty("user.dir") +"\\Results\\"+Executiondateandtime+"\\Screenshots\\"+testcasenme;
		 File f = new File(screenshotpath);		
		f.mkdir();
		 File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
		 
		 FileUtils.copyFile(src, new File(screenshotpath+"\\"+Screenshotname+".png"));
		 }
		  
		 catch (IOException e)
		  {
		   Reporter.updatelog("Unable to capture Screenshot", LogStatus.ERROR);
		  
		  }
		 return screenshotpath+"\\"+Screenshotname+".png";
	 }


}

