package com.nopCommerce.qa.test;

import static com.nopCommerce.qa.utils.TestUtil.clickElementsByJavaScript;
import static com.nopCommerce.qa.utils.TestUtil.mouseHoverOnElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToWishListFunctionalityTest 
{
	
	public WebDriver driver;
	
	
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
	public void validateAddToWishListFunctionalityTest() throws InterruptedException
	{
		mouseHoverOnElement(driver.findElement(By.xpath("//a[contains(text(),'Apparel ')]")), driver);
		driver.findElement(By.xpath("//a[contains(text(),'Clothing ')]")).click();
		clickElementsByJavaScript(driver.findElement(By.xpath("//a[contains(text(),'Custom T-Shirt')]")), driver);
		//driver.findElement(By.xpath("//a[contains(text(),'Custom T-Shirt')]")).click();
		driver.findElement(By.cssSelector("#product_attribute_12")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@id='add-to-wishlist-button-29']")).click();//to click on add to wish list
		driver.findElement(By.xpath("//span[@class='close']")).click();//click on the close button of green alert
		driver.findElement(By.xpath("//span[@class='wishlist-label']")).click();//to click on wish list button at the top
		System.out.println("The amount reflected on final page is "+driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText());//to print final amount value
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='product-subtotal']")).getText(), "$15.00");
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();//to close all the browser windows
	}

}
