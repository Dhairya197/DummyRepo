package com.epam.bdd.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="(//a[text()='Sign in'])[1]")
	WebElement signin;
	
	@FindBy(xpath="//div[@class='signin-intro']//span[@id='user-info']")
	WebElement emailid;
	
	@FindBy(id="pass")
	WebElement passFeild;
	
	@FindBy(id="sgnBt")
	WebElement SignInButton;

	@FindBy(id="switch-account-anchor")
	WebElement switchAcc;
	
	@FindBy(id="signin-continue-btn")
	WebElement continueBtn;
	
	@FindBy(id="userid") 
	WebElement username;
	  
	@FindBy(id="pass")
	WebElement password;
	 
	public void signin() throws InterruptedException
	{
		Thread.sleep(1000);
		if(signin.isDisplayed())
		signin.click();
		Thread.sleep(1000);
	}
		
	public void verifyEmail(String Email,String Password) throws InterruptedException
	{
			username.click();
			Thread.sleep(1000);
			username.sendKeys(Email);
			Thread.sleep(1000);
			continueBtn.click();
			Thread.sleep(1000);
			password.click();
			Thread.sleep(1000);
			password.sendKeys(Password);
			Thread.sleep(1000);
			SignInButton.click();
			Thread.sleep(1000);
			
	
	}

}
