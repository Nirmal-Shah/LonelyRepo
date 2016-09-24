package pages;

import common.pages.PageBase;

public class PostMainPage extends PageBase
{
	public String getPostBlackQuote(int index)
	{
		return safeGetTextBy("CSS", "tbody>tr:nth-child("+index+")>td[class='post__text']");
	}
	
	public String getPostDateTime(int index)
	{
		return safeGetTextBy("XPATH", String.format(getLocatorProp("postreplyon_xpath"), index));
	}
	
	public String getPosterName(int index)
	{
		return safeGetTextBy("XPATH", String.format(getLocatorProp("postreplyby_xpath"), index));
	}
	
	public String getThreadLikesMain()
	{
		return safeGetTextBy("XPATH", getLocatorProp("threadLikes.xpath"));
	}
	
	public HomePage getToUrl(String url)
	{
		driver.get(url);
		
		return new HomePage();
	}

	public String getPostUrl()
	{
		return driver.getCurrentUrl();
	}
	
}
