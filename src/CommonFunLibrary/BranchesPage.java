package CommonFunLibrary;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BranchesPage {
@FindBy(xpath="//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")
WebElement clickBranche;
public void ClickBranches()throws Throwable
{
	clickBranche.click();
	Thread.sleep(4000);
}
}
