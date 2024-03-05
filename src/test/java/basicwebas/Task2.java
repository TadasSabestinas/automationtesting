package basicwebas;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;



public class Task2 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers\\\\chromeexec\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoqa.com/");
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(("button.fc-button.fc-cta-consent.fc-primary-button"))).click();
		
		driver.findElement(By.xpath("//div[contains(@class, 'mt-4')]/div/div[contains(@class, 'card-body')][h5 = 'Elements']")).click();
		
		driver.findElement(By.xpath("//li[@id='item-3'][span = 'Web Tables']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		addRecordsUntilSecondPageReached(driver);
		
		((JavascriptExecutor) driver)
	     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Next')]"))).click();
		
		driver.findElement(By.xpath("//span[@id='delete-record-11']")).click();
		
		driver.findElement(By.xpath("//input[@aria-label='jump to page']")).click();
		
		driver.findElement(By.xpath("//span[@class='-pageInfo']/span")).click();
		
		boolean present;
		try {
			driver.findElement(By.xpath("//input[@aria-label='jump to page' and @value='1']"));
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}

		String totalPages = driver.findElement(By.xpath("//span[@class='-pageInfo']/span")).getText();
		if(present) {
			System.out.println("Automatiškai perkeliama į 1 puslapį");
		}
		if(totalPages.equals("1")) {
			System.out.println("Puslapių skaičius sumažėjo iki vieno");
		}
		driver.quit();
	}
	
	public static void addRecordsUntilSecondPageReached(WebDriver driver) {
	    FluentWait<WebDriver> wait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofSeconds(1))
	            .ignoring(NoSuchElementException.class);

	    WebElement totalPagesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='-pageInfo']/span")));
	    int value = Integer.parseInt(totalPagesElement.getText());

	    if (value < 2) {
	        driver.findElement(By.id("addNewRecordButton")).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName"))).sendKeys("Tadas");
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName"))).sendKeys("Tadas");
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail"))).sendKeys("tadas@gmail.com");
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("age"))).sendKeys("22");
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("salary"))).sendKeys("7777");
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("department"))).sendKeys("MIF");
	        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();

	        // Recursively call this method until the condition is met
	        addRecordsUntilSecondPageReached(driver);
	    }
	    // If value is 2 or more, the method will end, effectively stopping the loop
	}

}


