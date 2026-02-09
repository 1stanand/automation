package core.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;

import core.config.ConfigReader;

public class WaitUtils {
    private final WebDriverWait wait;
    private final WebDriverWait visibilityWait;
    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        int timeoutSeconds = ConfigReader.getConfigAsInt("timeout");
        if (timeoutSeconds <= 0) {
            timeoutSeconds = 30;
        }
        int isVisibleWaitSeconds = ConfigReader.getConfigAsInt("isVisibleWait");
        if (isVisibleWaitSeconds <= 0) {
            isVisibleWaitSeconds = timeoutSeconds;
        }
        isVisibleWaitSeconds = Math.max(1, isVisibleWaitSeconds);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        visibilityWait = new WebDriverWait(driver, Duration.ofSeconds(isVisibleWaitSeconds));
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
        wait.until(d -> {
            Object result = ((JavascriptExecutor) d).executeScript(
                    "return window.getAllAngularTestabilities ? " +
                            "window.getAllAngularTestabilities().every(t => t.isStable()) : true;");
            return Boolean.TRUE.equals(result);
        });
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
            visibilityWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        } finally {
            ActionUtils.captureScreenshot(driver);
        }
    }

    public boolean isVisible(By locator, int seconds) {
        int timeoutSeconds = Math.max(1, seconds);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
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
