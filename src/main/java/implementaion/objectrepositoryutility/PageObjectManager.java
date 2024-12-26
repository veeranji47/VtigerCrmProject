package implementaion.objectrepositoryutility;


import org.openqa.selenium.WebDriver;

public class PageObjectManager {

//	LoginPage login;
//	HomePage home;
//	OrganizationsPage organization;
//	ContactsPage contacts;
//	LeadsPage leads;
//	CreateToDoPage createToDo;
//	CreatingNewOrganizationPage createOrg;
//	CreatingNewContactPage createContact;
//	CreatingNewLeadPage createLead;
//	OrganizationInformationPage orgInfo;
//	ContactInformationPage contactInfo;
//	LeadInformationPage leadInfo;
//	EventInformationPage eventInfo;
//	DuplicatingPage duplicateLead;
	
	WebDriver driver;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLogin() {
		return new LoginPage(driver);
	}

	public HomePage getHome() {
		return new HomePage(driver);
	}

	public OrganizationPage getOrganization() {
		return new OrganizationPage(driver);
	}

	public ContactPage getContacts() {
		return new ContactPage(driver);
	}

	public CreatingNewOrganizationPage getCreateOrg() {
		return new CreatingNewOrganizationPage(driver);
	}

	public CreatingNewContactPage getCreateContact() {
		return new CreatingNewContactPage(driver);
	}

	public OrganizationInformationPage getOrgInfo() {
		return new OrganizationInformationPage(driver);
	}

	public ContactInformationPage getContactInfo() {
		return new ContactInformationPage(driver);
	}

}