import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage extends AbstractPage {

    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminTab;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//label[text()='Employee Name']/../following-sibling::div//input")
    private WebElement employeeNameInput;

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
    private WebElement usernameInput;

    @FindBy(xpath = "//label[text()='Password']/../following-sibling::div//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div//input")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
    private WebElement searchUsernameInput;

    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//div[contains(@class, 'oxd-select-text')]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[contains(@class, 'oxd-select-text')]")
    private WebElement statusDropdown;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void openAdminTab() {
        wait.until(ExpectedConditions.elementToBeClickable(adminTab)).click();
    }

    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    private void selectDropdownOption(WebElement dropdown, String optionText) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']")));
        option.click();
    }

    public void fillUserForm(String userRole, String employeeName, String status, String username, String password) {
        selectDropdownOption(userRoleDropdown, userRole);

        wait.until(ExpectedConditions.visibilityOf(employeeNameInput)).clear();
        employeeNameInput.sendKeys(employeeName);

        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[contains(text(), '" + employeeName + "')]")));
        suggestion.click();

        selectDropdownOption(statusDropdown, status);

        wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
        usernameInput.sendKeys(username);

        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput)).clear();
        confirmPasswordInput.sendKeys(password);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clickCancel() {
        By cancelButtonLocator = By.xpath("//button[contains(text(),'Cancel') or contains(@class,'oxd-button--ghost')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
        cancelButton.click();
    }

    public boolean isSuccessToastDisplayed(String message) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'oxd-toast') and contains(., '" + message + "')]")
            ));
            return toast.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void searchUser(String username) {
        wait.until(ExpectedConditions.visibilityOf(searchUsernameInput));
        searchUsernameInput.clear();
        searchUsernameInput.sendKeys(username);

        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.oxd-table-row.oxd-table-row--with-border"), 2));
    }

    public boolean isUserInTable(String username) {
        List<WebElement> rows = driver.findElements(By.cssSelector("div.oxd-table-row.oxd-table-row--with-border"));
        if (rows.size() != 2) {
            System.out.println("The user " + username + " is not found in the table.");
            return false;
        }
        WebElement row = rows.get(1);
        boolean found = row.getText().contains(username);
        if (!found) {
            System.out.println("User " + username + " not found in the row.");
        }
        return found;
    }

    public void clickDeleteButton(String username) {
        if (!isUserInTable(username)) {
            System.out.println("User not found, can't delete.");
            return;
        }
        WebElement row = driver.findElements(By.cssSelector("div.oxd-table-row.oxd-table-row--with-border")).get(1);
        WebElement deleteButton = row.findElement(By.cssSelector("button i.bi-trash"));
        deleteButton.click();
    }

    public void confirmDeletion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By confirmDeleteButtonLocator = By.xpath("//button[contains(@class, 'oxd-button--label-danger')]");
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButtonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmDeleteButton);
        confirmDeleteButton.click();

        By toastSuccessLocator = By.cssSelector(".oxd-toast-container .oxd-toast-content--success");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccessLocator));

        System.out.println("The user is successfully deleted!");
    }

    public void cancelDeletion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By cancelButtonLocator = By.xpath("//button[contains(., 'Cancel')]");
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(cancelButtonLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancelButton);
        cancelButton.click();

        System.out.println("The deletion is successfully canceled!");
    }

}
