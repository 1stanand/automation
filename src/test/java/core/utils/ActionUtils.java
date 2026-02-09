package core.utils;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import core.config.ConfigReader;

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
        if (!ConfigReader.getConfigAsBoolean("screenshot")) {
            return;
        }
        String testName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        String hourMinute = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss_SSS"));
        File screenshotDir = new File(
                System.getProperty("user.dir") + "/screenshots/" + date + "/" + testName + "/" + hourMinute + "/");
        String screenshotName = "step_" + time + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(screenshotDir, screenshotName);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        try {
            FileUtils.copyFile(src, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
