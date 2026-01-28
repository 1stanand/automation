package pages;

import core.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By search = By.cssSelector("a[class='oxd-main-menu-item']");

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
        return wait.isVisible(search);
    }

}
