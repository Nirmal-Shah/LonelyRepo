package utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.DriverInitialize;

public class WaitTool extends DriverInitialize
{
	public static final String timeout = "5";
	public static final long delay = 60;
	public static WebElement waitForElement(WebDriver driver, String locator, int timeOutInSeconds)
	{	
		WebElement element;	
		try
		{	
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
			  
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds); 
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			
			return element; //return the element	
		}
		
		catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return null; 
	}
	
	public void waitForElementVisible(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (InvalidElementStateException e) {
		} catch (NoSuchElementException e) {
		}
	}

	public static void clickAndWait(WebElement element) throws InterruptedException {
		element.click();
		waitForPageToLoad();
		Thread.sleep(delay);
	}
	
	public static void waitForPageToLoad() {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(timeout), TimeUnit.SECONDS);
	}
 }
