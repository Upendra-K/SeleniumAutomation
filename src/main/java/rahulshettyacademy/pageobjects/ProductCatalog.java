package rahulshettyacademy.pageobjects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponenets;

public class ProductCatalog extends AbstractComponenets {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productsList;

	@FindBy(xpath = ".//button[contains(text(),'Add To Cart')]")
	WebElement button_addToCart;

	@FindBy(css = ".toast-container")
	WebElement message_toast;

	@FindBy(css = ".ng-animating")
	WebElement icon_spin;

	@FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement button_cart;

	By produstsListLocator = By.cssSelector(".mb-3");
	By productnameLocator = By.xpath(".//div/h5/b");
	By addToCartLocator = By.xpath(".//button[contains(text(),'Add To Cart')]");

	public void addProductToCart(String productName) throws InterruptedException {
		waitUntilElementToAppear(produstsListLocator);
		WebElement product = productsList.stream().filter(s -> s.findElement(productnameLocator).getText().contains(productName)).findFirst()
				.orElse(null);
		product.findElement(addToCartLocator).click();
		waitUntilElementToAppear(message_toast);
		Thread.sleep(2000);
		// waitUntilElementToDisappear(icon_spin);

	}
	
	public void addProductsToCart(String[] productName) throws InterruptedException {
		waitUntilElementToAppear(produstsListLocator);
		Set<String> productSet = new HashSet<>(Arrays.asList(productName));

		productsList.stream()
		        .filter(product -> productSet.contains(product.findElement(productnameLocator).getText()))
		        .forEach(product -> {
		            product.findElement(addToCartLocator).click();
		            System.out.println("Clicked: " + product.findElement(productnameLocator).getText());
		            waitUntilElementToAppear(message_toast);
		            waitUntilElementToDisappear(icon_spin);
		    		
		        });
//		waitUntilElementToAppear(message_toast);
//		Thread.sleep(2000);
		// waitUntilElementToDisappear(icon_spin);

	}

}
