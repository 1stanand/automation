package core.utils;

import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;

import core.config.ConfigReader;
import io.qameta.allure.Allure;

public class ActionUtils {
    private final WebDriver driver;
    private final WaitUtils wait;
    private final JavascriptExecutor js;

    public ActionUtils(WebDriver webDriver) {
        driver = webDriver;
        wait = new WaitUtils(webDriver);
        js = (JavascriptExecutor) driver;
    }

    public static void captureScreenshot(WebDriver driver) {
        captureScreenshot(driver, "step");
    }

    public static void captureScreenshot(WebDriver driver, String screenshotLabel) {
        if (!ConfigReader.getConfigAsBoolean("screenshot")) {
            return;
        }
        if (driver == null) {
            return;
        }

        String testName = resolveCurrentTestName();
        String safeTestName = sanitize(testName);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        String hourMinute = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss_SSS"));
        String safeLabel = sanitize(screenshotLabel == null ? "step" : screenshotLabel);
        String screenshotName = safeLabel + "_" + time + "_" + Thread.currentThread().threadId() + ".png";
        Path screenshotDir = Path.of(System.getProperty("user.dir"), "target", "screenshots", date, safeTestName,
                hourMinute);

        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.createDirectories(screenshotDir);
            Files.write(screenshotDir.resolve(screenshotName), screenshotBytes);
            Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
        } catch (Exception e) {
            System.err.println("Unable to capture screenshot: " + e.getMessage());
        }
    }

    private static String resolveCurrentTestName() {
        ITestResult testResult = Reporter.getCurrentTestResult();
        if (testResult != null && testResult.getMethod() != null) {
            return testResult.getMethod().getMethodName();
        }
        return "unknown_test";
    }

    private static String sanitize(String value) {
        return value.replaceAll("[^a-zA-Z0-9._-]", "_");
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void clickElement(By locator) {
        wait.waitForClickability(locator);
        scrollElementIntoView(locator);
        find(locator).click();
        captureScreenshot(driver);
    }

    public void inputText(By locator, String text) {
        wait.waitForClickability(locator);
        scrollElementIntoView(locator);
        find(locator).clear();
        find(locator).sendKeys(text);
        captureScreenshot(driver);
    }

    public void javaScriptClick(By locator) {
        wait.waitForClickability(locator);
        js.executeScript("arguments[0].click();", find(locator));
        captureScreenshot(driver);
    }

    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("Alert Was not Present");
        }
    }

    public boolean elementShouldBeVisible(By locator) {
        scrollElementIntoView(locator);
        return wait.isVisible(locator);
    }

    public void scrollElementIntoView(By locator) {
        wait.waitForVisibility(locator);
        WebElement element = find(locator);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        captureScreenshot(driver);
    }

    public void selectFromCustomDropdown(By locator, String text) {
        wait.waitForClickability(locator);
        clickElement(locator);
        captureScreenshot(driver);
        By optionToSelect = By.xpath(
                "//div[@role='option']//span[normalize-space()='" + text + "']");

        wait.waitForClickability(optionToSelect);
        clickElement(optionToSelect);
        captureScreenshot(driver);
    }

    public void selectFromAutComplete(By locator, String text) {
        wait.waitForClickability(locator);
        inputText(locator, text);
        By suggestion = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-dropdown')]//span[contains(normalize-space(),'" + text.trim()
                        + "')]");
        wait.waitForClickability(suggestion);
        clickElement(suggestion);
        captureScreenshot(driver);
    }

    public String generateRandomString(int length) {
        final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new SecureRandom();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return builder.toString();
    }

    public String generateRandomDigits(int length) {
        final String DIGITS = "0123456789";
        Random random = new SecureRandom();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return builder.toString();
    }
}
