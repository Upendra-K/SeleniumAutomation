package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponenets;

public class OrdersPage extends AbstractComponenets{
WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".ng-star-inserted tr td:nth-child(3)")
	List <WebElement> text_ordersHistoryItems;
	
	public boolean verifyOrderItemDisplayed(String productname) {
		 
		boolean isExist = text_ordersHistoryItems.stream().anyMatch(names->names.getText().equalsIgnoreCase(productname));
		return isExist;
		
		
	}

}
