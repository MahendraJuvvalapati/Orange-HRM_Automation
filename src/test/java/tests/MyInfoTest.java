package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.MyInfoPage;

public class MyInfoTest extends BaseTest{

	private Logger log = LogManager.getLogger(MyInfoTest.class);
	
	@Test(priority = 1)
	public void changeExistingInfo()
	{
		LoginPage loginPage = new LoginPage(getDriver());

		log.info("Performing login with valid credentials");
		boolean isLoggedIn = loginPage.loginToApplication("Admin", "admin123");

		log.info("Verifying login result");
		Assert.assertTrue(isLoggedIn, "Login Failed....");
		
		MyInfoPage myInfoPage=new MyInfoPage(getDriver());
		myInfoPage.navigateToMyInfo();
		myInfoPage.enterFirstName("Ram");
		myInfoPage.enterLastName("Charan");
		myInfoPage.enterEmpId("1113");
		myInfoPage.clickOnSave();
		boolean isSuccess=myInfoPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "No success Message");
		
		myInfoPage.refreshPage();
		boolean isChanged=myInfoPage.validateProfileNameChange("Ram Charan");
		
		Assert.assertTrue(isChanged, "Name not changed");	
	}
	
	@Test(priority = 2)
	public void changeProfileImage() throws InterruptedException
	{
		String imgPath = System.getProperty("user.dir") + "/testData/addPhoto.png";
		MyInfoPage myInfoPage=new MyInfoPage(getDriver());
		myInfoPage.clickOnProfileImage();
		Thread.sleep(3000);
		myInfoPage.clickOnAddImageBtn(imgPath);
		myInfoPage.clickOnSave();
		boolean isSuccess=myInfoPage.isSuccessMessageDisplayed();
		
		log.info("Verifying the profile image changed.");
		Assert.assertTrue(isSuccess, "image is not changed...");
		log.info("The profile image is changed succesfully.");
	}

}
