package pages.dashboard;

import core.base.BasePage;
import org.openqa.selenium.By;

public class DashboardPage extends BasePage {
    private final By dashboardHeading = By.xpath("//h6[contains(normalize-space(), 'Dashboard')]");
    private final By assignLeaveButton = By.xpath("//button[@title='Assign Leave']");
    private final By leaveListButton = By.xpath("//button[@title='Leave List']");
    private final By timesheetsButton = By.xpath("//button[@title='Timesheets']");
    private final By applyLeaveButton = By.xpath("//button[@title='Apply Leave']");
    private final By myLeaveButton = By.xpath("//button[@title='My Leave']");
    private final By myTimesheetButton = By.xpath("//button[@title='My Timesheet']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(dashboardHeading);
    }

    public boolean areQuickLaunchButtonsVisible() {
        waitForPage();
        return action.elementShouldBeVisible(assignLeaveButton)
                && action.elementShouldBeVisible(leaveListButton)
                && action.elementShouldBeVisible(timesheetsButton)
                && action.elementShouldBeVisible(applyLeaveButton)
                && action.elementShouldBeVisible(myLeaveButton)
                && action.elementShouldBeVisible(myTimesheetButton);
    }
}
