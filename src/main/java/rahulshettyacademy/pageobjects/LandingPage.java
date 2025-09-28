package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponenets;

public class LandingPage extends AbstractComponenets{
	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement passoword;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css =".toast-bottom-right")
	WebElement toast_invalidLogin;
	
	public void navigate() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	
	public ProductCatalog login(String email, String password) {
		userEmail.sendKeys(email);
		passoword.sendKeys(password);
		submit.click();
		return new ProductCatalog(driver);
		
	}
	
	public String getInvalidLoginError() {
		waitUntilElementToAppear(toast_invalidLogin);
		return toast_invalidLogin.getText();
		
	}
}
