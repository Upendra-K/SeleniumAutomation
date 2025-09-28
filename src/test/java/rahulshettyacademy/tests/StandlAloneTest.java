package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandlAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String productname = "ZARA COAT 3";

		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		options.setExperimentalOption("prefs", prefs);

		options.addArguments("--incognito"); // OR use --guest
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-save-password-bubble");
		options.addArguments("--no-first-run");
		options.addArguments("--no-default-browser-check");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("sunny123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sunny@123");
		driver.findElement(By.id("login")).click();
		Thread.sleep(3000);
		List<WebElement> products = driver.findElements(By.cssSelector(".card"));

		WebElement product = products.stream()
				.filter(s -> s.findElement(By.xpath(".//div/h5/b")).getText().contains(productname)).findFirst()
				.orElse(null);
		product.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartWrap"));

		WebElement item = cartItems.stream().filter(items -> items.getText().contains(productname)).findFirst()
				.orElse(null);
		item.findElement(By.cssSelector(".btn.btn-primary")).click();

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");

		List<WebElement> countries = driver
				.findElements(By.cssSelector(".ta-item.ta-item.list-group-item.ng-star-inserted"));
		WebElement slctCtry = countries.stream().filter(country -> country.getText().equalsIgnoreCase("India"))
				.findFirst().orElse(null);
		slctCtry.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		List<WebElement> orderIdNumber = driver.findElements(By.cssSelector("table#htmlData td.em-spacer-1 > label"));
		System.out.println(orderIdNumber.get(1).getText().split(" ")[1]);

		Thread.sleep(3000);
		driver.quit();
	}
}
