package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement header;
	
	@FindBy(id = "mouseArea_Last Name")
	private WebElement lastNameTxt;
	
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement orgNameTxt;
	
	@FindBy(xpath = "//td[@id='mouseArea_Support Start Date']")
	private WebElement startDateText;
	
	@FindBy(xpath = "//td[@id='mouseArea_Support End Date']")
	private WebElement endDateText;
	
	public WebElement getHeader() {
		return header;
	}

	public WebElement getLastNameTxt() {
		return lastNameTxt;
	}

	public WebElement getOrgNameTxt() {
		return orgNameTxt;
	}

	public WebElement getStartDateText() {
		return startDateText;
	}

	public WebElement getEndDateText() {
		return endDateText;
	}
	
	

}
