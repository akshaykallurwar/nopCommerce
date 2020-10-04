package com.nopCommerce.qa.test;

import static com.nopCommerce.qa.utils.TestUtil.explicitWaitForElementLoading;
import static com.nopCommerce.qa.utils.TestUtil.mouseHoverOnElement;
import static com.nopCommerce.qa.utils.TestUtil.scrollUntilVisibleByJs;
import static com.nopCommerce.qa.utils.TestUtil.clickElementsByJavaScript;
import static com.nopCommerce.qa.utils.TestUtil.explicitWaitForElementNotInteractableException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToCartFunctionalityTest 
{
	
	public WebDriver driver;
	public WebElement shoppingCart;
	public WebElement quantityTextBox;
	public String actualProductSubTotal;
	
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");//to open the URL
	}
	
	@Test
	public void validateAddToCartFunctionalityTest() throws InterruptedException
	{
			shoppingCart = driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
			driver.findElement(By.cssSelector("input.search-box-text.ui-autocomplete-input")).sendKeys("Apple MacBook Pro 13-inch");//search Apple MacBook Pro 13-inch
			driver.findElement(By.cssSelector("input.button-1.search-box-button")).click();//click on search button
			scrollUntilVisibleByJs(driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")), driver);//scroll till Add to cart is visible
			explicitWaitForElementLoading(driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")), driver);
			clickElementsByJavaScript(driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")), driver);
			driver.findElement(By.xpath("//input[@id='product_enteredQuantity_4']")).clear();//clear the text box
			driver.findElement(By.xpath("//input[@id='product_enteredQuantity_4']")).sendKeys("5");//enter value 5 in text box
			driver.findElement(By.xpath("//input[@type='button' and @id='add-to-cart-button-4']")).click();//click on add to cart button
			driver.findElement(By.xpath("//span[@class='close']")).click();//click on the close button of green alert
			explicitWaitForElementNotInteractableException(driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]")), driver);//wait for shopping cart to load
			mouseHoverOnElement(driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]")), driver);//mouse hover on shopping cart
			driver.findElement(By.xpath("//input[@type='button' and @value='Go to cart']")).click();//click on Go to cart
			System.out.println("The amount reflected on final page is "+driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText());//to print final amount value
			Assert.assertEquals(driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText(), "$9,000.00");//final assertion to validate the result
				
		}
		
	@AfterMethod
	public void tearDown()
	{
		driver.quit();//to close all the browser windows
	}

}
