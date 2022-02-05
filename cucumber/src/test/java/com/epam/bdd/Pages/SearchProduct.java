package com.epam.bdd.Pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchProduct {

    WebDriver driver;
    
	  public SearchProduct(WebDriver driver){

	        this.driver = driver;
	        //This initElements method will create all WebElements
	        PageFactory.initElements(driver, this);

	    }
	  
	@FindBy(xpath=".//input[@type='text']")
	WebElement search;
	
	@FindBy(xpath="(//h3[@class='s-item__title']//ancestor::a)[1]")
	WebElement ProdTitle;
	
	public boolean verifyhomepage()
	{
		boolean Homepage=search.isDisplayed();
		return Homepage;
	}
	
	public void searchProd(String prod)
	{
		search.click();
		search.sendKeys(prod);
		search.sendKeys(Keys.ENTER);
		
	}
	public void get_Verify_Title(String value)
	{
		//WebElement ele= driver.findElement(By.xpath("(//h3[@class='s-item__title']//ancestor::a)[1]"));
		String title=ProdTitle.getText().toLowerCase();
		if(ProdTitle.isDisplayed()&& title.contains(value.toLowerCase()))
		{
			String parentWindow=driver.getWindowHandle();
			ProdTitle.click();
			Set<String> childWindow=driver.getWindowHandles();
			for(String child:childWindow)
			{
				if(!child.equals(parentWindow))
				{
					driver.switchTo().window(child);
					break;
				}
			}
		}
	}
	
}
