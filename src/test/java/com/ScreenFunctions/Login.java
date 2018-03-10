package com.ScreenFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.Runnerclass.Runnerclass;

public class Login extends Runnerclass
{
	@FindBy(how=How.XPATH,using="//input[@id='txtUsername']")
	public static WebElement Edt_UserName;
	
	@FindBy(how=How.NAME,using="txtPassword")
	public static WebElement Edt_Password;
	
	@FindBy(how=How.ID,using="btnLogin")
	public static WebElement Btn_Login;
	
	@FindBy(how=How.ID,using="welcome")
	public static WebElement Lnk_Welcome;
	
	@FindBy(how=How.XPATH,using="//a[text()='Logout']")
	public static WebElement Lnk_Logout;
		

public  boolean Login(String UserName, String Password)
{
	boolean status = true;
	
	try
	{
		Edt_UserName.sendKeys(UserName);
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
		status= false;
	}
	
	if(status)
	{
		try
		{
			Edt_Password.sendKeys(Password);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			status= false;
		}
		
		if(status)
		{
			try
			{
				Btn_Login.click();
				//Thread.sleep(3000);
				
				waitForElement(Lnk_Welcome);
				Lnk_Welcome.click();
				
				waitForElement(Lnk_Logout);
				Lnk_Logout.click();
				
				waitForElement(Edt_UserName);
				//test.log(logStatus, "Login is Sucessfull"+test.addBase64ScreenShot(base64));
				logEvent("pass","Login is sucessfull");
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("Button not found");
				status= false;
			}
			
		}
		
		else
		{
			System.out.println("Password is not found");
		}
	}
	else
	{
		System.out.println("UserName is not found");
	}
	
	return status;
}



}
	