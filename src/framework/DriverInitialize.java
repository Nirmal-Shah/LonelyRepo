package framework;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import framework.DriverInitialize;

public class DriverInitialize 
{
	public static WebDriver driver;
	public final static Logger logger = Logger.getLogger(DriverInitialize.class);

	String log4jConfigFile;
	
	public WebDriver setBrowser(String browserName)
	{
		Browser browser = Browser.valueOf(browserName);
		switch(browser)
		{
		case firefox:
			driver=new FirefoxDriver();
			break;
	
		case internetexplorer:
			System.setProperty("webdriver.ie.driver",
				    System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			
			 DesiredCapabilities capab = DesiredCapabilities.internetExplorer();
		     capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		            true);
		        
			driver = new InternetExplorerDriver(capab);
			break;
			
		case chrome:
			 System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+ "/drivers/chromedriver.exe");
			 driver = new ChromeDriver();
			 break;
			 
		default:
			System.out.println("invalid browser name or may not be implemented");
			break;
		}
		configureLogger();
		
		return driver;
	}
	
	public synchronized void configureLogger()
	{
		log4jConfigFile = System.getProperty("user.dir")+"\\resources\\log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static String getCurrentDateAndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
		Date date = new Date();
		String time = sdf.format(date);
		return time;
	}

}
