package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LogoutPage {
WebDriver driver;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tbody/tr/td[3]/a/img")
WebElement clicklogout;
@FindBy(name="login")
WebElement loginbutton;
public boolean verifyLogout()throws Throwable
{
	this.clicklogout.click();
	Thread.sleep(5000);
	if(loginbutton.isDisplayed())
	{
		Reporter.log("Logout Success",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail",true);
		return false;
	}
}
}
