package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {

    private Logger log = LogManager.getLogger(DashboardTest.class); // Logger instance

    @Test
    public void verifyDashboardPageElements() {
        log.info("========== Test Started: verifyDashboardPageElements ==========");

        LoginPage loginPage = new LoginPage(getDriver());
        log.info("Logging in with valid credentials");
        boolean isLoggedIn = loginPage.loginToApplication("Admin", "admin123");
        Assert.assertTrue(isLoggedIn, "Login Failed...");

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        log.info("Verifying Dashboard heading visibility");
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard heading not visible.");

        log.info("Verifying profile image visibility");
        Assert.assertTrue(dashboardPage.isProfileImageDisplayed(), "Profile image not visible.");

        log.info("Verifying all Dashboard widgets visibility");
        Assert.assertTrue(dashboardPage.isAllWidgetsDisplayed(), "One or more widgets missing on Dashboard.");

        log.info("========== Test Finished: verifyDashboardPageElements ==========");
    }

    @Test(dependsOnMethods = "verifyDashboardPageElements")
    public void verifyLogout() {
        log.info("========== Test Started: verifyLogout ==========");

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        log.info("Performing logout");
        boolean isLoggedOut = dashboardPage.clickOnLogout();
        Assert.assertTrue(isLoggedOut, "Log Out Failed....");

        log.info("========== Test Finished: verifyLogout ==========");
    }
}
