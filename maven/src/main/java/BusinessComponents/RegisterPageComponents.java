package BusinessComponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import framework.Utils.Reporter;
import framework.Utils.SupportedLibrary;

public class RegisterPageComponents extends SupportedLibrary {
	public void RegisterPage()
	{
		driver.findElement(By.xpath("//*[text()='Skip Sign In']")).click();
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(getData("GeneralData","FirstName"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys(getData("GeneralData","LastName"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@ng-model='Adress']")).sendKeys(getData("GeneralData","Address"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@ng-model='EmailAdress']")).sendKeys(getData("GeneralData","EmailAddress"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@ng-model='Phone']")).sendKeys(getData("GeneralData","Phone"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		
		if(getData("GeneralData","Gender").toLowerCase()=="male")
		{
			driver.findElement(By.xpath("//*[@value='Male']")).click();
			Reporter.updatelog("User Name entered", LogStatus.PASS);
		}
		else
		{
			driver.findElement(By.xpath("//*[@value='FeMale']")).click();	
			Reporter.updatelog("User Name entered", LogStatus.PASS);
		}
		
		if(getData("GeneralData","Hobbies").toLowerCase()=="cricket")
		{
			driver.findElement(By.xpath("//*[@value='Cricket']")).click();
			Reporter.updatelog("User Name entered", LogStatus.PASS);
		}
		else if(getData("GeneralData","Hobbies").toLowerCase()=="movies")
		{
			driver.findElement(By.xpath("//*[@value='Movies']")).click();
			Reporter.updatelog("User Name entered", LogStatus.PASS);
		}
		else
		{
			driver.findElement(By.xpath("//*[@value='Hockey']")).click();
			Reporter.updatelog("User Name entered", LogStatus.PASS);
		}
		
		
		List<WebElement> ele=driver.findElements(By.xpath("//*[@id='basicBootstrapForm']/div[7]/div/multi-select/div[2]/ul/li[2]/a"));
		for(WebElement ele1:ele)
		{
			if(ele1.getText().toLowerCase().equalsIgnoreCase(getData("GeneralData","Languages")))
			{
				ele1.click();
				Reporter.updatelog("User Name entered", LogStatus.PASS);
			}
		}
		Select s=new Select(driver.findElement(By.xpath("//*[@id='skills']")));
		s.deselectByVisibleText(getData("GeneralData","Skills"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		Select s1=new Select(driver.findElement(By.xpath("//*[@id='countries']")));
		s1.deselectByVisibleText(getData("GeneralData","Country"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		List<WebElement> ele2=driver.findElements(By.xpath("//*[@id='select2-country-results']/li"));
		
		for(int i=0;i<ele2.size();i++)
		{
			if(ele.get(i).getText().equalsIgnoreCase(getData("GeneralData","SelectCountry")))
			{
				ele.get(i).click();
				Reporter.updatelog("User Name entered", LogStatus.PASS);
			}
		}
	
		
		
		Select s3=new Select(driver.findElement(By.xpath("//*[@placeholder='Year']")));
		s3.deselectByVisibleText(getData("GeneralData","Year"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		Select s4=new Select(driver.findElement(By.xpath("//*[@placeholder='Month']")));
		s4.deselectByVisibleText(getData("GeneralData","Month"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		Select s5=new Select(driver.findElement(By.xpath("//*[@placeholder='Day']")));
		s5.deselectByVisibleText(getData("GeneralData","Day"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@id='firstpassword']")).sendKeys(getData("GeneralData","Password"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@id='secondpassword']")).sendKeys(getData("GeneralData","ConformPassword"));
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		driver.findElement(By.xpath("//*[@id='submitbtn']")).click();
		Reporter.updatelog("User Name entered", LogStatus.PASS);
		
		
		
	}

}
