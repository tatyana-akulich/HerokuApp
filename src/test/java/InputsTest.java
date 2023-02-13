import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class InputsTest extends BaseTest {
    private WebElement input;

    @BeforeClass
    public void getInputField() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        input = driver.findElement(By.tagName("input"));
    }

    @BeforeMethod(alwaysRun = true)
    public void clearField() {
        input.clear();
    }

    @Test
    public void testKeysArrowInput() {
        input.sendKeys("0");
        input.sendKeys(Keys.ARROW_DOWN);
        assertEquals(input.getAttribute("value"), "-1", "Input value is invalid");
        input.sendKeys(Keys.ARROW_UP);
        assertEquals(input.getAttribute("value"), "0", "Input value is invalid");
    }

    @Test
    public void checkPositiveDigitsInput() {
        input.sendKeys("123456789");
        assertEquals(input.getAttribute("value"), "123456789", "Input value is invalid");
    }

    @Test
    public void checkDecimalDigitsInput() {
        input.sendKeys("1.15");
        assertEquals(input.getAttribute("value"), "1.15", "Input value is invalid");
    }

    @Test
    public void checkNegativeDigitsInput() {
        input.sendKeys("-10");
        assertEquals(input.getAttribute("value"), "-10", "Input value is invalid");
    }

    @Test
    public void checkAlphabetic() {
        input.sendKeys("abcd");
        assertEquals(input.getAttribute("value"), "", "Letters shouldn't be accepted"); // letters are not accepted
    }

    @Test
    public void checkSymbolsInput() {
        input.sendKeys(",");
        assertEquals(input.getAttribute("value"), "", "Coma shouldn't be accepted"); //coma and point without digits are not accepted
        input.sendKeys("...");
        assertEquals(input.getAttribute("value"), "", "Points without digits shouldn't be accepted");
    }

    @Test
    public void checkSymbolsAndDigitsInput() {
        input.sendKeys(",5");
        assertEquals(input.getAttribute("value"), ".5", "Input value is invalid"); //coma or point as a first symbol is accepted
        input.clear();
        input.sendKeys("1,5.3");
        assertEquals(input.getAttribute("value"), "1.53", "Several points/comas shouldn't be accepted"); //only one coma or point in the field is accepted
    }
}
