package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log = LogManager.getLogger(BasePage.class); // Logger instance

    // Constructor initializes driver and page elements
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("BasePage initialized with WebDriver");
    }

    // Open a URL
    public void openUrl(String url) {
        log.info("Navigating to URL: " + url);
        driver.get(url);
    }

    // Click an element
    public void click(By locator) {
        log.info("Clicking on element: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        log.debug("Clicked element successfully");
    }

    // Type text into a field
    public void type(By locator, String text) {
        log.info("Typing '" + text + "' into element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        log.debug("Text typed successfully");
    }

    // Get visible text
    public String getText(By locator) {
        log.info("Getting text from element: " + locator);
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        log.debug("Text obtained: " + text);
        return text;
    }

    // Check if element is visible
    public boolean isDisplayed(By locator) {
        log.info("Checking if element is displayed: " + locator);
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
            log.debug("Element is displayed: " + locator);
            return visible;
        } catch (TimeoutException e) {
            log.warn("Element not visible: " + locator);
            return false;
        }
    }

    // Get page title
    public String getPageTitle() {
        String title = driver.getTitle();
        log.info("Current page title: " + title);
        return title;
    }

    // Safe close
    public void quitBrowser() {
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
        }
    }
}
