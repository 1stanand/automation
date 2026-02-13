package tests.dashboard;

import core.base.BaseTest;
import core.utils.JsonContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminPage;
import pages.common.SidePanel;
import pages.dashboard.DashboardPage;
import pages.leave.LeavePage;
import pages.login.LoginPage;
import pages.pim.PimPage;

public class DashboardTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    private void loginAsValidUser() {
        LoginPage loginPage = new LoginPage();
        JsonContext.use("login.json", "validUser.admin");
        loginPage.login(JsonContext.get("username"), JsonContext.get("password"));
        Assert.assertTrue(loginPage.isLoggedIn(), "Expected user to be logged in before module tests.");
    }

    @Test(groups = { "smoke" }, invocationCount = 2)
    public void dashboardButtonsShouldBeVisible() {
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.areQuickLaunchButtonsVisible(),
                "Not all Dashboard quick-launch buttons are visible.");
    }

    @Test(groups = { "smoke" })
    public void verifyMyLeaveNavigation() {
        SidePanel sidePanel = new SidePanel();
        LeavePage leavePage = sidePanel.openLeave();
        Assert.assertTrue(leavePage.isPageLoaded(), "Failed to navigate to Leave page.");
    }

    @Test(groups = { "smoke", "regression" })
    public void verifyAdminPageNavigation() {
        SidePanel sidePanel = new SidePanel();
        AdminPage adminPage = sidePanel.openAdmin();
        Assert.assertTrue(adminPage.isPageLoaded(), "Failed to navigate to Admin page.");
    }

    @Test(groups = { "regression" }, dependsOnMethods = "verifyAdminPageNavigation")
    public void verifyAddUser() {
        SidePanel sidePanel = new SidePanel();
        AdminPage adminPage = sidePanel.openAdmin();
        adminPage.addUser("user_1");
        Assert.assertTrue(adminPage.isUserAddedSuccessfully(), "User was not added successfully.");
        Assert.assertTrue(adminPage.isGeneratedUserVisibleInTable(), "Generated user row was not found in table.");
    }

    @Test(groups = { "regression" })
    public void addEmployee() {
        SidePanel sidePanel = new SidePanel();
        PimPage pimPage = sidePanel.openPim();
        pimPage.addEmployee("PIM.employee_1");
        Assert.assertTrue(pimPage.isEmployeeAddedSuccessfully(), "Employee was not added successfully.");
        Assert.assertTrue(pimPage.areEmployeeDetailsDisplayed(), "Employee details were not displayed correctly.");
    }
}
