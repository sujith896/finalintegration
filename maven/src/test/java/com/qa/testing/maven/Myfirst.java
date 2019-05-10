package com.qa.testing.maven;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Myfirst {
	WebDriver driver;
	@BeforeTest
	public void m1(){
		// TODO Auto-generated method stub
		System.out.println("my first program");
		System.setProperty("webdriver.chrome.driver", "D:\\EclipseMavenWorkSpace\\maven\\chromedriver_win32 (2)\\chromedriver.exe");
		
		driver=new ChromeDriver();
		driver.get("http://demo.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//*[text()='Skip Sign In']")).click();

	}
	
	@Test
	public void m2(String FirstName,String LastName,String Address,String EmailAddress,String Phone,String Gender,
			String Hobbies,String Languages,String Skills,String Country,String SelectCountry,String Year,String Month,
			String Day,String Password,String ConformPassword)
	{
		driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//*[@placeholder='Last Name'")).sendKeys(LastName);
		driver.findElement(By.xpath("//*[@ng-model='Adress']")).sendKeys(Address);
		driver.findElement(By.xpath("//*[@ng-model='EmailAdress']")).sendKeys(EmailAddress);
		driver.findElement(By.xpath("//*[@ng-model='Phone']")).sendKeys(Phone);
		
		if(Gender.toLowerCase()=="male")
		{
			driver.findElement(By.xpath("//*[@value='Male']")).click();
		}
		else
		{
			driver.findElement(By.xpath("//*[@value='FeMale']")).click();	
		}
		
		if(Hobbies.toLowerCase()=="cricket")
		{
			driver.findElement(By.xpath("//*[@value='Cricket']")).click();
		}
		else if(Hobbies.toLowerCase()=="movies")
		{
			driver.findElement(By.xpath("//*[@value='Movies']")).click();	
		}
		else
		{
			driver.findElement(By.xpath("//*[@value='Hockey']")).click();
		}
		
		
		List<WebElement> ele=driver.findElements(By.xpath("//*[@id='basicBootstrapForm']/div[7]/div/multi-select/div[2]/ul/li[2]/a"));
		for(WebElement ele1:ele)
		{
			if(ele1.getText().toLowerCase().equalsIgnoreCase(Languages))
			{
				ele1.click();
			}
		}
		Select s=new Select(driver.findElement(By.xpath("//*[@id='skills']")));
		s.deselectByVisibleText(Skills);
		
		Select s1=new Select(driver.findElement(By.xpath("//*[@id='countries']")));
		s1.deselectByVisibleText(Country);
		
		List<WebElement> ele2=driver.findElements(By.xpath("//*[@id='select2-country-results']/li"));
		
		for(int i=0;i<ele2.size();i++)
		{
			if(ele.get(i).getText().equalsIgnoreCase(SelectCountry))
			{
				ele.get(i).click();
			}
		}
	
		
		
		Select s3=new Select(driver.findElement(By.xpath("//*[@placeholder='Year']")));
		s3.deselectByVisibleText(Year);
		
		Select s4=new Select(driver.findElement(By.xpath("//*[@placeholder='Month']")));
		s4.deselectByVisibleText(Month);
		
		Select s5=new Select(driver.findElement(By.xpath("//*[@placeholder='Day']")));
		s5.deselectByVisibleText(Day);
		
		driver.findElement(By.xpath("//*[@id='firstpassword']")).sendKeys(Password);
		driver.findElement(By.xpath("//*[@id='secondpassword']")).sendKeys(ConformPassword);
		driver.findElement(By.xpath("//*[@id='submitbtn']")).click();
		
		
		
	}

	@AfterTest
	public void m3()
	{
		driver.quit();
		
	}
}
