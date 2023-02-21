package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TyposTest extends BaseTest {
    @Test
    public void testInputForTypos() {
        driver.get("http://the-internet.herokuapp.com/typos");
        List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
        WebElement paragraphWithTypos = paragraphs.get(1);
        String lineWithoutTypos = "Sometimes you'll see a typo, other times you won't.";
        assertEquals(paragraphWithTypos.getText(), lineWithoutTypos, "Line contains a typo"); //fails if any typo is found
    }
}
