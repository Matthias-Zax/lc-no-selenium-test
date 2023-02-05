import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracticesoftwaretestingTests {
    WebDriver driver;

    @Test
    public void T1_LOGIN_loginWithValidCredentials_UserLoggedIn() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://practicesoftwaretesting.com/#/");

        // Navigate to Login Screen
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]//a[contains(text(),'Sign in')]")).click();

        // Insert Credentials
        driver.findElement(By.id("email")).sendKeys("test@test.at");
        driver.findElement(By.id("password")).sendKeys("test1234!");
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();

        // Assert right header after the login -> Login is successful
        WebElement message = driver.findElement(By.xpath("//h1"));
        assertTrue(message.getText().contains("My account"), "Login Header shows 'My Account'");
        driver.quit();
    }

    @Test
    public void T5_LOGIN_loginWithInvalidCredentials_ErrorMsgDisplayed() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://practicesoftwaretesting.com/#/");

        // Navigate to Login Screen
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]//a[contains(text(),'Sign in')]")).click();

        // Insert Credentials
        driver.findElement(By.id("email")).sendKeys("invalid@mail.at");
        driver.findElement(By.id("password")).sendKeys("invalid1!");
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();

        // Assert right header after the login -> Login is successful
        WebElement message = driver.findElement(By.xpath("//div[contains(@data-test, 'error')] /div"));
        assertTrue(message.getText().contains("Invalid email or password"), "Error Message is not correct!");
 
        driver.quit();
    }


}

