package com.Testcases;

import java.util.List;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.Runnerclass.Runnerclass;
import com.ScreenFunctions.Login;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC_01_US101_Login extends Runnerclass
{

	@Given("Launch the firefox browser")
	public static void LaunchBrowser()
	{
		initializeReport("Tc_01_US101_Login");
		
		startTest_Report();
		driver = new FirefoxDriver();
	}
	
	@And("Enter URL")
	public static void EnterURL()
	{
		driver.get("http://testingmasters.com/hrm");
		//String url=getEnvURL("QA");
		//System.out.println(url);
		
		
	}
	@Then("maximize the browser")
	public static void MaximizeBrowser()
	{
		driver.manage().window().maximize();
		
	}
	//@When("username is available enter the admin credentials")
	/*public static void EnterCredentials(DataTable credentials)
	{
		List<List<String>> cred=credentials.raw();
		System.out.println(cred.size());
		
		for(int i=0;i<=cred.size()-1;i++)
		{	
		System.out.println(cred.get(i).get(0));
		System.out.println(cred.get(i).get(1));
		Login lgn = PageFactory.initElements(driver, Login.class);
		lgn.Login(cred.get(i).get(0),cred.get(i).get(1));
		}
	} */ 
	@When("username is available enter the \"([^\"]*)\" credentials")
	public static void EnterCredentials(String input)
		{
		System.out.println(input);
		Login lgn = PageFactory.initElements(driver, Login.class);
		
		int itrcount = getIterationCount("Testcases","Tc_01_US101_Login");
		
		for(int i=1;i<=itrcount-1;i++)
		{
		
		String username=getData("Testcases","Tc_01_US101_Login","Username",i);
		String password=getData("Testcases","Tc_01_US101_Login","Password",i);
		System.out.println(username);
		System.out.println(password);
		
		lgn.Login(username,password);
		}
	}
	
	
}
