package tests;

import core.base.BaseTest;
import core.utils.JsonContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("validUser.username");
        String password = JsonContext.get("validUser.password");
        login.login(username, password);
        Assert.assertTrue(login.assertSuccessLogin(), "Login failed.");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("invalidUser.username");
        String password = JsonContext.get("invalidUser.password");
        login.login(username, password);
        Assert.assertFalse(login.assertSuccessLogin(), "Invalid Login Validation failed.");
    }

    @Test
    public void invalidUsernameTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("invalidUsername.username");
        String password = JsonContext.get("invalidUsername.password");
        login.login(username, password);
        Assert.assertFalse(login.assertSuccessLogin(), "Invalid Username Validation failed.");
    }
}
