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
        JsonContext.use("login.json", "validUser");
        String username = JsonContext.get("username");
        String password = JsonContext.get("password");
        login.login(username, password);
        Assert.assertTrue(login.assertSuccessLogin(), "Login failed.");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json", "invalidUser");
        String username = JsonContext.get("username");
        String password = JsonContext.get("password");
        login.login(username, password);
        Assert.assertFalse(login.assertSuccessLogin(), "Invalid Login Validation failed.");
    }

    @Test
    public void invalidUsernameTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json", "invalidUsername");
        String username = JsonContext.get("username");
        String password = JsonContext.get("password");
        login.login(username, password);
        Assert.assertFalse(login.assertSuccessLogin(), "Invalid Username Validation failed.");
    }
}
