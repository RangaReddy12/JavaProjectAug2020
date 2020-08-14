package DriverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import CommonFunLibrary.BranchUpdatePage;
import CommonFunLibrary.BranchesPage;
import CommonFunLibrary.LoginPage;
import CommonFunLibrary.LogoutPage;
import CommonFunLibrary.NewBranchPage;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScript extends PBConstant{
String inputpath="D:\\NovemberBatchEveng\\Automation_Frameworks\\TestInput\\Controller.xlsx";
String outputpath="D:\\NovemberBatchEveng\\Automation_Frameworks\\TestOutput\\Keyword.xlsx";
String TCSheet="TestCases";
String TSSheet="TestSteps";
@Test
public void startTest()throws Throwable
{
	boolean res=false;
	String tcres=null;
	//call all page class into driver script
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);	
	BranchesPage navigatebranch=PageFactory.initElements(driver, BranchesPage.class);
	NewBranchPage branch=PageFactory.initElements(driver, NewBranchPage.class);
	BranchUpdatePage Bupdate=PageFactory.initElements(driver, BranchUpdatePage.class);
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	//creating object for excel methods
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	//count no of rows in TCSheet
	int TCcount=xl.rowCount(TCSheet);
	//count no of rows in TSSheet
	int TScount=xl.rowCount(TSSheet);
	Reporter.log(TCcount+"   "+TScount,true);
	//iterate all rows in TCSheet
	for(int i=1; i<=TCcount;i++)
	{
		//read execute cell From TCSheet
String execute=xl.getCelldata(TCSheet, i, 2);
if(execute.equalsIgnoreCase("Y"))
{
	//read tcid from TCSheet
	String tcid=xl.getCelldata(TCSheet, i, 0);
	//iterate all rows in TSSheet
	for(int j=1;j<=TScount;j++)
	{
		//read tsid cell from TSSheet
		String tsid=xl.getCelldata(TSSheet, j, 0);
		if(tcid.equalsIgnoreCase(tsid))
		{
			//read keyword cell from TSSheet
			String keyword=xl.getCelldata(TSSheet, j, 3);
			//call methods with keyword
			if(keyword.equalsIgnoreCase("AdminLogin"))
			{
				//call login method
			res=login.verifyLogin("Admin", "Admin");	
			}
			else if(keyword.equalsIgnoreCase("NewBranchCreation"))
			{
				navigatebranch.ClickBranches();
				res=branch.verifynewBranch("Kadiri23", "anantapur", "madanapalli", "tanakallu", "kadiri", "23467", 1, 1, 1);
			}
			else if(keyword.equalsIgnoreCase("UpdateBranch"))
			{
				navigatebranch.ClickBranches();
				res=Bupdate.verifyupdatebranch("kukatpalli", "Byderabad", "90909");
			}
			else if(keyword.equalsIgnoreCase("AdminLogout"))
			{
				res=logout.verifyLogout();
			}
			
			String tsres=null;
			//if res is true method executed success or method fail
			if(res)
			{
				tsres="Pass";
				//write as pass into results cell in TSSheet
				xl.setCellData(TSSheet, j, 4, tsres, outputpath);
			}
			else
			{
				tsres="Fail";
				//write as fail into results cell in TSSheet
				xl.setCellData(TSSheet, j, 4, tsres, outputpath);
			}
			if(!tsres.equalsIgnoreCase("Fail"))
			{
				tcres=tsres;	
			}
		}
	}
	//write tcres into TSheet
	xl.setCellData(TCSheet, i, 3, tcres, outputpath);
}
else
{
	//write as Not Executed into results cell in TCSheet
	xl.setCellData(TCSheet, i, 3, "Not Executed", outputpath);
}
	}
}
}
