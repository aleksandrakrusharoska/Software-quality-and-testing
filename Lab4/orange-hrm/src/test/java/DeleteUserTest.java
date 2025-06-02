import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteUserTest {
    private WebDriver driver;
    private AdminPage adminPage;
    private String username;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        LoginPage loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        loginPage.loginAsAdmin();

        adminPage.openAdminTab();
        adminPage.clickAddButton();
        username = "username8888";
        adminPage.fillUserForm("ESS", "Peter Mac Anderson", "Enabled", username, "Password123!");
        adminPage.clickSave();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By userRowLocator = By.xpath("//div[contains(@class, 'oxd-table-row') and .//div[text()='" + username + "']]");
        WebElement userRow = wait.until(ExpectedConditions.visibilityOfElementLocated(userRowLocator));
    }

    @Test
    public void testDeleteUserConfirm() {
        adminPage.searchUser(username);
        adminPage.clickDeleteButton(username);
        adminPage.confirmDeletion();

        Assert.assertTrue("Expected success toast to be displayed after deletion",
                adminPage.isSuccessToastDisplayed("Successfully Deleted"));
        Assert.assertFalse("User should not be present after deletion",
                adminPage.isUserInTable(username));
    }

    @Test
    public void testDeleteUserCancel() {
        adminPage.searchUser(username);
        adminPage.clickDeleteButton(username);
        adminPage.cancelDeletion();

        Assert.assertFalse("No deletion success toast should appear", adminPage.isSuccessToastDisplayed("Successfully Deleted"));
        Assert.assertTrue("User should still be present after canceling deletion", adminPage.isUserInTable(username));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
