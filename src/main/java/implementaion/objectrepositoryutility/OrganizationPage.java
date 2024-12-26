package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement cretaeOrgBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchBar;
	
	@FindBy(name = "search_field")
	private WebElement searchDD;
	

	@FindBy(xpath = "//input[@value=' Search Now ' and @name = 'submit']")
	private WebElement searchBarBtn;

	public WebElement getSearchBar() {
		return searchBar;
	}
	
	public WebElement getSearchDD() {
		return searchDD;
	}

	public void getSearchBarBtn() {
		searchBarBtn.click();;
	}

	public void getCretaeOrgBtn() {
		cretaeOrgBtn.click();
	}

	
}
