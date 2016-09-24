package pages;

import common.pages.PageBase;

public class LoginPage extends PageBase
{
	public HomePage performLogin(String username, String password)
	{
		safeTypeBy("XPATH",getLocatorProp("username_xpath"), username);
		safeTypeBy("XPATH",getLocatorProp("pwd_xpath"), password);
		
		safeClickBy("XPATH",getLocatorProp("loginsubmit_xpath"));
		
		return new HomePage();
	}
	
	public void clickLoginLink()
	{
		safeClickBy("xpath",getLocatorProp("loginbtn_xpath"));
	}
}

