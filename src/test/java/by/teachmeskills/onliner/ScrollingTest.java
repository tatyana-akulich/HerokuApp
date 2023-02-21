package by.teachmeskills.onliner;

import by.teachmeskills.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScrollingTest extends BaseTest {
    @BeforeClass
    public void openPage() {
        driver.get("https://www.onliner.by/");
    }

    @Test
    public void testScrolling() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        WebElement forum = driver.findElement(By.xpath("//a[text()='Форум']"));
        js.executeScript("arguments[0].scrollIntoView(true);", forum);
        js.executeScript("window.scrollTo(0,0)");//top
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");//down
    }
}
