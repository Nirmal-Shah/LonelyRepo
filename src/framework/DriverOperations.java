package framework;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.HomePage;

public class DriverOperations extends DriverInitialize
{
	Actions action;
	
	public boolean verifyElementPresent(String xpathStr)
	{
		boolean elementPresent=false;
		if(driver.findElement(By.xpath(xpathStr)).isDisplayed())
			elementPresent=true;
		
		return elementPresent;
	}

	public WebElement getWebElement(String xpathStr)
	{
		return driver.findElement(By.xpath(xpathStr));
	}
	
	public HomePage navigateToUrl(String browser, String url) throws InterruptedException
	{
		driver=setBrowser(browser);
		System.out.println("browser set to "+browser+".... please wait the requested url "+url+" is opening..");
		driver.get(url);
		driver.manage().window().maximize();
		return new HomePage();
	}
	
	public void safeTypeBy(String findBy,String locator, String textToType)
	{	
		if(findBy.equalsIgnoreCase("ID"))
			driver.findElement(By.id(locator)).sendKeys(textToType);
		
		if(findBy.equalsIgnoreCase("XPATH"))
			driver.findElement(By.xpath(locator)).sendKeys(textToType);
		
		if(findBy.equalsIgnoreCase("CLASS"))
			driver.findElement(By.className(locator)).sendKeys(textToType);
	}
	
	public void safeClickBy(String findBy,String locator)
	{	
		//logger.info("Clicked on "+locator);
		if(findBy.equalsIgnoreCase("ID"))
			driver.findElement(By.id(locator)).click();
				
		if(findBy.equalsIgnoreCase("XPATH"))
			driver.findElement(By.xpath(locator)).click();
		
		if(findBy.equalsIgnoreCase("CLASS"))
			driver.findElement(By.className(locator)).click();
	}
	
	public void safeClickWithActionBy(String findBy,String locator)
	{	
		action=new Actions(driver);
		if(findBy.equalsIgnoreCase("ID"))
			action.click(driver.findElement(By.id(locator))).perform();
				
		if(findBy.equalsIgnoreCase("XPATH"))
			action.click(driver.findElement(By.xpath(locator))).perform();
		
		if(findBy.equalsIgnoreCase("CLASS"))
			action.click(driver.findElement(By.className(locator))).perform();
	}
	
	public void performMouseoverBy(String findBy,String locator)
	{
		action=new Actions(driver);
		if(findBy.equalsIgnoreCase("ID"))
			action.moveToElement(driver.findElement(By.id(locator))).perform();
	
		if(findBy.equalsIgnoreCase("XPATH"))
			action.moveToElement(driver.findElement(By.xpath(locator))).perform();
	
		if(findBy.equalsIgnoreCase("CLASS"))
			action.moveToElement(driver.findElement(By.className(locator))).perform();
	}
	
	public String safeGetTextBy(String findBy,String locator)
	{	
		String text="";
		if(findBy.equalsIgnoreCase("ID"))
			text=driver.findElement(By.id(locator)).getText();
		
		if(findBy.equalsIgnoreCase("XPATH"))
			text=driver.findElement(By.xpath(locator)).getText();
		
		if(findBy.equalsIgnoreCase("CLASS"))
			text=driver.findElement(By.className(locator)).getText();
		
		if(findBy.equalsIgnoreCase("CSS"))
			text=driver.findElement(By.cssSelector(locator)).getText();
		
		//logger.info("Locator name is:"+locator+" and text inside locator is:"+text);
		
		return text;
	}

	
	public ArrayList<WebElement> safeGetWebElements(String locator)
	{
		return (ArrayList<WebElement>) driver.findElements(By.xpath(locator));
		
	}
	
	public boolean isElementDisplayed(String findBy,String locator)
	{	
		boolean isDisplay=false;
		if(findBy.equalsIgnoreCase("ID"))
			isDisplay=driver.findElement(By.id(locator)).isDisplayed();
		
		if(findBy.equalsIgnoreCase("XPATH"))
		{
			if(driver.findElements( By.xpath(locator) ).size() != 0)
				isDisplay=true;
		}
			//isDisplay= driver.findElement(By.xpath(locator)).isDisplayed();
		
		if(findBy.equalsIgnoreCase("CLASS"))
			isDisplay= driver.findElement(By.className(locator)).isDisplayed();
		
		return isDisplay;
	}
	
	/*public void navigateBack() throws InterruptedException
	{
		driver.navigate().back();
		Thread.sleep(5000);
	}*/
	
	public String safeGetAttribute(String locator, String attribute)
	{
		return driver.findElement(By.xpath(locator)).getAttribute(attribute);
	}
	
	public boolean isElementPresent(String obj) {
		boolean displayed = false;
		try {
			if(driver.findElements( By.xpath(obj) ).size() != 0)
				displayed = true;
		} catch (NoSuchElementException e) {
			displayed = false;
			return displayed;
		} catch (WebDriverException e) {
			displayed = false;
			return displayed;
		}
		return displayed;
	}
}