package basicwebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Task1 {

	public static void main(String[] args) {
	 System.setProperty("webdriver.chrome.driver", "Drivers\\chromeexec\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// 1. Atidaro puslapi
		driver.get("https://demowebshop.tricentis.com");
		driver.manage().window().maximize();
		// 2. Paspaudzia gift cards kairiajame meniu
		driver.findElement(By.xpath("//li[@class='inactive']/a[@href='/gift-cards']")).click();
		
		// 3. Pasirenka preke, kurios kaina didesne nei 99 nuoroda.
		driver.findElement(By.xpath("//div[contains(@class, 'product-item')][.//span[contains(@class, 'price')]/text() > 99]//a")).click();
		// 4. Supildo laukus Recipient's naame ir Your name
		driver.findElement(By.className("recipient-name")).sendKeys("Vida");
		driver.findElement(By.className("sender-name")).sendKeys("Tadas");
		// 5. I tekstini lauka "Qty" ivesti 5000
		driver.findElement(By.className("qty-input")).clear();
		driver.findElement(By.className("qty-input")).sendKeys("5000");
		// 6. Spausti "Add to cart" mygtuka
		driver.findElement(By.id("add-to-cart-button-4")).click();
//		7. Spausti "Add to wishlist" mygtuka
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		driver.findElement(By.id("add-to-wishlist-button-4")).click();
//		8. Spausti "Jewelry" kairiajame meniu.
		driver.findElement(By.xpath("//li[@class='inactive']/a[@href='/jewelry']")).click();
//		9. Spausti "Create your own jewelry" nuoroda
		driver.findElement(By.xpath("//a[@href='/create-it-yourself-jewelry']")).click();
//		10. Pasirinkti reiksmes: "Material - Silver 1mm", Length in cm - 80, Pendant - star
		Select dropdown = new Select(driver.findElement(By.id("product_attribute_71_9_15")));
		dropdown.selectByVisibleText("Silver (1 mm)");
		
		driver.findElement(By.name("product_attribute_71_10_16")).sendKeys("80");
		
		driver.findElement(By.id("product_attribute_71_11_17_50")).click();
//		11. I tekstini lauka Qty ivesti 26
		driver.findElement(By.className("qty-input")).clear();
		driver.findElement(By.className("qty-input")).sendKeys("26");
//		12. Spausti "Add to cart" mygtuka
		driver.findElement(By.id("add-to-cart-button-71")).click();
//		13. Spausti "Add to wishlist" mygtuka
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("add-to-wishlist-button-71")).click();
//		14. Spausti nuoroda "Wishlist" puslapio virsuje
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//div[@class='header-links']/ul/li/a[@href='/wishlist' and @class='ico-wishlist']")).click();
//		15. Abejom prekem paspausti "Add to cart" varneles
		List<WebElement> AddToCartCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox' and @name='addtocart']"));
		for (WebElement checkbox : AddToCartCheckboxes) {
			checkbox.click();
		}
//		16. Spausti "Add to cart" mygtuka
		driver.findElement(By.xpath("//input[@type='submit' and @name='addtocartbutton']")).click();
//		17. Atsiradus Shopping cart puslapyje patvirtinti, kad "Sub-total" reiksme yra '1002600.00'
		String result = driver.findElement(By.xpath("//span[@class='product-price']")).getText();
		String expectedResult = "1002600.00";
		if(result.equals(expectedResult)) {
			System.out.println("Teisinga, sub-total reiksme yra 1002600.00");
		}
		else {
			System.out.println("Neteisinga, sub-total reiksme nera 1002600.00");
		}
		driver.quit();
	}

}
