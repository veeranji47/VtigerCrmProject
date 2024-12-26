package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lstName;
	
	@FindBy(xpath = "//tr[5]/td[2]/img[contains(@src,'images/select.gif')]")
	private WebElement orgNamePlusBtn;
	
	@FindBy(name = "support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name = "support_end_date")
	private WebElement endDateEdt;
	
	@FindBy(xpath = "//table[@class='small']/tbody/tr[1]/td/div[@align='center']/input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getLstName() {
		return lstName;
	}

	public void getOrgNamePlusBtn() {
		orgNamePlusBtn.click();
	}

	public WebElement getStartDateEdt() {
		return startDateEdt;
	}

	public WebElement getEndDateEdt() {
		return endDateEdt;
	}

	public void clickSaveBtn() {
		saveBtn.click();
	}

	
	

}
