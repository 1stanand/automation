package pages.dashboard;

import org.openqa.selenium.By;

import core.base.BasePage;
import core.context.ContextManager;
import core.context.TestContext;
import core.utils.JsonContext;

public class AdminPage extends BasePage {
    private final By ADMIN_PAGE_HEADER = By.cssSelector("h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
    private final By ADD_USER_BUTTON = By
            .cssSelector("button[type='button'].oxd-button.oxd-button--medium.oxd-button--secondary");
    private final By ROLE_DROPDOWN = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]");
    private final By STATUS_DD = By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]");
    private final By NAME_INPUT = By.xpath("//input[@placeholder='Type for hints...']");
    private final By USERNAME_INPUT = By
            .xpath("//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input");

    private final By PASSWORD_INPUT = By.xpath(
            "//label[normalize-space()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input[@type='password']");

    private final By CONFIRM_PASSWORD_INPUT = By.xpath(
            "//label[normalize-space()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input[@type='password']");

    private final By SAVE_BUTTON = By.xpath("//button[@type='submit']");
    private final By SUCCESS_MESSAGE = By.xpath("//div[@id='oxd-toaster_1']//p[text()='Successfully Saved']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(ADMIN_PAGE_HEADER);
    }

    public boolean isUserAddedSuccessfully() {
        return wait.isVisible(SUCCESS_MESSAGE);
    }

    public void addUser(String userKey) {
        waitForPage();
        JsonContext.use("addUsers.json", userKey);
        String role = JsonContext.get("role");
        String name = JsonContext.get("name");
        String status = JsonContext.get("status");
        String username = JsonContext.get("username");
        String password = JsonContext.get("password");
        String usernameToInput = action.generateRandomString(5) + username;
        TestContext ctx = ContextManager.get();
        ctx.put("username", usernameToInput);
        action.clickElement(ADD_USER_BUTTON);
        action.selectFromCustomDropdown(ROLE_DROPDOWN, role);
        action.selectFromCustomDropdown(STATUS_DD, status);
        action.selectFromAutComplete(NAME_INPUT, name);
        action.inputText(USERNAME_INPUT, usernameToInput);
        action.inputText(PASSWORD_INPUT, password);
        action.inputText(CONFIRM_PASSWORD_INPUT, password);
        action.clickElement(SAVE_BUTTON);
    }

    public boolean isUserRowDisplayed() {
        waitForPage();
        TestContext ctx = ContextManager.get();
        ctx.get("username");
        By tableEntry = By
                .xpath("//div[@class='orangehrm-container']//div[contains(text(),'" + ctx.get("username") + "')]");
        wait.waitForVisibility(tableEntry);
        return action.elementShouldBeVisible(tableEntry);
    }

}
