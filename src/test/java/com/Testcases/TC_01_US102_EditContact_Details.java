package com.Testcases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.Runnerclass.Runnerclass;
import com.ScreenFunctions.Navigate_Contact_Details;
import com.ScreenFunctions.applicationLaunch;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class TC_01_US102_EditContact_Details extends Runnerclass{

	@Given("^Launch the firefox browser Enter Url and login credentials$")
	public static void loginToApplication() {
		driver = new FirefoxDriver();
		driver.get("http://testingmasters.com/hrm");
		driver.manage().window().maximize();
		applicationLaunch lanapp = PageFactory.initElements(driver, applicationLaunch.class);
		
		int itrcount=getIterationCount("Testcases","TC_01_US102_EditContact_Details");
		for(int i =1; i<=itrcount-1; i++)
		{
		String username = getData("Testcases","TC_01_US102_EditContact_Details","Username",i);
		String password = getData("Testcases","TC_01_US102_EditContact_Details","Password",i);
		System.out.println(username);
		System.out.println(password);
		
		
		lanapp.launchApp(username, password);
		System.out.println("Login Credentials entered");
	}
	}
	@And("Navigate to Myinfo Contact details page")
	public static void contactDetails()
	{
		System.out.println("Contact details page - Navigation");
		Navigate_Contact_Details navcont = PageFactory.initElements(driver, Navigate_Contact_Details.class);
		navcont.navigateToContactDetails();
	}

	
}
