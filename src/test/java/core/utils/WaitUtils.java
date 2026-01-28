package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;

public class WaitUtils {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.driver = driver;
    }

    public void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ActionUtils.captureScreenshot(driver);
    }

    public void waitForClickability(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        ActionUtils.captureScreenshot(driver);
    }

    public void waitForPageIdle() {
        wait.until(d -> (Boolean) ((JavascriptExecutor) d).executeScript(
                "return window.getAllAngularTestabilities && " +
                        "window.getAllAngularTestabilities().every(t => t.isStable());"));
        ActionUtils.captureScreenshot(driver);

    }

    public void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ActionUtils.captureScreenshot(driver);
    }

    public boolean isVisible(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            ActionUtils.captureScreenshot(driver);
        }
    }

    public boolean isPresent(By locator) {
        ActionUtils.captureScreenshot(driver);
        return !driver.findElements(locator).isEmpty();
    }

    public void untilTrue(BooleanSupplier condition) {
        ActionUtils.captureScreenshot(driver);
        wait.until(driver -> condition.getAsBoolean());
    }

}
