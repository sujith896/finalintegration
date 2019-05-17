package com.qa.testing.maven;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import framework.Utils.Reporter;
import framework.Utils.SupportedLibrary;

public class Myfirst extends SupportedLibrary {
	
	
	public static void main(String[] args){
		
		System.out.println("my first program");
		loadPropertiesFile();
		startExecution();
		
}
	
	
	

	
}
