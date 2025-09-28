package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.confirmationPage;
import rahulshettyacademy.testcomponents.Basetest;
import rahulshettyacademy.testcomponents.Retry;

public class PlaceOrderTest extends Basetest {
	String username = "sunny123@gmail.com";
	String passowrd = "Sunny@123";
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "smoke")
	public void placeOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		//extent.createTest("placeOrder");

		ProductCatalog pc = lp.login(input.get("email"), input.get("password"));

		pc.addProductToCart(input.get("product"));
		CartPage cp = pc.clickOnCart();

		boolean isVisible = cp.isItemPresentInCart(input.get("product"));
		Assert.assertTrue(isVisible);
		CheckoutPage cop = cp.clickOnCheckout();

		cop.selectCountry();
		confirmationPage cnfp = cop.placeOrder();

		String orderId = cnfp.getOrderId();
		System.out.println(orderId);
		
	}

	@Test(dependsOnMethods = {"placeOrder"}, retryAnalyzer = Retry.class)
	public void orderHistory() {
		
		ProductCatalog pc = lp.login(username, passowrd);

		OrdersPage op = pc.clickOnOrders();
		boolean isdisplayed = op.verifyOrderItemDisplayed(productName);
		Assert.fail("Failing intendently");
		Assert.assertTrue(isdisplayed);
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		/*
		 * First way of writing 2D Array
		 * 
		 * Object[][] data = { { "sunny123@gmail.com", "Sunny@123", "ZARA COAT 3" }, {
		 * "shetty@gmail.com", "Iamking@0001", "ADIDAS ORIGINAL" }, {
		 * "anshika@gmail.com", "Iamking@000", "IPHONE 13 PRO" } }; return data;
		 */
		
		
		
		/*
		 * Second way of writing 2D Array using HashMap

		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "sunny123@gmail.com"); map1.put("password", "Sunny@123");
		 * map1.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map2 = new HashMap<String, String>();
		 * map2.put("email", "shetty@gmail.com"); map2.put("password", "Iamking@000");
		 * map2.put("product", "ADIDAS ORIGINAL");
		 * 
		 * HashMap<String, String> map3 = new HashMap<String, String>();
		 * map3.put("email", "anshika@gmail.com"); map3.put("password", "Iamking@000");
		 * map3.put("product", "IPHONE 13 PRO"); return new Object[][] { { map1 }, {
		 * map2 }, { map3 } };
		 */
		
		List<HashMap<String, String>> data = readDataAndConvertToHashMap(System.getProperty("user.dir")+"//src/test//java/resources//LoginData.json");
		Object[][] returnData = new Object[data.size()][1];  // rows = # of JSON objects, cols = 1
	    for (int i = 0; i < data.size(); i++) {
	        returnData[i][0] = data.get(i);
	    }
	    return returnData;

		
	}
}
