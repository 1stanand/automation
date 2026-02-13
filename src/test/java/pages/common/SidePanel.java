package pages.common;

import core.base.BasePage;
import org.openqa.selenium.By;
import pages.admin.AdminPage;
import pages.dashboard.DashboardPage;
import pages.leave.LeavePage;
import pages.pim.PimPage;

public class SidePanel extends BasePage {
    private final By sidePanelContainer = By.cssSelector("aside.oxd-sidepanel");
    private final By dashboardMenu = By.xpath("//nav//span[normalize-space()='Dashboard']");
    private final By adminMenu = By.xpath("//nav//span[normalize-space()='Admin']");
    private final By pimMenu = By.xpath("//nav//span[normalize-space()='PIM']");
    private final By leaveMenu = By.xpath("//nav//span[normalize-space()='Leave']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(sidePanelContainer);
    }

    public DashboardPage openDashboard() {
        waitForPage();
        action.clickElement(dashboardMenu);
        return new DashboardPage();
    }

    public AdminPage openAdmin() {
        waitForPage();
        action.clickElement(adminMenu);
        return new AdminPage();
    }

    public PimPage openPim() {
        waitForPage();
        action.clickElement(pimMenu);
        return new PimPage();
    }

    public LeavePage openLeave() {
        waitForPage();
        action.clickElement(leaveMenu);
        return new LeavePage();
    }
}
