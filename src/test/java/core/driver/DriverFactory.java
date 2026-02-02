package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import core.config.ConfigReader;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getConfigAsBoolean("headless");

        switch (browser) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless)
                    edgeOptions.addArguments("--headless=new");
                return new EdgeDriver(edgeOptions);
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments("--window-size=1920,1080");
                return new ChromeDriver(chromeOptions);
        }
    }
}
