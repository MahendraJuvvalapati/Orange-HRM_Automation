package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private Logger log = LogManager.getLogger(LoginTest.class); // Logger instance

    @Test
    public void verifyLogin() {
        log.info("========== Test Started: verifyLogin ==========");
        
        LoginPage loginPage = new LoginPage(getDriver());

        log.info("Performing login with valid credentials");
        boolean isLoggedIn = loginPage.loginToApplication("Admin", "admin123");

        log.info("Verifying login result");
        Assert.assertTrue(isLoggedIn, "Login Failed....");
        
        log.info("========== Test Finished: verifyLogin ==========");
        
        DashboardPage dashboardPage =new DashboardPage(getDriver());
        
        dashboardPage.clickOnLogout();
    }

    @Test(dependsOnMethods = "verifyLogin")
    public void verifyForgotPassword() {
        log.info("========== Test Started: verifyForgotPassword ==========");
        
        LoginPage loginPage = new LoginPage(getDriver());

        log.info("Performing forgot password flow for user: Admin");
        boolean isResetSuccessful = loginPage.forgotPassword("Admin");

        log.info("Verifying reset password result");
        Assert.assertTrue(isResetSuccessful, "Reset Password Failed.....");

        log.info("========== Test Finished: verifyForgotPassword ==========");
    }
}
