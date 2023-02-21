package by.teachmeskills.onliner;

import by.teachmeskills.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest extends BaseTest {
    @BeforeClass
    public void openPage() {
        driver.get("https://www.onliner.by/");
    }

    @Test
    public void testFrame() {
        WebElement searchInputHomepage = driver.findElement(By.xpath("//input[@class='fast-search__input']"));
        searchInputHomepage.sendKeys("тостер");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='modal-iframe']")));
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='result__wrapper']"));
        assertThat(products).as("List of found products shouldn't be empty")
                .isNotEmpty();
        String firstProductName = products.get(0).findElement(By.xpath("//div[@class='product__title']//a")).getText();
        assertThat(firstProductName).as("Invalid product name (can't be null)")
                .isNotNull();
        WebElement searchLine = driver.findElement(By.xpath("//input[@class='search__input']"));
        searchLine.clear();
        searchLine.sendKeys(firstProductName);
        driver.findElement(By.xpath("//span[@class='search__close']")).click();
        driver.switchTo().defaultContent();
        assertThat(driver.findElement(By.xpath("//input[@name='query']")).getAttribute("value")).as("Wrong text in search line")
                .isEqualTo(firstProductName);
    }
}
