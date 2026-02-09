package tests;

import core.base.BaseTest;
import core.utils.JsonContext;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

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

    @Test(dataProvider = "validLoginData")
    public void testValidLogin(String username, String password) {
        LoginPage login = new LoginPage();
        login.login(username, password);
        Assert.assertTrue(login.isLoggedIn(), "Login Failed, Despite Having correct Credentials");
    }

    @Test(dataProvider = "invalidLoginData")
    public void testInvalidLogin(String username, String password) {
        LoginPage login = new LoginPage();
        login.login(username, password);
        Assert.assertFalse(login.isLoggedIn(), "Login Success, Despite Having incorrect Credentials");
        Assert.assertTrue(login.isInvalidCredentialsErrorVisible(), "Error message is not displayed correctly");
    }

}
