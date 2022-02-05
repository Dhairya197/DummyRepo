package com.epam.bdd.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	  WebDriver driver;
	    
	  public ProductPage(WebDriver driver){
	        this.driver = driver;
	        //This initElements method will create all WebElements
	        PageFactory.initElements(driver, this);

	    }
	  @FindBy(xpath="//a[@id='isCartBtn_btn']")
	  WebElement AddToCartButton;
	  
	  @FindBy(xpath=".//button[text()='Go to checkout']")
	  WebElement GoToCheckout;
	  
	  
	  public boolean isAddToCartVisible()
	  {
			if(AddToCartButton.isDisplayed())
				{
				AddToCartButton.click();
				return true;
				}
			return false;
	  }
	  public boolean verifyCheckout()
	  {
			if(GoToCheckout.isDisplayed())
			return true;
			
			return false;
	  }
}
