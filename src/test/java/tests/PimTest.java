package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.PimPage;

public class PimTest extends BaseTest{
	
	@Test
	public void createNewPIM()
	{
		LoginPage loginPage=new LoginPage(getDriver());
		loginPage.loginToApplication("Admin", "admin123");
		
		PimPage pimPage=new PimPage(getDriver());
		pimPage.navigateToPIM();
		pimPage.clickOnAddBtn();
		pimPage.enterFirstName("Mahendra");
		pimPage.enterLastName("J");
		pimPage.enterEmpId("1112");
		pimPage.enableCreateLoginDetails();
		pimPage.enterUserName("Mahii");
		pimPage.enterPassword("Mahi@1234");
		pimPage.enterConfirmPassword("Mahi@1234");
		pimPage.clickOnSave();
		
		boolean isPimCreated = pimPage.isPIMsaved();
		log.info("Verifying PIM creation result");
		Assert.assertTrue(isPimCreated,"PIM is not created succesfully..");
		log.info("PIM created Succesfully");
		
	}
	
	@Test(dependsOnMethods = "createNewPIM")
	public void editPIM()
	{
		PimPage pimPage=new PimPage(getDriver());
		pimPage.navigateToPIM();
		pimPage.enterEmpNameIntoSearch("Mahendra");
		pimPage.clickOnSearch();
		pimPage.clickOnEdit();
		pimPage.enterLastName("Juvvalapati");
		pimPage.clickOnSave();
		
		boolean isSucess = pimPage.isSuccessMessageDisplayed();
		log.info("Verifying user edit result");
		Assert.assertTrue(isSucess,"Last Name not changed");
		log.info("User edited succesfully.");
		
		pimPage.navigateToPIM();
		pimPage.enterEmpNameIntoSearch("Mahendra");
		pimPage.clickOnSearch();
		
		boolean isChanged = pimPage.isLastNameChanged();
		log.info("Verifying user change result");
		Assert.assertTrue(isChanged,"Last Name not changed");
		log.info("User Changed successfully");
	}
	
	@Test(dependsOnMethods = "editPIM")
	public void searchPImAndDelete()
	{
		PimPage pimPage=new PimPage(getDriver());
		pimPage.navigateToPIM();
		pimPage.enterEmpIdIntoSearch("1112");
		pimPage.clickOnSearch();
		pimPage.selectUserCheckBox();
		pimPage.clickOnDelete();
		pimPage.clickOnConfirmDelete();
		boolean isDeleted = pimPage.isSuccessMessageDisplayed();
		
		log.info("Verifying PIM deletion result");
		Assert.assertTrue(isDeleted,"PIM is not deleted succesfully..");
		log.info("PIM deleted Succesfully");
		
	}

}
