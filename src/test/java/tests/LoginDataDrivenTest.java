package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.ExcelUtils;

public class LoginDataDrivenTest extends BaseTest {

    // 🔹 DataProvider reading Excel login data
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testData/credentials.xlsx";
        return ExcelUtils.loginData(filePath, "Sheet1");
    }

    // 🔹 Test method using Excel data
    @Test(dataProvider = "loginData")
    public void loginWithExcelData(String username, String password) {
        log.info("🟢 Starting login test for user: {}", username);

        LoginPage loginPage = new LoginPage(getDriver());
        boolean isLoggedIn = loginPage.loginToApplication(username, password);

        if(isLoggedIn) {
            log.info("✅ Login successful for user: {}", username);
            DashboardPage dashboardPage =new DashboardPage(getDriver());
            dashboardPage.clickOnLogout();
            Assert.assertTrue(isLoggedIn, "Login failed for user: " + username);
        } else {
        	boolean isLoginFailed=loginPage.verifyInvalidCredentialsMessage();
            log.warn("❌ Login failed for user: {}", username);
            Assert.assertTrue(isLoginFailed, "Login failed for user: " + username);
        }

        
    }
}
