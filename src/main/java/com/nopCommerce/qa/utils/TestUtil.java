package com.nopCommerce.qa.utils;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil 
{
	public static void explicitWaitForElementLoading(WebElement element, WebDriver driver) 
	{
		WebDriverWait webdriverWait = new WebDriverWait(driver, 15);
		webdriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement explicitWaitForElementNotInteractableException(WebElement element,WebDriver driver)
	{
		WebDriverWait webdriverWait = new WebDriverWait(driver, 15);
		webdriverWait.ignoring(ElementNotInteractableException.class).until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	public static void mouseHoverOnElement(WebElement element, WebDriver driver) 
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public static void scrollUntilVisibleByJs(WebElement element, WebDriver driver) throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// to click on a WebElement
	public static void clickElementsByJavaScript(WebElement element, WebDriver driver) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	

}
