package Selenium.maven_jenkins;

import org.testng.annotations.DataProvider;

public class Data {
	
	

	@DataProvider
	public static Object[][] RegisterPageData() {
		return new Object[][] {
			{"sujith", "kumar", "nellore", "sujith896@gmail.com", "9052740438", "male",
				"cricket","English","AutoCad","Argentina",
				"Australia","1994","12","1","Sujith@1994","Sujith@1994"}
			
			};
	}
	

}
