package DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DataDrivenFramwork {
WebDriver driver;
Properties p;
FileInputStream fi;
FileInputStream pfi;
Workbook wb;
Sheet ws;
Row row;
FileOutputStream fo;
ExtentReports report;
ExtentTest test;
String inputpath="D:\\NovemberBatchEveng\\Automation_Frameworks\\TestInput\\Logindata.xlsx";
String outputpath="D:\\NovemberBatchEveng\\Automation_Frameworks\\TestOutput\\datadriven.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	report= new ExtentReports("./ExtenReports/Report.html");
	p=new Properties();
	pfi=new FileInputStream("D:\\NovemberBatchEveng\\Automation_Frameworks\\PropertyFile\\Environment.properties");
	p.load(pfi);
	if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:\\NovemberBatchEveng\\Automation_Frameworks\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		
	}
	else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "D:\\NovemberBatchEveng\\Automation_Frameworks\\Drivers\\geckodriver.exe");
		driver=new FirefoxDriver();
			}
	else
	{
		System.out.println("Browser is not matching");
	}
}
@Test
public void verifyLogin()throws Throwable
{
	fi=new FileInputStream(inputpath);
	wb=WorkbookFactory.create(fi);
	ws=wb.getSheet("Login");
	row=ws.getRow(0);
	int rc=ws.getLastRowNum();
	int cc=row.getLastCellNum();
	Reporter.log("No of rows are::"+rc+"No of columns are::"+cc,true);
	for(int i=1;i<=rc;i++)
	{
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
	test=report.startTest("Validate Login");
	String username=ws.getRow(i).getCell(0).getStringCellValue();
	String password=ws.getRow(i).getCell(1).getStringCellValue();
	driver.findElement(By.name("txtUsername")).sendKeys(username);
	driver.findElement(By.name("txtPassword")).sendKeys(password);
	driver.findElement(By.name("Submit")).click();
	Thread.sleep(5000);
	if(driver.getCurrentUrl().contains("dash"))
	{
		Reporter.log("Login success",true);
		ws.getRow(i).createCell(2).setCellValue("Login success");
		ws.getRow(i).createCell(3).setCellValue("Pass");
		test.log(LogStatus.PASS, "Login success");
	}
	else
	{
	File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screen, new File("./Screens/iterations/"+i+"loginpage.png"));
		//capture error message
	String errormessage=driver.findElement(By.id("spanMessage")).getText();	
	Reporter.log(errormessage,true);
	ws.getRow(i).createCell(2).setCellValue(errormessage);
	ws.getRow(i).createCell(3).setCellValue("fail");
	test.log(LogStatus.FAIL, errormessage);
	}
	report.endTest(test);
	report.flush();
	}
	fi.close();
	fo=new FileOutputStream(outputpath);
	wb.write(fo);
	fo.close();
	wb.close();
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}









