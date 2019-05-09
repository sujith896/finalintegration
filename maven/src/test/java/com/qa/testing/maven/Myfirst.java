package com.qa.testing.maven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Myfirst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("my first program");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujith reddy\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://demo.automationtesting.in/");
		System.out.println(driver.getTitle());

	}

}
