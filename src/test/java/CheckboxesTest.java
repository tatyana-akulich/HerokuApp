import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckboxesTest extends BaseTest {
    private List<WebElement> elementsOfCheckbox;

    @BeforeClass
    public void getCheckboxElements() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        elementsOfCheckbox = driver.findElements(By.cssSelector("[type = checkbox]"));
    }

    @Test
    public void checkCheckbox1() {
        WebElement checkBox1 = elementsOfCheckbox.get(0);
        assertFalse(checkBox1.isSelected(), "Element shouldn't be selected");
        checkBox1.click();
        assertTrue(checkBox1.isSelected(), "Element should be selected");
    }

    @Test
    public void checkCheckbox2() {
        WebElement checkBox2 = elementsOfCheckbox.get(1);
        assertTrue(checkBox2.isSelected(), "Element should be selected");
        checkBox2.click();
        assertFalse(checkBox2.isSelected(), "Element shouldn't be selected");
    }
}
