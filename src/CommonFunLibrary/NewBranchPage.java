package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class NewBranchPage {
WebDriver driver;
public NewBranchPage(WebDriver driver)
{
	this.driver=driver;
}
//locators for branchcreation
@FindBy(xpath="//input[@id='BtnNewBR']")
WebElement clicknewbranch;
@FindBy(name="txtbName")
WebElement enterbname;
@FindBy(name="txtAdd1")
WebElement enteraddress1;
@FindBy(name="Txtadd2")
WebElement enteraddress2;
@FindBy(name="txtadd3")
WebElement enteraddress3;
@FindBy(name="txtArea")
WebElement enterarea;
@FindBy(name="txtZip")
WebElement enterzcode;
@FindBy(name="lst_counrtyU")
WebElement selectcountry;
@FindBy(name="lst_stateI")
WebElement selectstate;
@FindBy(name="lst_cityI")
WebElement selectcity;
@FindBy(name="btn_insert")
WebElement clicksubmit;
public boolean verifynewBranch(String bname,String address1,String address2,
String address3,String area,String zcode,int country,int state,int city)throws Throwable
{
	this.clicknewbranch.click();
	Thread.sleep(4000);
	this.enterbname.sendKeys(bname);
	this.enteraddress1.sendKeys(address1);
	this.enteraddress2.sendKeys(address2);
	this.enteraddress3.sendKeys(address3);
	this.enterarea.sendKeys(area);
	this.enterzcode.sendKeys(zcode);
	new Select(this.selectcountry).selectByIndex(country);
	Thread.sleep(4000);
	new Select(this.selectstate).selectByIndex(state);
	Thread.sleep(4000);
	new Select(this.selectcity).selectByIndex(city);
	Thread.sleep(4000);
	this.clicksubmit.click();
	Thread.sleep(5000);
	//capture alert message
	String alertmessage=driver.switchTo().alert().getText();
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String expected="New Branch";
	if(alertmessage.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log(alertmessage,true);
		return true;
	}
	else
	{
		Reporter.log("New Branch Not Created",true);
		return true;
	}
	
}
}






