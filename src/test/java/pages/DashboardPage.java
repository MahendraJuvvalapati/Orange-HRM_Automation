package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class DashboardPage extends BasePage {

    private Logger log = LogManager.getLogger(DashboardPage.class); // Logger instance

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

	private By heading = By.xpath("//h6[normalize-space()='Dashboard']");
    private By profileImage = By.xpath("//img[@class='oxd-userdropdown-img']");
    private By myActions = By.xpath("//p[normalize-space()='My Actions']");
    private By timeAtWork = By.xpath("//p[normalize-space()='Time at Work']");
    private By quickLaunch = By.xpath("//p[normalize-space()='Quick Launch']");
    private By buzzLatestPosts = By.xpath("//p[normalize-space()='Buzz Latest Posts']");
    private By empOnLeaveToday = By.xpath("//p[normalize-space()='Employees on Leave Today']");
    private By empDistributionBySubUnit = By.xpath("//p[normalize-space()='Employee Distribution by Sub Unit']");
    private By empDistributionByLocation = By.xpath("//p[normalize-space()='Employee Distribution by Location']");
    private By profileDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    private By logOut = By.xpath("//a[normalize-space()='Logout']");

    public boolean isDashboardDisplayed() {
        log.info("Checking if Dashboard heading is displayed");
        boolean displayed = isDisplayed(heading);
        log.info("Dashboard heading displayed: " + displayed);
        return displayed;
    }

    public boolean isProfileImageDisplayed() {
        log.info("Checking if profile image is displayed");
        boolean displayed = isDisplayed(profileImage);
        log.info("Profile image displayed: " + displayed);
        return displayed;
    }

    public boolean isAllWidgetsDisplayed() {
        log.info("Checking if all widgets on Dashboard are displayed");
        boolean allDisplayed = isDisplayed(myActions) &&
                               isDisplayed(timeAtWork) &&
                               isDisplayed(quickLaunch) &&
                               isDisplayed(buzzLatestPosts) &&
                               isDisplayed(empOnLeaveToday) &&
                               isDisplayed(empDistributionBySubUnit) &&
                               isDisplayed(empDistributionByLocation);
        log.info("All widgets displayed: " + allDisplayed);
        return allDisplayed;
    }

    public boolean clickOnLogout() {
        log.info("Performing logout action");
        click(profileDropdown); // BasePage logs click
        click(logOut);           // BasePage logs click

        boolean loggedOut = !isDashboardDisplayed();
        if (loggedOut) {
            log.info("Logout successful");
        } else {
            log.warn("Logout may have failed");
        }
        return loggedOut;
    }
}
