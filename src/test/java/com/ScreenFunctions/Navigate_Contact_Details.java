package com.ScreenFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.Runnerclass.Runnerclass;

public class Navigate_Contact_Details extends Runnerclass{
	
	@FindBy(how=How.XPATH,using="//a[@id='menu_pim_viewMyDetails']/b")
	public static WebElement lnk_Myinfo;
	
	@FindBy(how=How.XPATH,using="//ul[@id='sidenav']/li[2]/a")
	public static WebElement lnk_contactdet;
	
	@FindBy(how=How.XPATH,using="//input[@id='btnSave']")
	public static WebElement lnk_editContact;
	
	@FindBy(how=How.XPATH,using="//input[@id='btnSave']")
	public static WebElement lnk_saveContact;

	public boolean navigateToContactDetails()
	{
		boolean status= true;
		try
		{
			lnk_Myinfo.click();
			System.out.println("Myinfo link clicked");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			status=false;
		}
		if(status)
		{
			try
			{
				lnk_contactdet.click();
				System.out.println("Contact details link clicked");
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
					//Check fields of form are disabled or not
					List<WebElement> list_Contact= driver.findElements(By.xpath("//input[@type='text']"));
					int count_contact_field = 0;
					
					for(WebElement eachTextbox: list_Contact)
					{
						if(eachTextbox.isEnabled())
						{
							System.out.println("Contact form fields are in enable mode");
							break;
						}			
						else
							count_contact_field++;
					}
					System.out.println("Contact form fields are in disable mode");
				}
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
					status=false;
				}
			}
			if(status)
			{
				try
				{
					lnk_editContact.click();
					System.out.println("Edit button clicked in Contact details");
					
					//Check for edit button changed to save button or not
					String Expected_saveValue = "Save";
					String btn_save=lnk_saveContact.getAttribute("value");
					if(btn_save.contentEquals(Expected_saveValue))
					{
						System.out.println("Yes there is Save button available");
					}
					else
					{
						System.out.println("Save not available");
					}
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
						List<WebElement> list= driver.findElements(By.xpath("//input[@type='text']"));
						System.out.println(list.size());
						
						int i = 0;
						
						for(WebElement eachTextbox: list)
						{
							if(eachTextbox.getText()!=null)
							{
								eachTextbox.clear();
								i++;
							}			
						}
						System.out.println(i +"cleared");
					}
					catch(Exception e) 
					{
						System.out.println(e.getMessage());
						status=false;
					}
				}
			}
			
			
			else
			{
				System.out.println("Edit button is not available in Contact details page");
			}
		}else
		{
			System.out.println("Contact details link is not available");
		}
		return status;	
	}
}
