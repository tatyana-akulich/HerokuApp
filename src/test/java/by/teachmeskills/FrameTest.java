package by.teachmeskills;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest extends BaseTest {
    @BeforeClass
    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/frames");
    }

    @Test
    public void testFrame() {
        driver.findElement(By.xpath("//a[text()='iFrame']")).click();
        driver.switchTo().frame("mce_0_ifr");
        assertThat(driver.findElement(By.xpath("//p")).getText())
                .as("Text is not equal to text in frame")
                .isEqualTo("Your content goes here.");
    }
}
