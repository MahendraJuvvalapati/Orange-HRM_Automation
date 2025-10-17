package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    // ThreadLocal WebDriver for parallel execution
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected Properties config;
    protected Logger log = LogManager.getLogger(BaseTest.class);

    // Getter for thread-local WebDriver
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    @BeforeMethod
    public void setUp() {
        log.info("Loading config.properties file");
        loadConfig();

        String browser = config.getProperty("browser", "chrome");
        log.info("Browser selected: " + browser);

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("intl.accept_languages", "en,en_US");
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--lang=en");
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                log.info("Chrome browser launched with English language");
                break;
            }
            case "firefox": {
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages", "en-US");
                FirefoxOptions options = new FirefoxOptions();
                options.setProfile(profile);

                driver = new FirefoxDriver(options);
                log.info("Firefox browser launched with English language");
                break;
            }
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("intl.accept_languages", "en,en_US");
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--lang=en");

                driver = new EdgeDriver(options);
                log.info("Edge browser launched with English language");
                break;
            }
            default:
                log.error("Invalid browser specified: " + browser);
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();
        log.info("Browser window maximized");
        driver.manage().deleteAllCookies();
        log.info("Browser cookies cleared");

        String url = config.getProperty("baseUrl");
        driver.get(url);
        log.info("Navigated to URL: " + url);

        // Set driver for current thread
        driverThread.set(driver);
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
            driverThread.remove(); // Remove thread-local driver
        }
    }

    private void loadConfig() {
        config = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            config.load(fis);
            log.info("Config loaded successfully");
        } catch (IOException e) {
            log.error("Failed to load config.properties file", e);
            throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }
}
