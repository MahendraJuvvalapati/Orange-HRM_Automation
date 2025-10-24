package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AdminPage;
import pages.LoginPage;

public class AdminTest extends BaseTest {

	private Logger log = LogManager.getLogger(AdminTest.class);

	@Test(priority = 1)
	public void createNewAdmin() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());

		log.info("Performing login with valid credentials");
		boolean isLoggedIn = loginPage.loginToApplication("Admin", "admin123");

		log.info("Verifying login result");
		Assert.assertTrue(isLoggedIn, "Login Failed....");
		
		AdminPage adminPage=new AdminPage(getDriver());
		
		adminPage.navigateToAdmin();
		adminPage.clickOnAddBtn();
		adminPage.selectAdminUserRole();
		adminPage.selectEmpName("a");
		adminPage.selectStatus();
		adminPage.enterUserName("Mahendra");
		adminPage.enterPassword("Mahe@1234");
		adminPage.enterConfirmPassword("Mahe@1234");
		adminPage.clickOnSave();
		boolean isCreated=adminPage.isAdminUserCreated();
		
		
		log.info("Verifying user creation result");
		Assert.assertTrue(isCreated,"Admin User Not Created..");
		log.info("User created succesfully.");
		Thread.sleep(4000);
	 
	}
	
	@Test(dependsOnMethods = "createNewAdmin" )
	public void editAdminUser() throws InterruptedException
	{
		
		AdminPage adminPage=new AdminPage(getDriver());

		adminPage.enterUserNameIntoSearch("Mahendra");
		adminPage.clickOnSearch();
		adminPage.clickOnEdit();
		adminPage.enterUserName("Mahendra Juvvalapati");
		adminPage.clickOnSave();
		
		boolean isSuccess=adminPage.isSuccessMessageDisplayed();
		log.info("Verifying user edit result");
		Assert.assertTrue(isSuccess,"Admin User Not Created..");
		log.info("User edited succesfully.");
		Thread.sleep(4000);
		adminPage.enterUserNameIntoSearch("Mahendra Juvvalapati");
		adminPage.clickOnSearch();
		boolean isChanged=adminPage.verifyUserName();
		log.info("Verifying userName change result");
		Assert.assertTrue(isChanged,"Admin User Not Created..");
		log.info("UserName changed succesfully.");
	}
	
	@Test(dependsOnMethods = "editAdminUser" )
	public void deleteAdminUser()
	{
		
		AdminPage adminPage=new AdminPage(getDriver());

		adminPage.enterUserNameIntoSearch("Mahendra Juvvalapati");
		adminPage.clickOnSearch();
		adminPage.selectUserCheckBox();
		adminPage.clickOnDelete();
		adminPage.clickOnConfirmDelete();
		
		boolean isDeleted=adminPage.isSuccessMessageDisplayed();
		log.info("Verifying user deletion result");
		Assert.assertTrue(isDeleted,"User not deleted..");
		log.info("User deleted succesfully.");
		
	}

}
