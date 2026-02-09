package pages;

import core.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By userDropdownName = By.cssSelector("p.oxd-userdropdown-name");
    private final By loginError = By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(username);
    }

    public void login(String username, String password) {
        waitForPage();
        action.inputText(this.username, username);
        action.inputText(this.password, password);
        action.clickElement(loginBtn);
    }

    public boolean assertSuccessLogin() {
        return isLoggedIn();
    }

    public boolean isLoggedIn() {
        return wait.isVisible(userDropdownName);
    }

    public String getLoginErrorMessage() {
        if (wait.isVisible(loginError)) {
            return driver.findElement(loginError).getText().trim();
        }
        return "";
    }

    public boolean isInvalidCredentialsErrorVisible() {
        return wait.isVisible(loginError)
                && INVALID_CREDENTIALS.equals(driver.findElement(loginError).getText().trim());
    }

}
