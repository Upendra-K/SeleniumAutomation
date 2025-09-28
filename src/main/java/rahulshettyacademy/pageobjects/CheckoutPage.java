package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponenets;

public class CheckoutPage extends AbstractComponenets {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement drpdwn_country;

	@FindBy(css = ".ta-item.ta-item.list-group-item.ng-star-inserted")
	List<WebElement> options;

	@FindBy(css = ".action__submit")
	WebElement btn_placeOrder;

	public void selectCountry() {
		drpdwn_country.sendKeys("Ind");
		WebElement slctCtry = options.stream().filter(country -> country.getText().equalsIgnoreCase("India"))
				.findFirst().orElse(null);
		slctCtry.click();
	}

	public confirmationPage placeOrder() {
		btn_placeOrder.click();
		return new confirmationPage(driver);
	}

}
