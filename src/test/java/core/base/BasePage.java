package core.base;

import core.driver.DriverManager;
import core.utils.ActionUtils;
import core.utils.WaitUtils;
import org.openqa.selenium.WebDriver;

public abstract class BasePage implements Loadable {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected ActionUtils action;

    public BasePage(){
        this.driver = DriverManager.getDriver();
        this.wait = new WaitUtils(driver);
        this.action = new ActionUtils(driver);
    }

    protected void waitForPage(){
        wait.untilTrue(this::isPageLoaded);
    }
}
