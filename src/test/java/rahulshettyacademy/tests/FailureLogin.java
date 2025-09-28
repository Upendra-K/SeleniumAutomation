package rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.testcomponents.Basetest;

public class FailureLogin extends Basetest{
	
	
	@Test
	public void  validateIncorrectPassword() throws InterruptedException, IOException {

		String username = "sunny123@gmail.com";
		String passowrd = "Sunny@1234";
		String productsName = "ZARA COAT 3";
		ProductCatalog pc = lp.login(username, passowrd);
		String errormessage =lp.getInvalidLoginError();
		System.out.println(errormessage);
		Assert.assertEquals( errormessage, "Incorrect email or password.");

	}
}
