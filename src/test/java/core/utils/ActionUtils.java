package core.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public ActionUtils(WebDriver webDriver) {
        driver = webDriver;
        wait = new WaitUtils(webDriver);
    }

    public static void captureScreenshot(WebDriver driver) {
        if (!ConfigReader.getConfigAsBoolean("screenshot")) {
            return;
        }
        String testName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss_SSS"));
        File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots/" + date + "/" + testName + "/");
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

    private WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void clickElement(By locator) {
        wait.waitForClickability(locator);
        find(locator).click();
        captureScreenshot(driver);
    }

    public void inputText(By locator, String text) {
        wait.waitForClickability(locator);
        find(locator).clear();
        find(locator).sendKeys(text);
        captureScreenshot(driver);
    }

    public void javaScriptClick(By locator) {
        wait.waitForClickability(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", locator);
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
        captureScreenshot(driver);
        return find(locator).isDisplayed();

    }

}
