package by.teachmeskills;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextMenuTest extends BaseTest {
    @BeforeClass
    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
    }

    @Test
    public void testContextMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("hot-spot"))).contextClick().perform();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        assertThat(alertText).as("Alert text is wrong")
                .isEqualTo("You selected a context menu");
    }
}
