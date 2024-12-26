package implementaion.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "user_name")
	private WebElement usernameEdt;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdt;
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	public void loginToVtiger(String name, String pass) {
		usernameEdt.sendKeys(name);
		passwordEdt.sendKeys(pass);
		loginBtn.click();
	}
	
	
	
	

}
