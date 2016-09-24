package websitetest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.PostMainPage;
import utilities.WaitTool;
import utilities.WriteToCSV;
import common.test.TestBase;

public class HomePageTest extends TestBase
{
	//String forumThreadName, forumThreadHref, threadViews, threadReplies, threadCountry, threadBy, threadCreatedAt, threadUrl;
	String postTitle, forumThreadHref, threadViews, threadReplies, threadCountry, threadBy, threadCreatedAt, threadUrl;

	boolean threadIsPinned;
	public static String username,password;
	public static HomePage homepage;
	public static PostMainPage postMainPage;
	ArrayList<HashMap<String,String>> userInfo=new ArrayList<HashMap<String,String>>();
	HashMap<String,String> eachUserInfo=new HashMap<String,String>();
	int postInc=0;
	@FindBy(css = "tr[class='post'] td[class='post__text']")
    List<WebElement> postlist;
	
	String failScreenshotFilePath =System.getProperty("user.dir")+ "\\FAILURE-SCREENSHOTS\\";
	String threadScreenshotFilePath =System.getProperty("user.dir")+ "\\THREAD-SCREENSHOTS\\";
	final String[] columnHeaders = new String[] {"ForumThreadName", "ThreadHref", "ThreadViews", "ThreadReplies", "ThreadIsPinned", "ThreadCountry", "ThreadBy", "ThreadCreatedDaete", "ThreadUrl", "PostDateTime", "PostText", "PosterName", "PostUrl", "Screenshot_Id", "Screenshot_Filepath", "TotalNumberOfPosts"};
	String fileName; 
	
	@Test
	public void testDrugsLoginExp() throws Exception
	{
		loginPage.clickLoginLink();
		username=getMainProp("username");
		password=getMainProp("password");
		
		homepage=loginPage.performLogin(username, password);
		
		//dynamic wait for 5 seconds applied
		WaitTool.waitForPageToLoad();
		int linkCnt=homepage.getTotalLinkCount();

		fileName="xyz"+getCurrentDateAndTime();
		
		//getIntoForumMainLinks(linkCnt);
		
		if (linkCnt > 9) {
			if (driver.findElement(By.xpath(getLocatorProp("threadnextpage_xpath"))).isDisplayed()) {
				while (driver.findElement(By.xpath(getLocatorProp("threadnextpage_xpath"))).isDisplayed())
				{
					getIntoForumMainLinks(linkCnt);
					WaitTool.clickAndWait(driver.findElement(By.xpath("//a [@class='pagination__link pagination__link--next icon--chevron-right--before']")));						
				}
			}
		}
		//getIntoForumMainLinks(linkCnt);	
	}
	
	public void getIntoForumMainLinks(int linkCnt) throws Exception
	{
		
		for(int i=1;i<= linkCnt;i++)
		{
			//Thread.sleep(5000);
			postTitle=homepage.getForumThreadName(i);
			
			/*if(forumThreadName.length()>25)
				fileName=forumThreadName.substring(0,25)+getCurrentDateAndTime();
			else
				fileName=forumThreadName;*/
			
			forumThreadHref=homepage.getForumThreadHref(i);
			threadViews=homepage.getForumThreadViews(i);
			threadReplies=homepage.getForumThreadReplies(i);
			threadIsPinned=homepage.getForumThreadIsPinned(i);
			threadCountry=homepage.getThreadCountry(i);
			threadBy=homepage.getThreadBy(i);
			threadCreatedAt=homepage.getThreadCreatedDate(i);
			threadUrl=homepage.getThreadUrl();
			
			eachUserInfo.put(columnHeaders[0], postTitle);
			eachUserInfo.put(columnHeaders[1], forumThreadHref);
			eachUserInfo.put(columnHeaders[2], threadViews);
			eachUserInfo.put(columnHeaders[3], threadReplies);
			eachUserInfo.put(columnHeaders[4], threadIsPinned+"");
			eachUserInfo.put(columnHeaders[5], threadCountry);
			eachUserInfo.put(columnHeaders[6], threadBy);
			eachUserInfo.put(columnHeaders[7], threadCreatedAt);
			eachUserInfo.put(columnHeaders[8], threadUrl);
		
			postMainPage=homepage.clickThreadLink(i);
			Thread.sleep(5000);
			
			driver.findElement(By.xpath(getLocatorProp("printMenu_button"))).click();
			driver.findElement(By.xpath(getLocatorProp("showAllPost"))).click();
			
			for(String windowHandles : driver.getWindowHandles())
				driver.switchTo().window(windowHandles);
			
				int size = driver.findElements(By.cssSelector("tr[class='post']")).size();
						
				readPosts(size);
				driver.close();
				
				for(String windowHandles : driver.getWindowHandles())
					driver.switchTo().window(windowHandles);
				
				driver.navigate().back();
			//postMainPage.getToUrl(threadUrl);
		}
	}
	
	public void readPosts(int size) throws Exception
	{
		// entered into each post
		userInfo.clear();
		for (int j = 1; j <= size; j++) 
		{		
			HashMap<String,String> eachUserInfo = new HashMap<String,String>();
			eachUserInfo.put(columnHeaders[0], postTitle);
			eachUserInfo.put(columnHeaders[1], forumThreadHref);
			eachUserInfo.put(columnHeaders[2], threadViews);
			eachUserInfo.put(columnHeaders[3], threadReplies);
			eachUserInfo.put(columnHeaders[4], threadIsPinned+"");
			eachUserInfo.put(columnHeaders[5], threadCountry);
			eachUserInfo.put(columnHeaders[6], threadBy);
			eachUserInfo.put(columnHeaders[7], threadCreatedAt);
			eachUserInfo.put(columnHeaders[8], threadUrl);
			eachUserInfo.put(columnHeaders[9],postMainPage.getPostDateTime(j));				
			eachUserInfo.put(columnHeaders[10],postMainPage.getPostBlackQuote(j+1));
			eachUserInfo.put(columnHeaders[11],postMainPage.getPosterName(j));
			eachUserInfo.put(columnHeaders[12],postMainPage.getPostUrl());
			postInc++;
			eachUserInfo.put(columnHeaders[13],"");
			eachUserInfo.put(columnHeaders[14],"");
			eachUserInfo.put(columnHeaders[15],postInc+"");
			userInfo.add(eachUserInfo);
		}
	
		/*Thread.sleep(5000);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		FileUtils.copyFile(scrFile, new File(threadScreenshotFilePath+forumThreadName+getCurrentDateAndTime()+".jpg"));
		String ssFilePath=threadScreenshotFilePath+forumThreadName+getCurrentDateAndTime()+".jpg";
		String ssId=forumThreadName+getCurrentDateAndTime()+".jpg";
				
		eachUserInfo.put(columnHeaders[13],ssId);
		eachUserInfo.put(columnHeaders[14],ssFilePath);
		eachUserInfo.put(columnHeaders[15],postInc+"");
		
		userInfo.clear();
		userInfo.add(eachUserInfo);*/
		WriteToCSV.writeDataToCSV(userInfo,columnHeaders, fileName);
	}

	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException 
	{
		String methName;
		if (testResult.getStatus() == ITestResult.FAILURE)
		{ 
			methName=testResult.getName().toString().trim();
			System.out.println(testResult.getStatus()); 
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(scrFile, new File(failScreenshotFilePath+methName+getCurrentDateAndTime()+".jpg"));
		} 
		
		//driver.quit();
	}
}

