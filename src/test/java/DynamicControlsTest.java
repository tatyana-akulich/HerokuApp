import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class DynamicControlsTest extends BaseTest {
    @BeforeClass
    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void testDynamicControls() {
        Wait<WebDriver> fluent = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By checkBox = By.id("checkbox");
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertThat(driver.findElements(checkBox)).as("Element shouldn't be found")
                .isEmpty();
        By input = By.xpath("//input[@type='text']");
        assertThat(driver.findElement(input).isEnabled()).as("Input should be disabled")
                .isFalse();
        driver.findElement(By.xpath("//button[text()='Enable']")).click();
        fluent.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        assertThat(driver.findElement(input).isEnabled()).as("Input should be enabled")
                .isTrue();
    }
}
