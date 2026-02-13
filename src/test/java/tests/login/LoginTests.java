package tests.login;

import core.base.BaseTest;
import core.utils.JsonContext;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.login.LoginPage;

public class LoginTests extends BaseTest {
    @DataProvider(name = "validLoginData")
    public Object[][] getValidTestData() {
        JsonContext.use("login.json", "validUser.admin");
        return new Object[][] {
                { JsonContext.get("username"), JsonContext.get("password") }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidTestData() {
        JsonContext.use("login.json", "invalidUser");
        return new Object[][] {
                { JsonContext.get("scene_1.username"), JsonContext.get("scene_1.password") },
                { JsonContext.get("scene_2.username"), JsonContext.get("scene_2.password") }
        };
    }

    @Test(dataProvider = "validLoginData", groups = "smoke")
    public void validLogin(String username, String password) {
        LoginPage login = new LoginPage();
        login.login(username, password);
        Assert.assertTrue(login.isLoggedIn(), "Login failed despite valid credentials.");
    }

    @Test(dataProvider = "invalidLoginData", groups = "smoke")
    public void invalidLogin(String username, String password) {
        LoginPage login = new LoginPage();
        login.login(username, password);
        Assert.assertFalse(login.isLoggedIn(), "Login Was Successful even with invalid credentials.");
        Assert.assertTrue(login.isInvalidCredentialsErrorVisible(), "Invalid Credentials Error did not Appear");
    }
}
