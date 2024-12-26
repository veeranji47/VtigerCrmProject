package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement header;
	
	@FindBy(id = "mouseArea_Industry")
	private WebElement industryCheck;
	
	@FindBy(id = "mouseArea_Type")
	private WebElement typeCheck;
	
	@FindBy(id = "mouseArea_Phone")
	private WebElement phone;
	
	public WebElement getIndustryCheck() {
		return industryCheck;
	}

	public WebElement getTypeCheck() {
		return typeCheck;
	}

	public WebElement getHeader() {
		return header;
	}
	public WebElement getPhone() {
		return phone;
	}

}
