package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ClaimPage;
import pages.LoginPage;

public class ClaimTest extends BaseTest{
	
	private Logger log = LogManager.getLogger(ClaimTest.class);

	String Id;
	@Test(priority = 1)
	public void createNewClaim() throws InterruptedException {

		LoginPage loginPage = new LoginPage(getDriver());
		log.info("Performing login with valid credentials");
		boolean isLoggedIn = loginPage.loginToApplication("Admin", "admin123");

		log.info("Verifying login result");
		Assert.assertTrue(isLoggedIn, "Login Failed....");
		
		ClaimPage claimPage=new ClaimPage(getDriver());
		claimPage.navigateToClaimPage();
		claimPage.navigateToSubmitClaim();
		claimPage.clickOnEventDropdown();
		claimPage.selectTravelAllowanceEvent();
		claimPage.clickOnCurrencyDropdown();
		claimPage.selectIndainRupeeCurrency();
		claimPage.enterTextInRemarks("Created on 10/31/2025");
		claimPage.clickOnCreateBtn();
		boolean isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim creation failed");
		
		claimPage.clickOnAddExpensesBtn();
		claimPage.clickOnExpenseTypeDropDown();
		claimPage.selectFuelAllowance();
		claimPage.enterDate("2025-10-10");
		claimPage.enterAmount("10000");
		claimPage.enterTextInNote("Expenses Made on 31/10/2025");
		claimPage.clickOnSaveBtn();
		isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim save failed");
		
		Thread.sleep(4000);
		claimPage.clickOnSubmitBtn();
		isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim submit failed");
		
		Id=claimPage.getReferenceIdInCreateClaim();
		claimPage.clickOnBackBtn();
	}
	
	@Test(dependsOnMethods = "createNewClaim",priority = 2)
	public void searchSubmittedClaim()
	{
		ClaimPage claimPage=new ClaimPage(getDriver());
		claimPage.enterReferenceIdIntoSearch(Id);
		claimPage.clickOnSearch();
		boolean isSame = claimPage.verifyDescriptionInMyClaimsRecords("Created on 10/31/2025");
		
		Assert.assertTrue(isSame, "Description mismatch");
		
		isSame=claimPage. verifyStatusInMyClaimsRecords("Submitted");
		
		Assert.assertTrue(isSame, "Status mismatch");
	}
	@Test(priority = 3,dependsOnMethods = "createNewClaim")
	public void cancelClaim()
	{
		ClaimPage claimPage=new ClaimPage(getDriver());
		claimPage.clickOnViewDetails();
		claimPage.clickOnCancelBtn();
		boolean isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim cancel failed");
		
		claimPage.clickOnBackBtn();
		claimPage.enterReferenceIdIntoSearch(Id);
		claimPage.clickOnSearch();
		
		boolean isSame=claimPage.verifyStatusInMyClaimsRecords("Cancelled");
		
		Assert.assertTrue(isSame, "Status mismatch");

	}
	
	@Test(priority = 4)
	public void assignClaim() throws InterruptedException
	{
		ClaimPage claimPage=new ClaimPage(getDriver());
		claimPage.navigateToEmployeeClaims();
		claimPage.clickOnAssignClaimBtn();
		claimPage.enterEmpName("a");
		claimPage.selectEmployee();
		claimPage.clickOnEventDropdown();
		claimPage.selectTravelAllowanceEvent();
		claimPage.clickOnCurrencyDropdown();
		claimPage.selectIndainRupeeCurrency();
		claimPage.enterTextInRemarks("Issued On 10/31/2025");
		claimPage.clickOnCreateBtn();
		boolean isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim creation failed");
		
		claimPage.clickOnAddExpensesBtn();
		claimPage.clickOnExpenseTypeDropDown();
		claimPage.selectFuelAllowance();
		claimPage.enterDate("2025-10-10");
		claimPage.enterAmountInAssignClaim("1000");
		claimPage.enterTextInNote("Expenses issued on 31/10/2025");
		claimPage.clickOnSaveBtn();
		isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim save failed");
		
		Thread.sleep(4000);
		claimPage.clickOnSubmitBtn();
		isSuccess=claimPage.isSuccessMessageDisplayed();
		
		Assert.assertTrue(isSuccess, "Claim submit failed");
		
		Id=claimPage.getReferenceIdAssignClaim();
		claimPage.clickOnBackBtn();
	}
	
	@Test(dependsOnMethods = "assignClaim",priority = 5)
	public void searchAssignClaim()
	{
		ClaimPage claimPage=new ClaimPage(getDriver());
		claimPage.enterReferenceIdIntoSearchInEmpClaims(Id);
		claimPage.clickOnSearch();
		boolean isSame = claimPage.verifyDescriptionInEmpClaimsRecords("Issued On 10/31/2025");
		
		Assert.assertTrue(isSame, "Description mismatch");
		
		isSame=claimPage.verifyStatusInEmpClaimsRecords("Paid");
		
		Assert.assertTrue(isSame,"Status mismatch");
	}

}
