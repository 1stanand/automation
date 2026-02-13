package pages.leave;

import core.base.BasePage;
import org.openqa.selenium.By;

public class LeavePage extends BasePage {
    private final By leavePageHeader = By.xpath("//h6[contains(normalize-space(), 'Leave')]");

    @Override
    public boolean isPageLoaded() {
        return wait.isVisible(leavePageHeader);
    }
}
