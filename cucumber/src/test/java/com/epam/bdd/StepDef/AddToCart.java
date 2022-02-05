package com.epam.bdd.StepDef;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.bdd.Pages.LoginPage;
import com.epam.bdd.Pages.ProductPage;
import com.epam.bdd.Pages.SearchProduct;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToCart {

	public WebDriver driver;
	SearchProduct sp;
	ProductPage pp;
	String productname;
	LoginPage lp;

	@Before
	public void initDriver() {
		System.out.println("Open browser");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Dhairya_Lala\\Desktop\\onboardingWorkspace\\OnboardingProgram\\Executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.get("https://www.ebay.com/");
		sp=new SearchProduct(driver);
	}

	@After
	public void teardown() {
		System.out.println("Close browser");
		driver.quit();
	}

	@Given("user is on Ebay website landing page")
	public void user_is_on_ebay_website_landing_page() {

		driver.get("https://www.ebay.com/");
	}

	@When("user searches a product {string} on the homepage.")
	public void user_searches_a_product_on_the_homepage(String string) {
		productname = string;
		sp = new SearchProduct(driver);
		sp.searchProd(string);

	}

	@Then("user should be able to view product information related to product searched.")
	public void user_should_be_able_to_view_product_information_related_to_product_searched() {

		sp.get_Verify_Title(productname);
	}

	@Then("user click on add to cart button")
	public void user_click_on_add_to_cart_button() {

		pp = new ProductPage(driver);
		boolean isAddtoCartPresent = pp.isAddToCartVisible();
		Assert.assertTrue(isAddtoCartPresent);
	}

	@Then("user verifies if the product is added to cart")
	public void user_verifies_if_the_product_is_added_to_cart() {

		boolean isCheckoutPresent = pp.verifyCheckout();
		Assert.assertTrue(isCheckoutPresent);
	}

	// *******************************************************************//


	@When("user logs in with {string} and {string}")
	public void user_logs_in_with_and(String username, String password) throws InterruptedException {
		lp=new LoginPage(driver);
		lp.signin();
		lp.verifyEmail(username, password);
		
	}

	@Then("user should be able to view homepage.")
	public void user_should_be_able_to_view_homepage() {
		//sp=new SearchProduct(driver);
		Assert.assertTrue(sp.verifyhomepage());
	}

	@When("user searches for {string}")
	public void user_searches_for(String product) {
		productname=product;
		sp.searchProd(product);
	}
}
