package core.base;

import core.config.ConfigReader;
import core.context.ContextManager;
import core.driver.DriverFactory;
import core.driver.DriverManager;
import core.utils.JsonContext;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown() {
        JsonContext.clear();
        ContextManager.cleanContext();
        DriverManager.quitDriver();
    }
}
