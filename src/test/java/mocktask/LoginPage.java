package mocktask;

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
	public void getUsernameEdt(String un) {
		 usernameEdt.sendKeys(un);
	}

	public void getPasswordEdt(String psw) {
		usernameEdt.sendKeys(psw);
	}

	public void getLoginBtn() {
		 loginBtn.click();;
	}
	
	

}
