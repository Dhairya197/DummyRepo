package com.java.logicfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import net.bytebuddy.asm.Advice.Argument;

@Listeners(com.java.utils.Listners.class)

public class SeleniumClass {
	public WebDriver driver;
	Properties OR;
	FileInputStream fs;
	
	
	public SeleniumClass() throws IOException
	{
		 fs= new FileInputStream(System.getProperty("user.dir")+"\\OR\\OR.properties");
		 OR = new Properties();
		 OR.load(fs);
	}
	@Test(priority=1)
	public void browserSetup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dhairya_Lala\\Desktop\\onboardingWorkspace\\OnboardingProgram\\Executables\\chromedriver.exe");	
		driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}
	@Test(dependsOnMethods ={"browserSetup"},priority=2 )
	public void radiobutton()
	{
		System.out.println(OR.getProperty("radio2"));
		WebElement radio2=driver.findElement(By.xpath(OR.getProperty("radio2")));
		//js.executeScript("arguments[0].scrollIntoView(true);", radio2);
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("radio2"))));
        radio2.click();
        
        //To validate if radio button is selected or not and performing click event on radio button.
        WebElement radio1 = driver.findElement(By.xpath(OR.getProperty("radio1")));
        boolean selectState = radio1.isSelected();
        //Code to check if radio button is already selected
        if (!selectState){
            radio1.click();
            String text=driver.findElement(By.xpath(OR.getProperty("labelradio1"))).getText();
            Assert.assertEquals(text, "Radio1");
        }
        //Close chrome driver
     
	}

	@Test(dependsOnMethods ={"browserSetup"},priority=3) 
	public void checkbox()
	{
		 WebElement option3=driver.findElement(By.xpath(OR.getProperty("checkBox")));
		//js.executeScript("arguments[0].scrollIntoView(true);", option3);
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty("checkBox"))));
		 boolean isSelected = option3.isEnabled();

	        if (!isSelected){
	            option3.click();
	            SoftAssert softAssert = new SoftAssert();
	            softAssert.assertEquals(driver.findElement(By.xpath(OR.getProperty("labelCheckBox"))).getText(), "Option3");
	        }
	        
	        List<WebElement> checkboxes=driver.findElements(By.xpath(OR.getProperty("listCheckbox")));

	        for(int i = 0 ; i < checkboxes.size(); i++){
	            checkboxes.get(i).click();
	        }
	}

	@Test(dependsOnMethods ={"browserSetup"},priority=4) 
	public void alert() throws InterruptedException
	{
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.name("enter-name")));

		//Enter value in edit box and press ALert button.
		driver.findElement(By.name("enter-name")).sendKeys("Dhairya");
		driver.findElement(By.id("alertbtn")).click();
		//Thread.sleep(3000);
		//Code to accept the alert pop up
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		

	}
	
	@Test(dependsOnMethods ={"browserSetup"},priority=5) 
	public void handleWindow()
	{
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("openwindow")));

		 //code to open new window of browser.
        driver.findElement(By.id("openwindow")).click();
        driver.manage().window().maximize();

        //Code for Parent Window
        String parentWindow = driver.getWindowHandle();

        //Code to handle all new windows opened.
        Set<String> childWindows = driver.getWindowHandles();

        for (String childWindow : childWindows) {
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                driver.findElement(By.linkText("Login")).click();
                //Close child window
                driver.close();
            }
        }
        //code to switch back to parent window.
        driver.switchTo().window(parentWindow);

	}

	@Test(dependsOnMethods ={"browserSetup"},priority=6) 
	public void action()
	{
		Actions actions = new Actions(driver);
        WebElement mouseHover =  driver.findElement(By.xpath("//button[@id='mousehover']"));
       // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",mouseHover);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,1000)");
        actions.moveToElement(mouseHover).build().perform();
        driver.findElement(By.linkText("Reload")).click();

        WebElement ele =  driver.findElement(By.xpath("//button[@id='mousehover']"));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,1000)");
        actions.moveToElement(ele).contextClick().build().perform();
        driver.quit();        
        
	}
	
	
}
