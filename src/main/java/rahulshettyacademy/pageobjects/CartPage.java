package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponenets;

public class CartPage extends AbstractComponenets {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartWrap")
	List<WebElement> itemsInCart;

	@FindBy(css = ".totalRow .btn.btn-primary")
	WebElement button_checkout;

	public boolean isItemPresentInCart(String productName) {
		boolean isVisible = itemsInCart.stream().filter(items -> items.getText().equalsIgnoreCase(productName)) != null;
		return isVisible;

	}
	public boolean isItemsPresentInCart(String[] productName) {
		boolean isVisible = itemsInCart.stream().filter(items -> items.getText().equals(productName)) != null;
		return isVisible;

	}

	public CheckoutPage clickOnCheckout() {
		button_checkout.click();
		return new CheckoutPage(driver);
	}

}
