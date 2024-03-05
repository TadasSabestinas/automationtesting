package basicwebas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

@TestMethodOrder(OrderAnnotation.class)
public class Task4 {
	private WebDriver driver;
	private WebDriverWait wait;
	private static String email;
	private static String password;
	
	@BeforeAll
	public static void setUpEmail() {
		email = "random" + System.currentTimeMillis() + "@gmail.com";
		password = "pw" + System.currentTimeMillis() + "aa";
		
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromeexec\\chromedriver.exe");
        WebDriver staticDriver = new ChromeDriver();
        
        staticDriver.get("https://demowebshop.tricentis.com/");
        staticDriver.manage().window().maximize();
		
        staticDriver.findElement(By.xpath("//a[@class='ico-login']")).click();
		
        staticDriver.findElement(By.xpath("//input[@value='Register']")).click();
		
        staticDriver.findElement(By.xpath("//input[@id='gender-male']")).click();
		
        staticDriver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Tadas");
		
        staticDriver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Sabestinas");
		
        staticDriver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		
        staticDriver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		
        staticDriver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
		
        staticDriver.findElement(By.xpath("//input[@id='register-button']")).click();
		
        staticDriver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		staticDriver.quit();

	}
	
	
	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromeexec\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	@AfterEach
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"src\\\\test\\\\java\\\\data\\\\data1.txt", "src\\\\test\\\\java\\\\data\\\\data2.txt"})

	public void firstTest(String filePath) throws InterruptedException, IOException {
		List<String> itemNames = Files.readAllLines(Paths.get(filePath));
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-login']"))).click();
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		driver.findElement(By.xpath("//li[@class='inactive']/a[@href='/digital-downloads']")).click();
		
		for (String itemName : itemNames) {
			driver.findElement(By.xpath("//h2/a[contains(text(), '" + itemName + "')]")).click();
			driver.findElement(By.xpath("//div[@class='add-to-cart-panel']/input[@value='Add to cart']")).click();
			driver.findElement(By.xpath("//span[contains(text(), 'Digital downloads')]")).click();
		}
		
		driver.findElement(By.xpath("//span[contains(text(), 'Shopping cart')]")).click();
		
		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		if (driver.findElements(By.id("billing-address-select")).size() == 0) {
			WebElement selectElementLithuania = driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"));
			
			Select countriesDropdown = new Select(selectElementLithuania);
			
			countriesDropdown.selectByVisibleText("Lithuania");
			
			driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("Alytus");
			
			driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("Udrijos g. 15B");
			
			driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("62455");
			
			driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys("8698909593");
			
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Continue']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='PaymentMethod.save()']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='PaymentInfo.save()']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@onclick='ConfirmOrder.save()']"))).click();

		boolean present;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button']")));
			driver.findElement(By.xpath("//div/strong[contains(text(), 'Your order has been successfully processed!')]"));
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		
		if(present) {
			System.out.println("Ordered processed!");
		} else {
			System.out.println("Error occurred while processing the order");
		}
	}
}	