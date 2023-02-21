package by.teachmeskills;

import by.teachmeskills.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUploadTest extends BaseTest {
    @BeforeClass
    public void openPage() {
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    @Test
    public void testUpload() {
        String absolutePath = (System.getProperty("user.dir") + "/src/test/resources/testForUpload.docx");
        driver.findElement(By.id("file-upload")).sendKeys(absolutePath);
        driver.findElement(By.id("file-submit")).click();
        assertThat(driver.findElement(By.id("uploaded-files")).getText())
                .as("Name of file uploaded to the site should be equal to name in file system")
                .isEqualTo("testForUpload.docx");
    }
}
