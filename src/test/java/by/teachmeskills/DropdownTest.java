package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class DropdownTest extends BaseTest {
    private static final int AMOUNT_OF_ELEMENTS = 3;
    private List<WebElement> options;
    private Select dropdown;

    @BeforeMethod(alwaysRun = true)
    public void getDropdownElements() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        dropdown = new Select(driver.findElement(By.id("dropdown")));
        options = dropdown.getOptions();
    }

    @Test
    public void checkAmountOfDropdownElements() {
        assertEquals(options.size(), AMOUNT_OF_ELEMENTS, "Wrong amount of dropdown elements was found");
    }

    @Test
    public void checkTextOfDropdownElements() {
        assertEquals(options.get(0).getText(), "Please select an option", "Option is invalid");
        assertEquals(options.get(1).getText(), "Option 1", "Option is invalid");
        assertEquals(options.get(2).getText(), "Option 2", "Option is invalid");
    }

    @Test
    public void checkSelectionOfElements() {
        WebElement option1 = options.get(1);
        WebElement option2 = options.get(2);
        assertFalse(option1.isSelected(), "Option shouldn't be selected");
        assertFalse(option2.isSelected(), "Option shouldn't be selected");
        dropdown.selectByVisibleText("Option 1");
        assertTrue(option1.isSelected(), "Option should be selected");
        assertFalse(option2.isSelected(), "Option shouldn't be selected");
        dropdown.selectByValue("2");
        assertFalse(option1.isSelected(), "Option shouldn't be selected");
        assertTrue(option2.isSelected(), "Option should be selected");
    }
}
