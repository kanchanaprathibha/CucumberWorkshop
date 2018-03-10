package com.Runnerclass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.Utilities;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		monochrome =true,strict=true,
		glue= {"com.Testcases"},
		features = "Features",
		plugin= {"pretty","html:target/cucumber-html-report"},
		
		tags= {"@Regression"}
		
		)
public class Runnerclass extends AbstractTestNGCucumberTests
{
	public static WebDriver driver;
	public static Fillo fillo;
	public static Connection connection;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String crntclass;
	public static String resultpath;
	
	public static Recordset recordset;
	
	@BeforeSuite
	public static void loadTestData() throws FilloException
	{
		//Create a result folder on current date 
		resultpath = Utilities.createResultFolder();
		connectToExcel();
	}
	
	public static void startTest_Report()
	{
		test = extent.startTest("Test Execution Status");
	}
	
	@AfterTest
	public static void endTest()
	{
		extent.endTest(test);
		driver.quit();
		extent.flush(); //To push heap memory to File system
	}
	
	/*@AfterSuite
	public static void flushreport()
	{
		extent.flush();
		driver = new FirefoxDriver();
		driver.get("");
		driver.manage().window().maximize();
	}*/
	
	public static String takeScreenshot()
	{
		String path ="";
		try 
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		TakesScreenshot sht = (TakesScreenshot)driver;
		String print = sht.getScreenshotAs(OutputType.BASE64);
		
		return "data:image/jpg:base64,  "+print;	
	}
//===========================================================================
/*
 * Method Name: waitForElement
 * Purpose : This method is developed to maintain a constant wait across all the pages and elements
 * Input Parameters: User must send WebElement as parameters 
 * Output Parametes: NA
 * Designed By: 
 * Creation Date: 
 * Reviewed By: 
 * Comments: 
 * Modified Date:
 */
//===============================================================================	
	public static void waitForElement(WebElement element)	
	{
		for(int i=1; i<=15; i++)
		{
			//Try to perform mouse hovering action on a WebElement
			try 
			{
				//System.out.println("Wait is Executing");
				Actions act = new Actions(driver);
				act.moveToElement(element).build().perform();
				System.out.println("Wait is Completed and element found");
				//If an element is found then break the execution loop
				break;
			}
			catch(Exception e)
			{
				//If an element is not found then wait for 1 sec
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e1)
				{
					System.out.println(e1.getMessage());
				}
			}	
		}
	}
//###############################################################################
	/*Method Name :Connect to Excel
	 * 
	 * Purpose for fast retrieval of data we are converting Excel as Database
	 * 
	 * Plugin Dependency: Dependency is added in pom.xml
	 * 
	 * Input Parameter : NA
	 * 
	 * Output Parameter: NA
	 * 
	 */
//###############################################################################	
	public static void connectToExcel() throws FilloException
	{
		fillo=new Fillo();
		
		try 
		{
			String crntdir = System.getProperty("user.dir");
			connection=fillo.getConnection(crntdir+"\\Testdata\\testdata.xlsx");
			System.out.println("Connection established");
		}
		catch(FilloException fe)
		{
			fe.printStackTrace();
		}
	}
//####################################################################################
	/*Method name: getData
	 * 
	 * Purpose : Get data for a particular test case with respective to the iteration
	 * 
	 * Plugin Dependency: Dependency is added in pom.xml
	 * 
	 * Input Parameter : Test case name,Iteration
	 * 
	 * Output Parameter : String data
	 * */
//####################################################################################
	public static String getData(String sheetname, String Testcasename, String fieldname, int itr)
	{
		String data ="";
		try
		{
			String strQuery = "Select "+fieldname+" from " +sheetname+" where Tc_Name='"+Testcasename+"' and Iteration="+itr;
			
			recordset=connection.executeQuery(strQuery);
			while(recordset.next()){
			System.out.println(recordset.getField(fieldname));
			data=recordset.getField(fieldname);
			break;
			}
		}
		catch (Exception e)
		{
			System.out.println("No Records found");
		}	
	
		finally
		{
			recordset.close();
		}
		
		return data;	
	}
	
	//For no of iterations -to give no multiple inputs	
	public static int getIterationCount(String sheetname, String Testcasename)
	{
		int data =1;
		try
		{
			String strQuery = "Select "+Testcasename+" from "+sheetname;
			recordset=connection.executeQuery(strQuery);
			System.out.println(recordset.getCount());
			while(recordset.next())
			{
				data++;
			}
		}
		catch (Exception e)
		{
			System.out.println("No Records found");
		}	
		finally
		{
			recordset.close();
		}
		return data;	
	}
	public static String getEnvURL(String Env)
	{
		String data="";
		try 
		{		
			String strQuery="Select URL from CommonDetails where Environment="+Env+"and Execute='Yes'";
			recordset=connection.executeQuery(strQuery);
		 
			while(recordset.next())
			{
				System.out.println(recordset.getField(Env));
				data=recordset.getField(Env);
				break;
			}
	
		}
		catch(Exception e)
		{
			System.out.println("No URL found");
		}
		
		return data;
	}
	
	public static void initializeReport(String classname)
	{
		extent = new ExtentReports(resultpath+"\\"+classname+".html");
	}
	
	
	public static void logEvent(String status, String Descreption)
	{
		switch (status.toLowerCase())
		{
		case "pass":
			test.log(LogStatus.PASS, Descreption+test.addBase64ScreenShot(takeScreenshot()));
			break;
		
		case "fail":
			test.log(LogStatus.FAIL, Descreption+test.addBase64ScreenShot(takeScreenshot()));
			break;
		
		case "error":
			test.log(LogStatus.ERROR, Descreption+test.addBase64ScreenShot(takeScreenshot()));
			break;	
		}
	}
	
	
	
	
	
	
	
}
