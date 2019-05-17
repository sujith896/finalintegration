package framework.Utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.util.ExceptionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.base.Throwables;
import com.relevantcodes.extentreports.LogStatus;

public class Utils 
{
	private static String directory = System.getProperty("user.dir");	
	private static File f;
	private static Sheet runManagerMainSheet;
	public static ArrayList<String> scenarioNames;
	public static ArrayList<String> testCaseNames;
	private static XSSFWorkbook wrkbk;
	public static String TestCaseName;
	private static HashMap testCaseData;
	
	
	
	/*
	 *get test data from  sheet 
	 */
	public static String getData(String Sheetname,String Key)
	{
		String value = null;
		Sheet generalDataSheet = wrkbk.getSheet(Sheetname);
		   int lastRow=generalDataSheet.getLastRowNum();
			for(int rowNum=0;rowNum<=lastRow;rowNum++)
			{
				if(generalDataSheet.getRow(rowNum).getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName))
				{
					XSSFRow row=(XSSFRow) generalDataSheet.getRow(0);
					int lastCol=row.getLastCellNum();
					for(int colNum=1;colNum<lastCol;colNum++ )
					{
						if(generalDataSheet.getRow(0).getCell(colNum).getStringCellValue().equalsIgnoreCase(Key))
							value = generalDataSheet.getRow(rowNum).getCell(colNum).toString();
					/*	 key=generalDataSheet.getRow(0).getCell(colNum).toString();
						System.out.println(key);
						str = generalDataSheet.getRow(rowNum).getCell(colNum).toString();
						System.out.println(str);
						testCaseData.put(key, str);*/
											
					}
					break;
				}
			}
			
