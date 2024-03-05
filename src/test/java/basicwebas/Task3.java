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

public class Task3 {

	public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "Drivers\\chromeexec\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.fc-button.fc-cta-consent.fc-primary-button"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'mt-4')]/div/div[contains(@class, 'card-body')][h5 = 'Widgets']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='item-4'][span = 'Progress Bar']"))).click();

        driver.findElement(By.id("startStopButton")).click();

        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@role='progressbar']"), "aria-valuenow", "100"));

        driver.findElement(By.id("resetButton")).click();

        String result = driver.findElement(By.xpath("//div[@role='progressbar']")).getText();
        String desiredResult = "0%";
        if(result.equals(desiredResult)) {
            System.out.println("Progreso eilutė tuščia (0%)");
        } else {
            System.out.println("Progreso eilutė nėra tuščia");
        }
        driver.quit();
    }

}