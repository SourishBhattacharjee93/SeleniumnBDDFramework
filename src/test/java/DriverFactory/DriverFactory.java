package DriverFactory;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private static WebDriver initDriver() {

        WebDriver driver = null;
        String browser = null;
        try {
            Properties prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Properties/config.properties");
            prop.load(input);
            browser = prop.getProperty("browser").toUpperCase().trim();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

    }

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            webDriver.set(initDriver());
        }
        return webDriver.get();
    }

    public static WebDriver quitDriver() {
        WebDriver driver = webDriver.get();
        if (driver != null) {
            driver.quit();
            webDriver.remove();
        }
        return driver;
    }

}
