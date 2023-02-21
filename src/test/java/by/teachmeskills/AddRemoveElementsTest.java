package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddRemoveElementsTest extends BaseTest {
    private static final int AMOUNT_OF_LEFT_ELEMENTS = 1;

    @Test
    public void addRemoveElementsTest() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        addButton.click();
        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
        deleteButton.click();
        List<WebElement> displayedElements = driver.findElements(By.xpath("//button[text()='Delete']"));
        Assert.assertEquals(displayedElements.size(), AMOUNT_OF_LEFT_ELEMENTS,
                String.format("Amount of left elements is %d, should be %d", displayedElements.size(), AMOUNT_OF_LEFT_ELEMENTS));
    }
}
