package tests.dashboardTests;

import core.base.BaseTest;
import core.utils.JsonContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.dashboard.AdminPage;
import pages.dashboard.DashBoardPage;
import pages.dashboard.LeavePage;

public class DashBoardTests extends BaseTest {

    private void loginAsValidUser() {
        LoginPage login = new LoginPage();
        JsonContext.use("login.json");
        String username = JsonContext.get("validUser.username");
        String password = JsonContext.get("validUser.password");
        login.login(username, password);
        Assert.assertTrue(login.assertSuccessLogin());
    }

    @Test
    public void dashBoardButtonsShouldBeVisible() {
        loginAsValidUser();
        DashBoardPage dash = new DashBoardPage();
        Assert.assertTrue(dash.areQuickLaunchButtonsVisible(), "Not all dashboard Quick Launch buttons are visible.");
    }

    @Test
    public void verifyMyLeaveNavigation() {
        loginAsValidUser();
        DashBoardPage dash = new DashBoardPage();
        LeavePage leavePage = dash.clickMyLeaveButton();
        Assert.assertTrue(leavePage.isPageLoaded(), "Failed to navigate to Leave Page.");
    }

    @Test
    public void verifyAdminPageNavigation() {
        loginAsValidUser();
        DashBoardPage dash = new DashBoardPage();
        AdminPage admin = dash.clickAdminButton();
        Assert.assertTrue(admin.isPageLoaded());
    }

    @Test
    public void verifyAddUser() {
        loginAsValidUser();
        DashBoardPage dash = new DashBoardPage();
        AdminPage admin = dash.clickAdminButton();
        admin.addUser("user_1");
        Assert.assertTrue(admin.isUserAddedSuccessfully(), "User was not added successfully.");
    }
}