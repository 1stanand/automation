package tests;

import core.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashBoardPage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage login = new LoginPage();
        login.login("Admin", "admin123");
        Assert.assertTrue(login.assertSuccessLogin());
    }

    @Test
    public void invalidLoginTest() {
        LoginPage login = new LoginPage();
        login.login("Admin", "admin1234567");
        Assert.assertFalse(login.assertSuccessLogin());
    }

    @Test
    public void dashBoardButtonsShouldBeVisible() {
        LoginPage login = new LoginPage();
        login.login("Admin", "admin123");
        Assert.assertTrue(login.assertSuccessLogin());

        DashBoardPage dash = new DashBoardPage();
        dash.verifyQuickLaunchMenu();
    }
}
