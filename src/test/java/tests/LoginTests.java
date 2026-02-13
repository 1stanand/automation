package tests;

import core.base.BaseTest;
import core.utils.JsonContext;
import pages.login.LoginPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        JsonContext.use("login.json", "validUser.admin");
        return new Object[][] {
                { JsonContext.get("username"), JsonContext.get("password") }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        JsonContext.use("login.json", "invalidUser");
        return new Object[][] {
                { JsonContext.get("scene_1.username"), JsonContext.get("scene_1.password") },
                { JsonContext.get("scene_2.username"), JsonContext.get("scene_2.password") }
        };
    }

    @Test(dataProvider = "validLoginData", groups = { "smoke", "login" })
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed despite valid credentials.");
    }

    @Test(dataProvider = "invalidLoginData", groups = { "regression", "login" })
    public void testInvalidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        Assert.assertFalse(loginPage.isLoggedIn(), "Login succeeded despite invalid credentials.");
        Assert.assertTrue(loginPage.isInvalidCredentialsErrorVisible(),
                "Invalid credentials message was not displayed.");
    }

}
