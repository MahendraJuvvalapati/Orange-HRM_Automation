package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DirectoryPage;
import pages.LoginPage;

public class DirectoryTest extends BaseTest {
	
	private Logger log = LogManager.getLogger(DirectoryTest.class);
	
	@Test
	public void searchForEmployeeInDirectory()
	{
		LoginPage loginPage = new LoginPage(getDriver());
		log.info("Performing login with valid credentials");
		boolean isLoggedIn = loginPage.loginToApplication("Admin", "admin123");

		log.info("Verifying login result");
		Assert.assertTrue(isLoggedIn, "Login Failed....");
		
		DirectoryPage directoryPage=new DirectoryPage(getDriver());
		directoryPage.navigateToDirectoryPage();
		directoryPage.enterEmpName("a");
		directoryPage.selectEmployee();
		directoryPage.clickOnSearch();
		
		boolean isNameVisible=directoryPage.verifyEmpNameInRecords();
		log.info("Verifying Employee name in result");
		Assert.assertTrue(isNameVisible,"Employee name is visible");
		
		boolean isOneRecordVisible=directoryPage.verifyRecordsFound();
		log.info("Verifying Records found in result");
		Assert.assertTrue(isOneRecordVisible,"Multiple Records found");
		
		directoryPage.clickOnRecord();
		
		boolean isNameVisibleInExpRecord=directoryPage.verifyEmpNameInExpandedRecords();
		log.info("Verifying Employee name in expanded result");
		Assert.assertTrue(isNameVisibleInExpRecord,"Employee name is visible");
		
		boolean isExpandArrowWorking=directoryPage.validateExpandRecord();
		log.info("Verifying Arrow  in expanded result");
		Assert.assertTrue(isExpandArrowWorking,"Arrow is not working");
	}

}
