package com.qa.testing.maven;

import java.util.ArrayList;

import com.excel.utility.Xls_Reader;

public class Utils
{
	static Xls_Reader reader;
	
	
	public static ArrayList<Object[]> getDataFromExcel()
	{
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		
		try
		{
			reader=new Xls_Reader("D:\\EclipseMavenWorkSpace\\maven\\RunManager.xlsx");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		for(int rowNum=2;rowNum<reader.getRowCount("RegTestData");rowNum++)
		{
			String FirstName=reader.getCellData("RegTestData", "FirstName", rowNum);
			String LastName=reader.getCellData("RegTestData", "LastName", rowNum);
			String Address=reader.getCellData("RegTestData", "Address", rowNum);
			String EmailAddress=reader.getCellData("RegTestData", "EmailAddress", rowNum);
			String Phone=reader.getCellData("RegTestData", "Phone", rowNum);
			String Gender=reader.getCellData("RegTestData", "Gender", rowNum);
			String Hobbies=reader.getCellData("RegTestData", "Hobbies", rowNum);
			String Languages=reader.getCellData("RegTestData", "Languages", rowNum);
			String Skills=reader.getCellData("RegTestData", "Skills", rowNum);
			String Country=reader.getCellData("RegTestData", "Country", rowNum);
			String SelectCountry=reader.getCellData("RegTestData", "SelectCountry", rowNum);
			String Year=reader.getCellData("RegTestData", "Year", rowNum);
			String Month=reader.getCellData("RegTestData", "Month", rowNum);
			String Day=reader.getCellData("RegTestData", "Day", rowNum);
			String Password=reader.getCellData("RegTestData", "Password", rowNum);
			String ConformPassword=reader.getCellData("RegTestData", "ConformPassword", rowNum);
			
			Object obj[]= {FirstName,LastName,Address,EmailAddress,Phone,Gender,Hobbies,Languages,Skills,Country,SelectCountry,Year,Month,Day,Password,ConformPassword};
	        myData.add(obj);
	          
		}
		return myData;
		
	}
	
	
	
	
	
	
}