			return value;		
		}
	
	/*
	 * to invoke runmanager sheet
	 */
	protected static void invoke_RunManager()
	{
	  File f = new File(directory+"//Runmanager.xlsx");
	  try
	  {
		FileInputStream fis = new FileInputStream(f);
		wrkbk = new XSSFWorkbook (fis);
		runManagerMainSheet = wrkbk.getSheet("MainSheet");
	  } 
	  catch (FileNotFoundException e) 
	  {
		e.printStackTrace();
	  }
	  catch (IOException e) 
	  {
		e.printStackTrace();
	
	  }
	}
	
	
	/*
	 * to get scenario names from main sheet in Run manager excel file
	 */
	protected static void getScenarioNames()
	{
		int lastRow=runManagerMainSheet.getLastRowNum();
		scenarioNames = new ArrayList<String>();
		for(int rowNum=0;rowNum<=lastRow;rowNum++)
		{
			if(runManagerMainSheet.getRow(rowNum).getCell(1).getStringCellValue().equalsIgnoreCase("Yes"))
			{
				String str = runManagerMainSheet.getRow(rowNum).getCell(0).getStringCellValue();
				scenarioNames.add(str);
			}
		}
	}
	
	/*
	 * Get test case names for a given scenario
	 */
	protected static void getTestCaseNames(String ScenarioName)
	{
	  //ScenarioName==> if column2(Execute).row(1 to n) is          yes then get column1(TestCaseName)
	 // update ==> testCaseNames
		File f = new File(directory+"//Runmanager.xlsx");
		FileInputStream fis;
		Sheet sheet = null;
		try {
			fis = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(ScenarioName);
		} catch (FileNotFoundException e) {
			// 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int lastRow=sheet.getLastRowNum();
		testCaseNames = new ArrayList<String>();
		for(int rowNum=0;rowNum<=lastRow;rowNum++)
		{
			if(sheet.getRow(rowNum).getCell(1).getStringCellValue().equalsIgnoreCase("Yes"))
			{
				String str = sheet.getRow(rowNum).getCell(0).getStringCellValue();
				testCaseNames.add(str);
			}
		}
	}

	/*
	 *invoke a data sheet for a given scenario 
	 */
	protected static void invokeDataSheet(String ScenarioName) 
	{
	 File f = new File(directory+"//DataTables//"+ScenarioName+".xlsx");	
	  FileInputStream fis;
		try
		{
			fis = new FileInputStream(f);
			wrkbk = new XSSFWorkbook(fis);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	

	/*
	 *to get keywords from a test case 
	 */
	protected static ArrayList<String> getKeywords(String TestCaseName)
	{
	   Sheet businessFlowSheet = wrkbk.getSheet("BusinessFlow");
	   int lastRow=businessFlowSheet.getLastRowNum();
	   ArrayList<String> keywords = new ArrayList<String>();
		for(int rowNum=0;rowNum<=lastRow;rowNum++)
		{
			if(businessFlowSheet.getRow(rowNum).getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName))
			{
				XSSFRow row=(XSSFRow) businessFlowSheet.getRow(rowNum);
				int lastCol=row.getLastCellNum();
				for(int colNum=1;colNum<lastCol;colNum++ )
				{
					String str = businessFlowSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
					keywords.add(str);
				}
				break;
			}
		}
	   return keywords; 
	}
	/*
	 * Fetch and invoke the respective keywords from business components folder
	 */
	
	protected static void fetchAndInvokeKeyWord(ArrayList<String> keywords)   
	{
		boolean flag=false;
		
		//Target BusinessComponents folder and get the list of files present on BusinessComponents folder
		File f = new File("C:\\Users\\sujith reddy\\git\\finalintegration\\maven\\src\\main\\java\\BusinessComponents");//directory+ "//BusinessComponents"
		String[] filelist = f.list();
		
		//in an arraylist variable add all .classfiles
		ArrayList<String> classfiles = new ArrayList<String>();
		for(String files:filelist)
		{
			if(files.contains(".class"))
			{
				classfiles.add(files);
			}
		}
		
		//Create an object class array with the same size of arraylist
		Object[] supcomobj = new Object[classfiles.size()];
	
		//Create object for each business component class file
		int objlen=0;
		Iterator itr = classfiles.iterator();
			while(itr.hasNext())
			{
				String file =(String) itr.next();
				String ClassName = "BusinessComponents."+file.split("\\.")[0]; 
				Class<?> buscomClass = null;
				try 
				{
					buscomClass = Class.forName(ClassName);
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				// convert string classname to class
				try 
				{
					supcomobj[objlen] = buscomClass.newInstance();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				objlen++;
			}
        
        //for each method in to invoke 
        for(String method: keywords)
	        {
        		//searching in each class object
	        	for(int i=0; i<supcomobj.length;i++)
		        	{
		        		Method[] methods = supcomobj[i].getClass().getDeclaredMethods();
		        			//searching every method in class
		        			for(Method methodsinbuscom:methods)
				        		{
				        			String metname = methodsinbuscom.getName();
				        			if(metname.equals(method))
				        			{
				        				Method setNameMethod = null;
										try 
										{
											setNameMethod = supcomobj[i].getClass().getMethod(metname);
										} 
										catch (Exception e)
										{
											Reporter.updatelog(e, LogStatus.FAIL);
										}
											try 
											{
												Reporter.updatelog("\""+metname+"\" method invoked", LogStatus.INFO);
												setNameMethod.invoke(supcomobj[i]);
												Reporter.updatelog("\""+metname+"\" method Completed", LogStatus.INFO);
											} 
											catch (Exception e) 
											{
												
												Reporter.updatelog(e, LogStatus.FAIL);
											}
											catch (Error e) 
											{
												Reporter.updatelog(e, LogStatus.FAIL);
											
											}			                    	
				                    	flag=true;
				                    	break;
				        			}
				        		}
		        		if(flag==true)
			        		{
			        			flag=false;
			        			break;
			        		}
		        		//for last class object if method was not found the return error message
		        		if(i==(supcomobj.length-1))
		        			{
		        			Reporter.updatelog("Method not found error", LogStatus.FAIL);	
		        			}
		        	}
	        }
	}
	
}

