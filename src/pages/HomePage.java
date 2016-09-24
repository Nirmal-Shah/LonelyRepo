package pages;

import common.pages.PageBase;

public class HomePage extends PageBase
{
	public int getTotalLinkCount()
	{
		return safeGetWebElements(getLocatorProp("threadlinkcount_xpath")).size();
	}
	public String getForumThreadName(int index)
	{
		return safeGetTextBy("XPATH",String.format(getLocatorProp("threadlink_xpath"),index));
	}
	
	public String getForumThreadHref(int index)
	{
		return safeGetAttribute(String.format(getLocatorProp("threadlink_xpath"),index), "href");
	}
	
	public String getForumThreadViews(int index)
	{
		return safeGetTextBy("XPATH",String.format(getLocatorProp("threadviews_xpath"),index));
	}
	
	public String getForumThreadReplies(int index)
	{
		return safeGetTextBy("XPATH",String.format(getLocatorProp("threadreplies_xpath"),index));
	}
	
	public boolean getForumThreadIsPinned(int index)
	{
		return safeGetWebElements(String.format(getLocatorProp("threadreplies_xpath"),index)).size()>0;
	}
	
	public String getThreadCountry(int index)
	{
		return safeGetTextBy("XPATH",String.format(getLocatorProp("threadcountry_xpath"),index));
	}
	
	public String getThreadBy(int index)
	{
		return safeGetTextBy("XPATH",String.format(getLocatorProp("threadby_xpath"),index));
	}
	
	public String getThreadCreatedDate(int index)
	{
		return safeGetTextBy("XPATH",String.format(getLocatorProp("threadcreatedat_xpath"),index));
	}
	
	public String getThreadUrl()
	{
		return driver.getCurrentUrl();
	}
	
	public PostMainPage clickThreadLink(int index)
	{
		safeClickBy("XPATH", String.format(getLocatorProp("threadlink_xpath"),index));
		
		return new PostMainPage();
	}
}
