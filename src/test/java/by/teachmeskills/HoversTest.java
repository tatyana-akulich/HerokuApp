package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class HoversTest extends BaseTest {
    private List<WebElement> users;
    private List<WebElement> captions;

    @BeforeMethod(alwaysRun = true)
    public void getUsers() {
        driver.get("http://the-internet.herokuapp.com/hovers");
        users = driver.findElements(By.className("figure"));
        captions = driver.findElements(By.className("figcaption"));
    }

    @Test
    public void testUser1() {
        Actions actions = new Actions(driver);
        actions.moveToElement(users.get(0)).perform();
        assertEquals(captions.get(0).getText(), "name: user1\nView profile");
        //actions.moveToElement(driver.findElement(By.cssSelector("[href = '/users/1']"))).click().build().perform();
        actions.moveToElement(driver.findElement(By.linkText("View profile"))).click().build().perform();
        assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Not Found");
    }

    @Test
    public void testUser2() {
        Actions actions = new Actions(driver);
        actions.moveToElement(users.get(1)).perform();
        assertEquals(captions.get(1).getText(), "name: user2\nView profile");
        actions.moveToElement(driver.findElement(By.linkText("View profile"))).click().build().perform();
        assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Not Found");
    }

    @Test
    public void testUser3() {
        Actions actions = new Actions(driver);
        actions.moveToElement(users.get(2)).perform();
        assertEquals(captions.get(2).getText(), "name: user3\nView profile");
        actions.moveToElement(driver.findElement(By.linkText("View profile"))).click().build().perform();
        assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Not Found");
    }
}
