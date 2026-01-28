package pages.dashboard;

import core.base.BasePage;
import org.openqa.selenium.By;

public class DashBoardPage extends BasePage {

    private final By admin = By.cssSelector("a[class='oxd-main-menu-item']");
    private final By assignLeaveButton = By.xpath("//button[@title='Assign Leave']");
    private final By leaveListButton = By.xpath("//button[@title='Leave List']");
    private final By timeSheetButton = By.xpath("//button[@title='Timesheets']");
    private final By applyLeaveButton = By.xpath("//button[@title='Apply Leave']");
    private final By myLeaveButton = By.xpath("//button[@title='My Leave']");
    private final By myTimeSheetButton = By.xpath("//button[@title='My Timesheet']");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(admin);
    }

    public void verifyQuickLaunchMenu(){
        waitForPage();
        wait.waitForVisibility(assignLeaveButton);
        action.elementShouldBeVisible(assignLeaveButton);
        action.elementShouldBeVisible(leaveListButton);
        action.elementShouldBeVisible(timeSheetButton);
        action.elementShouldBeVisible(applyLeaveButton);
        action.elementShouldBeVisible(myLeaveButton);
        action.elementShouldBeVisible(myTimeSheetButton);
    }
}
