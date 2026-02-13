package core.base;

import core.config.ConfigReader;
import core.context.ContextManager;
import core.driver.DriverFactory;
import core.driver.DriverManager;
import core.utils.ActionUtils;
import core.utils.AllureResultsManager;
import core.utils.JsonContext;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeSuite(alwaysRun = true)
    public void prepareAllureResults() {
        AllureResultsManager.prepareAllureResultsDirectory();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
        driver.get(ConfigReader.get("url"));
        if (!ConfigReader.getConfigAsBoolean("headless")) {
            driver.manage().window().maximize();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result != null && result.getStatus() == ITestResult.FAILURE) {
            ActionUtils.captureScreenshot(DriverManager.getDriver(), "failure_" + result.getMethod().getMethodName());
        }
        JsonContext.clear();
        ContextManager.cleanContext();
        DriverManager.quitDriver();
    }
}
