package tests;

import core.base.BaseTest;
import core.utils.JsonContext;
import core.utils.JsonUtils;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashBoardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    private void loginAsValidUser() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("validUser.username");
        String password = JsonContext.get("validUser.password");
        login.login(username, password);
        Assert.assertTrue(login.assertSuccessLogin());
    }

    @Test
    public void validLoginTest() {
        loginAsValidUser();
    }

    @Test
    public void invalidLoginTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("invalidUser.username");
        String password = JsonContext.get("invalidUser.password");
        login.login(username, password);
        Assert.assertFalse(login.assertSuccessLogin());
    }

    @Test
    public void invalidUsernameTest() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("invalidUsername.username");
        String password = JsonContext.get("invalidUsername.password");
        login.login(username, password);
        Assert.assertFalse(login.assertSuccessLogin());
    }

    @Test
    public void dashBoardButtonsShouldBeVisible() {
        loginAsValidUser();
        DashBoardPage dash = new DashBoardPage();
        dash.verifyQuickLaunchMenu();
    }

}
