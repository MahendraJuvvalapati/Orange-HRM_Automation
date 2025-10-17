package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class LoginPage extends BasePage {

    private Logger log = LogManager.getLogger(LoginPage.class); // Logger instance

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By userNameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By invalidCreds = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
    private By forgotPassword = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
    private By resetPassword = By.cssSelector("button[type='submit']");
    private By resetSuccesful = By.xpath("//h6[normalize-space()='Reset Password link sent successfully']");

    // Forgot password flow
    public boolean forgotPassword(String userName) {
        log.info("Initiating forgot password flow for user: " + userName);
        click(forgotPassword); // BasePage logs click
        type(userNameInput, userName); // BasePage logs typing
        click(resetPassword); // BasePage logs click

        boolean success = isDisplayed(resetSuccesful);
        if (success) {
            log.info("Reset password link sent successfully for user: " + userName);
        } else {
            log.warn("Reset password may have failed for user: " + userName);
        }
        return success;
    }

    // Login flow
    public boolean loginToApplication(String userName, String password) {
        log.info("Attempting login for user: " + userName);
        type(userNameInput, userName); // BasePage logs typing
        type(passwordInput, password); // BasePage logs typing
        click(loginBtn); // BasePage logs click

        boolean loginSuccess = !isDisplayed(invalidCreds);
        if (loginSuccess) {
            log.info("Login successful for user: " + userName);
        } else {
            log.warn("Login failed for user: " + userName);
        }
        return loginSuccess;
    }
}
