package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SortableDataTablesTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void getPage() {
        driver.get("http://the-internet.herokuapp.com/tables");
    }

    @Test
    public void checkTable1Cell() {
        WebElement cell = driver.findElement(By.xpath("//table//tr[1]//td[1]"));
        assertEquals(cell.getText(), "Smith");
    }

    @Test
    public void checkTable2Cell() {
        WebElement cell = driver.findElement(By.xpath("//table[2]//tr[2]//td[2]"));
        assertEquals(cell.getText(), "Frank");
    }

    @Test
    public void checkTableHeadingCell() {
        WebElement cell = driver.findElement(By.xpath("//table[2]//th[3]"));
        assertEquals(cell.getText(), "Email");
    }

    @Test
    public void checkTableCellWithLink() {
        WebElement cell = driver.findElement(By.xpath("//table[2]//tr[1]//td[6]"));
        assertEquals(cell.getText(), "edit delete");
    }

}
