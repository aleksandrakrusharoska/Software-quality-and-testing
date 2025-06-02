import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserTest {
    private WebDriver driver;
    private AdminPage adminPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        loginPage.loginAsAdmin();
    }

    @Test
    public void testAddUserSuccessfully() {
        adminPage.openAdminTab();
        adminPage.clickAddButton();
        adminPage.fillUserForm("ESS", "Timothy Lewis Amiano", "Enabled", "test111", "Test@12345");
        adminPage.clickSave();

        Assert.assertTrue("Toast message was not displayed.",
                adminPage.isSuccessToastDisplayed("Successfully Saved"));
    }

    @Test
    public void testAddUserCancelled() {
        adminPage.openAdminTab();
        adminPage.clickAddButton();
        adminPage.fillUserForm("ESS", "Timothy Lewis Amiano", "Enabled", "test111", "Test@12345");
        adminPage.clickCancel();

        Assert.assertFalse("User should not be added after cancel.",
                adminPage.isUserInTable("test777"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

