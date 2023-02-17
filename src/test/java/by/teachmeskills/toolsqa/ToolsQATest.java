package by.teachmeskills.toolsqa;

import by.teachmeskills.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ToolsQATest extends BaseTest {

    @BeforeClass
    public void openPage() {
        driver.get("https://demoqa.com/alerts");
    }

    @Test
    public void testAlerts() {
        driver.findElement(By.id("alertButton")).click();
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText()).as("Wrong alert text for alert1")
                .isEqualTo("You clicked a button");
        alert.accept();
        driver.findElement(By.id("timerAlertButton")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        assertThat(alert.getText()).as("Wrong alert text for alert in 5 seconds")
                .isEqualTo("This alert appeared after 5 seconds");
        alert.accept();
        driver.findElement(By.id("confirmButton")).click();
        alert = driver.switchTo().alert();
        assertThat(alert.getText()).as("Wrong alert text for alert in 5 seconds")
                .isEqualTo("Do you confirm action?");
        alert.dismiss();
        assertThat(driver.findElement(By.id("confirmResult")).getText())
                .as("Wrong text after alert dismissing")
                .isEqualTo("You selected Cancel");
        driver.findElement(By.id("promtButton")).click();
        alert = driver.switchTo().alert();
        assertThat(alert.getText()).as("Wrong text of alert message")
                .isEqualTo("Please enter your name");
        alert.sendKeys("Tatyana");
        alert.accept();
        assertThat(driver.findElement(By.id("promptResult")).getText())
                .as("Wrong text after inputing data in alert")
                .isEqualTo("You entered Tatyana");
    }
}
