package com.ScreenFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.Runnerclass.Runnerclass;

public class applicationLaunch extends Runnerclass{
	@FindBy(how=How.XPATH,using="//input[@id='txtUsername']")
	public static WebElement Edi_Username;
	
	@FindBy(how=How.XPATH,using="//input[@name='txtPassword']")
	public static WebElement Edi_Password;
	
	@FindBy(how=How.XPATH,using="//input[@class='button']")
	public static WebElement Btn_Login;
	
	public boolean launchApp(String username,String pwd)
	{
		boolean status= true;
		try
		{
			Edi_Username.sendKeys(username);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			status=false;
		}
		
		if(status)
		{
			try
			{
				Edi_Password.sendKeys(pwd);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				status=false;
			}
			if(status)
			{
				try
				{
					Btn_Login.click();
					waitForElement(Edi_Username);
					
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					status=false;
				}
			}else 
			{
				System.out.println("Login button not available");
			}
			
		}else
		{
			System.out.println("Password field is not available");
		}
		return status;	
	}
	

	
}
