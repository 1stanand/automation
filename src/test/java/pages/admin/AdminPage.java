package pages.admin;

import core.base.BasePage;
import core.context.ContextManager;
import core.context.TestContext;
import core.utils.JsonContext;
import org.openqa.selenium.By;

public class AdminPage extends BasePage {
    private static final String GENERATED_USERNAME_KEY = "generatedUsername";

    private final By adminPageHeader = By.cssSelector("h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
    private final By addUserButton = By.cssSelector("button[type='button'].oxd-button.oxd-button--medium.oxd-button--secondary");
    private final By roleDropdown = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]");
    private final By statusDropdown = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]");
    private final By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By usernameInput = By.xpath("//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By passwordInput = By.xpath("//label[normalize-space()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input[@type='password']");
    private final By confirmPasswordInput = By.xpath("//label[normalize-space()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input[@type='password']");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By successMessage = By.xpath("//div[@id='oxd-toaster_1']//p[text()='Successfully Saved']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(adminPageHeader);
    }

    public void addUser(String userKey) {
        waitForPage();
        JsonContext.use("addUsers.json", userKey);

        String role = JsonContext.get("role");
        String employeeName = JsonContext.get("name");
        String status = JsonContext.get("status");
        String usernameSuffix = JsonContext.get("username");
        String password = JsonContext.get("password");
        String generatedUsername = action.generateRandomString(5) + usernameSuffix;

        TestContext context = ContextManager.get();
        context.put(GENERATED_USERNAME_KEY, generatedUsername);

        action.clickElement(addUserButton);
        action.selectFromCustomDropdown(roleDropdown, role);
        action.selectFromCustomDropdown(statusDropdown, status);
        action.selectFromAutComplete(employeeNameInput, employeeName);
        action.inputText(usernameInput, generatedUsername);
        action.inputText(passwordInput, password);
        action.inputText(confirmPasswordInput, password);
        action.clickElement(saveButton);
    }

    public boolean isUserAddedSuccessfully() {
        return wait.isVisible(successMessage);
    }

    public boolean isGeneratedUserVisibleInTable() {
        waitForPage();
        String generatedUsername = ContextManager.get().get(GENERATED_USERNAME_KEY);
        By userTableEntry = By.xpath(
                "//div[@class='orangehrm-container']//div[contains(text(),'" + generatedUsername + "')]");
        return wait.isVisible(userTableEntry);
    }
}
