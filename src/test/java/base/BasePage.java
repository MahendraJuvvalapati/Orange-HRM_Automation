package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log = LogManager.getLogger(BasePage.class);

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("BasePage initialized with WebDriver");
    }

	// ------------- Browser Operations -------------
    public void openUrl(String url) {
        log.info("Navigating to URL: " + url);
        driver.get(url);
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        log.info("Current page title: " + title);
        return title;
    }

    public void refreshPage() {
        log.info("Refreshing page");
        driver.navigate().refresh();
    }

    public void goBack() {
        log.info("Navigating back");
        driver.navigate().back();
    }

    public void goForward() {
        log.info("Navigating forward");
        driver.navigate().forward();
    }

    public void quitBrowser() {
        if (driver != null) {
            log.info("Closing browser");
            driver.quit();
        }
    }

    public void closeBrowser() {
        if (driver != null) {
            log.info("Closing current browser window");
            driver.close();
        }
    }

    // ------------- Element Actions -------------
    public void click(By locator) {
        log.info("Clicking on element: " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        log.debug("Clicked element successfully");
    }

    public void type(By locator, String text) {
        log.info("Typing '" + text + "' into element: " + locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        performTextAction(locator, "clear");
        element.sendKeys(text);
        log.debug("Text typed successfully");
    }

    public String getText(By locator) {
        log.info("Getting text from element: " + locator);
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        log.debug("Text obtained: " + text);
        return text;
    }

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

    public boolean isEnabled(By locator) {
        log.info("Checking if element is enabled: " + locator);
        boolean enabled = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isEnabled();
        log.debug("Element enabled: " + enabled);
        return enabled;
    }

    public boolean isSelected(By locator) {
        log.info("Checking if element is selected: " + locator);
        boolean selected = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isSelected();
        log.debug("Element selected: " + selected);
        return selected;
    }

    public void clearField(By locator) {
        log.info("Clearing field: " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
    }

    // ------------- Dropdowns -------------
    public void selectFromDropdownByVisibleText(WebElement element, String visibleText) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            log.info("Selected '" + visibleText + "' from dropdown");
        } catch (Exception e) {
            log.error("Failed to select '" + visibleText + "' from dropdown", e);
            throw e;
        }
    }

    public void selectFromDropdownByValue(WebElement element, String value) {
        try {
            Select select = new Select(element);
            select.selectByValue(value);
            log.info("Selected value '" + value + "' from dropdown");
        } catch (Exception e) {
            log.error("Failed to select value '" + value + "' from dropdown", e);
            throw e;
        }
    }

    public void selectFromDropdownByIndex(WebElement element, int index) {
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
            log.info("Selected index '" + index + "' from dropdown");
        } catch (Exception e) {
            log.error("Failed to select index '" + index + "' from dropdown", e);
            throw e;
        }
    }

    public void selectCustomDropdownOption(WebElement dropdown, List<WebElement> options, String valueToSelect) {
        try {
            dropdown.click();
            boolean found = false;
            for (WebElement option : options) {
                if (option.getText().equals(valueToSelect)) {
                    option.click();
                    log.info("Selected '" + valueToSelect + "' from custom dropdown");
                    found = true;
                    break;
                }
            }
            if (!found) log.warn("Option '" + valueToSelect + "' not found in custom dropdown");
        } catch (Exception e) {
            log.error("Failed to select '" + valueToSelect + "' from custom dropdown", e);
            throw e;
        }
    }

    // ------------- Waits -------------
    public void waitForVisibility(By locator, int seconds) {
        log.info("Waiting for visibility of element: " + locator);
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClickability(By locator, int seconds) {
        log.info("Waiting for element to be clickable: " + locator);
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ------------- Alerts -------------
    public void acceptAlert() {
        try {
            log.info("Accepting alert");
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (Exception e) {
            log.error("No alert to accept", e);
        }
    }

    public void dismissAlert() {
        try {
            log.info("Dismissing alert");
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        } catch (Exception e) {
            log.error("No alert to dismiss", e);
        }
    }

    public String getAlertText() {
        try {
            log.info("Getting alert text");
            return wait.until(ExpectedConditions.alertIsPresent()).getText();
        } catch (Exception e) {
            log.error("No alert present", e);
            return null;
        }
    }

    // ------------- Frames -------------
    public void switchToFrame(By locator) {
        log.info("Switching to frame: " + locator);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToDefaultContent() {
        log.info("Switching to default content");
        driver.switchTo().defaultContent();
    }

    // ------------- JavaScript / Scrolling -------------
    public void scrollIntoView(By locator) {
        log.info("Scrolling into view: " + locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollBy(int x, int y) {
        log.info("Scrolling by x=" + x + ", y=" + y);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public void clickWithJS(By locator) {
        log.info("Clicking with JS: " + locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void typeWithJS(By locator, String text) {
        log.info("Typing with JS '" + text + "' into: " + locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", element);
    }

    // ------------- Miscellaneous -------------
    public void hoverOverElement(By locator) {
        log.info("Hovering over element: " + locator);
        WebElement element = driver.findElement(locator);
        new org.openqa.selenium.interactions.Actions(driver)
                .moveToElement(element)
                .perform();
    }

    public WebElement getElement(By locator) {
        log.info("Getting an elements: " + locator);
        return driver.findElement(locator);
    }
    
    public List<WebElement> getElements(By locator) {
        log.info("Getting list of elements: " + locator);
        return driver.findElements(locator);
    }
    
    public By findElementBylabelText(String visibleText) {
        return By.xpath("//*[normalize-space(text())='" + visibleText + "']/parent::*/following-sibling::*[1]");
    }
    
    public void performTextAction(By locator, String action) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();

            switch (action.toLowerCase()) {
                case "clear":
                    element.sendKeys(Keys.CONTROL, "a");
                    element.sendKeys(Keys.DELETE);
                    break;

                case "delete":
                    element.sendKeys(Keys.CONTROL, "a");
                    element.sendKeys(Keys.BACK_SPACE);
                    break;

                case "copy":
                    element.sendKeys(Keys.CONTROL, "a");
                    element.sendKeys(Keys.CONTROL, "c");
                    break;

                case "paste":
                    element.sendKeys(Keys.CONTROL, "v");
                    break;

                case "js-clear":
                	((JavascriptExecutor) driver).executeScript(
                        "arguments[0].value=''; arguments[0].dispatchEvent(new Event('input', {bubbles:true}));",
                        element
                    );
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported action type: " + action);
            }

        } catch (TimeoutException e) {
            log.error("⏳ Timeout: Element not found for locator: " + locator, e);
        } catch (Exception e) {
        	log.error("Failed to perform action '" + action + "' on element: " + locator, e.getMessage());
        }
    }
}
