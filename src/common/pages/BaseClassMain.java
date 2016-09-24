package common.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pages.LoginPage;
import framework.DriverOperations;

public class BaseClassMain extends DriverOperations
{
	public static LoginPage loginPage=null;
	public static Properties mainProp;
	public static Properties locatorProp;
	public static String[][] xpathData;
	public static DriverOperations driverOps;
	
	   protected BaseClassMain() {
	      // Exists only to defeat instantiation.
	   }
	   public static BaseClassMain getInstance() {
	      if(loginPage == null) {
	    	  loginPage = new LoginPage();
	      }
	      return loginPage;
	   }
	   
	   
	@BeforeClass
	public void readProperties() throws Exception
	{
		driverOps=new DriverOperations();
		mainProp=new Properties();
		locatorProp=new Properties();
		System.out.println(System.getProperty("user.dir")+"::::::::::::::::::::");
		InputStream mainInput=new FileInputStream(new File(System.getProperty("user.dir")+"/resources/dataprovide.properties"));
		InputStream locatorInput=new FileInputStream(new File(System.getProperty("user.dir")+"/resources/xpathdata.properties"));
		mainProp.load(mainInput);	
		locatorProp.load(locatorInput);
		navigateToUrl(getMainProp("site.browser"),getMainProp("site.url"));
		loginPage=(LoginPage) BaseClassMain.getInstance();
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
	}

	public static String getMainProp(String key)
	{
		return mainProp.getProperty(key);
	}
	
	public static String getLocatorProp(String key)
	{
		return locatorProp.getProperty(key);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
}
