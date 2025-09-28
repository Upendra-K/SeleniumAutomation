package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponenets;

public class confirmationPage extends AbstractComponenets {

	WebDriver driver;

	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "table#htmlData td.em-spacer-1 > label")
	List<WebElement> text_OrderId;

	public String getOrderId() {
		String orderId = text_OrderId.get(1).getText().split(" ")[1];
		return orderId;
	}

}
