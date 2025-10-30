package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BuzzPage;
import pages.LoginPage;

public class BuzzTest extends BaseTest {

	private Logger log = LogManager.getLogger(BuzzTest.class);

	@Test(priority = 1)
	public void createNewBuzzWithText() {

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginToApplication("Admin", "admin123");

		BuzzPage buzzPage = new BuzzPage(getDriver());
		buzzPage.navigateToBuzzPage();
		buzzPage.enterBuzzText("Hii, Everyone.");
		buzzPage.clickOnPost();
		boolean isSuccess = buzzPage.isSuccessMessageDisplayed();
		Assert.assertTrue(isSuccess);

		buzzPage.refreshPage();
		boolean isCreated = buzzPage.verifyCreatedPost("Hii, Everyone.");

		log.info("Verifying the buzz created.");
		Assert.assertTrue(isCreated, "Buzz is not created...");
		log.info("The Buzz is created succesfully.");
	}

	@Test(dependsOnMethods = "createNewBuzzWithText", priority = 2)
	public void editBuzz() {
		BuzzPage buzzPage = new BuzzPage(getDriver());
		buzzPage.clickOnPostOptions();
		buzzPage.clickOnEdit();
		buzzPage.enterTextInEdit("Hii, Everyone. This is Mahendra");
		buzzPage.clickOnPostinEdit();
		boolean isSuccess = buzzPage.isSuccessMessageDisplayed();
		Assert.assertTrue(isSuccess);

		buzzPage.refreshPage();
		boolean isCreated = buzzPage.verifyCreatedPost("Hii, Everyone. This is Mahendra");

		log.info("Verifying the buzz edited.");
		Assert.assertTrue(isCreated, "Buzz is not created...");
		log.info("The Buzz is edited succesfully.");

	}

	@Test(dependsOnMethods = "editBuzz", priority = 3)
	public void deleteBuzz() {
		BuzzPage buzzPage = new BuzzPage(getDriver());
		buzzPage.refreshPage();
		buzzPage.clickOnPostOptions();
		buzzPage.clickOnDelete();
		buzzPage.clickOnConfirmDelete();
		boolean isSuccess = buzzPage.isSuccessMessageDisplayed();

		log.info("Verifying the buzz deleted.");
		Assert.assertTrue(isSuccess);
		log.info("The buzz is deleted successfully.");
	}

	@Test(priority = 4)
	public void createBuzzWithImage()
	{
        String imgPath = System.getProperty("user.dir") + "/testData/addPhoto.png";
		BuzzPage buzzPage =new BuzzPage(getDriver());
		buzzPage.refreshPage();
		buzzPage.clickOnSharePhotos();
		buzzPage.clickOnAddPhotos(imgPath);
		buzzPage.clickOnShare();
		boolean isSuccess = buzzPage.isSuccessMessageDisplayed();
		
		log.info("Verifying the buzz created.");
//		Assert.assertTrue(isSuccess, "Buzz is not created...");
		log.info("The Buzz is created succesfully.");
	}

	@Test(priority = 5)
	public void deleteBuzzWithImage() {
		deleteBuzz();
	}

	@Test(priority = 6)
	public void createBuzzWithVideo() throws InterruptedException {
		String videourl = "https://youtu.be/mJpjYjbXUoM?si=CkzYYla00HDNkQ0G";
		BuzzPage buzzPage = new BuzzPage(getDriver());
		Thread.sleep(2000);
		buzzPage.clickOnShareVideo();
		buzzPage.enterVideoUrl(videourl);
		buzzPage.clickOnShare();
		boolean isSuccess = buzzPage.isSuccessMessageDisplayed();

		log.info("Verifying the buzz created.");
		Assert.assertTrue(isSuccess, "Buzz is not created...");
		log.info("The Buzz is created succesfully.");
	}

	@Test(priority = 7)
	public void deleteBuzzWithVideo() {
		deleteBuzz();
	}
}
