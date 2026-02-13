package pages.login;

import core.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By userDropdownName = By.cssSelector("p.oxd-userdropdown-name");
    private final By loginErrorMessage = By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(usernameInput);
    }

    public void login(String username, String password) {
        waitForPage();
        action.inputText(usernameInput, username);
        action.inputText(passwordInput, password);
        action.clickElement(loginButton);
    }

    public boolean isLoggedIn() {
        return wait.isVisible(userDropdownName);
    }

    public String getLoginErrorMessage() {
        if (wait.isVisible(loginErrorMessage)) {
            return driver.findElement(loginErrorMessage).getText().trim();
        }
        return "";
    }

    public boolean isInvalidCredentialsErrorVisible() {
        return wait.isVisible(loginErrorMessage)
                && INVALID_CREDENTIALS.equals(driver.findElement(loginErrorMessage).getText().trim());
    }

}
