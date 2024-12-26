package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.genericUtilities.WebDriverUtility;


public class HomePage {
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td[@class='tabUnSelected']/a[.='Organizations']")
	private WebElement orgLnk;
	
	@FindBy(xpath = "//a[.='Contacts']")
	private WebElement contactLnk;
	
	@FindBy(xpath = "//img[contains(@src,'images/user.PNG')]")
	private WebElement adminstratorImg;
	
	@FindBy(xpath = "//a[.='Sign Out']")
	private WebElement signOutBtn;

	public void clickOrgLnk() {
		orgLnk.click();
	}

	public void clickContactLnk() {
		contactLnk.click();
	}
	
	public void signOutOfVtiger(WebDriverUtility driverUtil) {
		driverUtil.mouseHover(adminstratorImg);
		signOutBtn.click();
	}
}
