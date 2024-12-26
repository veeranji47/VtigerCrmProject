package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(id = "phone")
	private WebElement phnNum;
	
	
	
	@FindBy(name = "industry")
	private WebElement industryName;
	
	@FindBy(name = "accounttype")
	private WebElement acType;
	
	@FindBy(xpath = "//table[@class='small']/tbody/tr[1]/td/div[@align='center']/input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getPhnNum() {
		return phnNum;
	}

	public WebElement getIndustryName() {
		return industryName;
	}

	public WebElement getAcType() {
		return acType;
	}
	
	public WebElement projectId() {
		return acType;
	}
	
	public void clickSaveBtn() {
		saveBtn.click();
	}
	

}
