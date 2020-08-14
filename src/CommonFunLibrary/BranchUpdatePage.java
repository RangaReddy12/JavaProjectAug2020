package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdatePage {
WebDriver driver;
public BranchUpdatePage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement clickedit;
@FindBy(name="txtbnameU")
WebElement enterbname;
@FindBy(name="txtadd1u")
WebElement enteraddress1;
@FindBy(name="txtzipu")
WebElement enterzcode;
@FindBy(name="btnupdate")
WebElement clickupdate;
public boolean verifyupdatebranch(String bname,String address1,String zcode)
throws Throwable{
	this.clickedit.click();
	Thread.sleep(5000);
this.enterbname.clear();
this.enterbname.sendKeys(bname);
this.enteraddress1.clear();
this.enteraddress1.sendKeys(address1);
this.enterzcode.clear();
this.enterzcode.sendKeys(zcode);
this.clickupdate.click();
Thread.sleep(5000);
String alertupdate=driver.switchTo().alert().getText();
Thread.sleep(5000);
driver.switchTo().alert().dismiss();
Thread.sleep(5000);
String expected="Branch updated";
if(alertupdate.toLowerCase().contains(expected.toLowerCase()))
{
	Reporter.log(alertupdate,true);
	return true;
}
else
{
	Reporter.log("Branch Updation Fail",true);
	return false;
}
}
}
