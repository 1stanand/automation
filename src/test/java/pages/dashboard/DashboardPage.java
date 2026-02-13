package pages.dashboard;

import core.base.BasePage;
import org.openqa.selenium.By;

public class DashBoardPage extends BasePage {
    private final By dashBoard_Heading = By.xpath("//h6[contains(normalize-space(), 'Dashboard')]");
    private final By adminButton = By.xpath("//a[contains(normalize-space(),'Admin')]");
    private final By assignLeaveButton = By.xpath("//button[@title='Assign Leave']");
    private final By leaveListButton = By.xpath("//button[@title='Leave List']");
    private final By timeSheetButton = By.xpath("//button[@title='Timesheets']");
    private final By applyLeaveButton = By.xpath("//button[@title='Apply Leave']");
    private final By myLeaveButton = By.xpath("//button[@title='My Leave']");
    private final By myTimeSheetButton = By.xpath("//button[@title='My Timesheet']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(dashBoard_Heading);
    }

    public boolean areQuickLaunchButtonsVisible() {
        waitForPage();
        wait.waitForVisibility(assignLeaveButton);
        return action.elementShouldBeVisible(assignLeaveButton) &&
                action.elementShouldBeVisible(leaveListButton) &&
                action.elementShouldBeVisible(timeSheetButton) &&
                action.elementShouldBeVisible(applyLeaveButton) &&
                action.elementShouldBeVisible(myLeaveButton) &&
                action.elementShouldBeVisible(myTimeSheetButton);
    }

    public LeavePage clickMyLeaveButton() {
        wait.waitForClickability(myLeaveButton);
        action.clickElement(myLeaveButton);
        return new LeavePage();
    }

    public AdminPage clickAdminButton() {
        wait.waitForClickability(adminButton);
        action.clickElement(adminButton);
        return new AdminPage();
    }

}
