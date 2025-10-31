package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class ClaimPage extends BasePage {

	private Logger log = LogManager.getLogger(ClaimPage.class);

	public ClaimPage(WebDriver driver) {
		super(driver);
	}

	private By claimPage = By.xpath("//span[normalize-space()='Claim']");
	private By submitClaimPage = By.xpath("//a[normalize-space()='Submit Claim']");
	private By employeeClaimsPage = By.xpath("//a[normalize-space()='Employee Claims']");
	private By eventDropdown = By.xpath("(//div[contains(text(),'-- Select --')])[1]");
	private By expenseTypeDropDown = By.xpath("//div[@class='oxd-select-text-input']");
	private By currencyDropDown = By.xpath("(//div[contains(text(),'-- Select --')])[1]");
	private By remarksInput = By.xpath("//textarea[last()]");
	private By createBtn = By.xpath("//button[normalize-space()='Create']");
	private By successMessage = By.xpath("//p[.='Success']");
	private By submitBtn = By.xpath("//button[normalize-space()='Submit']");
	private By backBtn = By.xpath("//button[normalize-space()='Back']");
	private By descriptionInMyClaims = By.xpath("(//div[@role='cell'])[3]");
	private By descriptionInEmpClaims = By.xpath("(//div[@role='cell'])[4]");
	private By statusInMyClaims = By.xpath("(//div[@role='cell'])[6]");
	private By statusInEmpClaims = By.xpath("(//div[@role='cell'])[7]");
	private By viewDetails = By.xpath("(//button[.=' View Details '])[1]");
	private By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");
	private By saveBtn = By.xpath("//button[normalize-space()='Save']");
	private By expensesAddBtn = By.xpath("(//button[normalize-space()='Add'])[1]");
	private By fuelAllowanceOption = By.xpath("//span[normalize-space()='Fuel Allowance']");
	private By indianRupeeOption = By.xpath("//span[normalize-space()='Indian Rupee']");
	private By travelAllowanceOption = By.xpath("//span[normalize-space()='Travel Allowance']");
	private By dateInput = By.xpath("//input[contains(@placeholder, 'yyyy')]");
	private By amountInput = By.xpath("(//input)[8]");
	private By amountInputInAssignClaim = By.xpath("(//input)[9]");
	private By noteInput = By.xpath("(//textarea)[2]");
	private By assignClaimBtn = By.xpath("//button[normalize-space()='Assign Claim']");
	private By empNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private By selectEmpname=By.xpath("((//div[@role='listbox'])[1]//span)[1]");    
    private By referenceIdCreateClaim=By.xpath("(//div[@class='']//input)[1]");    
    private By referenceIdSearch=By.xpath("//input[@placeholder='Type for hints...']");    
    private By referenceIdSearchInEmpCLaim=By.xpath("(//input[@placeholder='Type for hints...'])[2]");    
    private By referenceIdInAssignCLaim=By.xpath("(//div[@class='']//input)[2]");    
    private By searchBtn=By.xpath("//button[normalize-space()='Search']");    

	
    public void navigateToClaimPage() {
		log.info("Navigating to Claim Page");
		click(claimPage);
		log.info("Inside claim Page");
	}
    
    public void navigateToSubmitClaim() {
		log.info("Navigating to Submit Claim Page");
		click(submitClaimPage);
		log.info("Inside submit claim Page");
	}
    public void navigateToEmployeeClaims() {
    	log.info("Navigating to employee Claim Page");
    	click(employeeClaimsPage);
    	log.info("Inside Employee claim Page");
    }
    
    public void clickOnEventDropdown() {
		log.info("Clicking on Event  dropdown");
		click(eventDropdown);
		log.info("Event dropdown clicked");
	}
    
    public void selectTravelAllowanceEvent() {
		log.info("Selecting Travel Allowance option");
		click(travelAllowanceOption);
		log.info("Travel Allowance option selected");
	}
    
    public void clickOnCurrencyDropdown() {
		log.info("Clicking on currency  dropdown");
		click(currencyDropDown);
		log.info("currency dropdown clicked");
	}
    
    public void selectFuelAllowance() {
		log.info("Selecting fuel allowance option");
		click(fuelAllowanceOption);
		log.info("fuel allowance option selected");
	}
    public void clickOnExpenseTypeDropDown() {
    	log.info("Clicking on expense Type  dropdown");
    	click(expenseTypeDropDown);
    	log.info("expense type dropdown clicked");
    }
    
    public void selectIndainRupeeCurrency() {
    	log.info("Selecting Indian Rupee option");
    	click(indianRupeeOption);
    	log.info("Indian Rupee option selected");
    }
    
    public void enterTextInRemarks(String Text) {
		log.info("Entering Text");
		type(remarksInput, Text);
		log.info("Text entered");
	}
    
    public void clickOnCreateBtn() {
		log.info("Clicking on create button");
		click(createBtn);
		log.info("create button clicked");
	}

    public boolean isSuccessMessageDisplayed() {
		return isDisplayed(successMessage);
	}
    
    public void clickOnAddExpensesBtn() {
		log.info("Clicking on Add button");
		click(expensesAddBtn);
		log.info("Add button clicked");
	}
    
    public void enterDate(String Text) {
		log.info("Entering Text");
		type(dateInput, Text);
		log.info("Text entered");
	}
    
    public void enterAmount(String Text) {
		log.info("Entering Text");
		type(amountInput, Text);
		log.info("Text entered");
	}

    public void enterTextInNote(String Text) {
		log.info("Entering Text");
		type(noteInput, Text);
		log.info("Text entered");
	}
    public void enterAmountInAssignClaim(String Text) {
    	log.info("Entering Text");
    	type(amountInputInAssignClaim, Text);
    	log.info("Text entered");
    }
    
    public void clickOnSaveBtn() {
		log.info("Clicking on save button");
		click(saveBtn);
		log.info("save button clicked");
	}
    
    public void clickOnSubmitBtn() {
		log.info("Clicking on submit button");
		click(submitBtn);
		log.info("submit button clicked");
	}
    
    public void clickOnBackBtn() {
		log.info("Clicking on back button");
		click(backBtn);
		log.info("Back button clicked");
	}
    
    public boolean verifyDescriptionInMyClaimsRecords(String Text) {
    	String actualText=getText(descriptionInMyClaims);
		return actualText.equals(Text);
	}
    
    public boolean verifyStatusInMyClaimsRecords(String Text) {
    	String actualText=getText(statusInMyClaims);
		return actualText.equals(Text);
	}
    
    public boolean verifyDescriptionInEmpClaimsRecords(String Text) {
    	String actualText=getText(descriptionInEmpClaims);
		return actualText.equals(Text);
	}
    
    public boolean verifyStatusInEmpClaimsRecords(String Text) {
    	String actualText=getText(statusInEmpClaims);
		return actualText.equals(Text);
	}
    
    public void clickOnViewDetails() {
		log.info("Clicking on view details button");
		click(viewDetails);
		log.info("View Details button clicked");
	}
    
    public void clickOnCancelBtn() {
		log.info("Clicking on cancel button");
		click(cancelBtn);
		log.info("cancel button clicked");
	}
    
    public void clickOnAssignClaimBtn() {
		log.info("Clicking on Assign Clsim button");
		click(assignClaimBtn);
		log.info("Assign Claim button clicked");
	}
    public String getReferenceIdInCreateClaim() {
    	return getTextWithJs(referenceIdCreateClaim);
    }
    public String getReferenceIdAssignClaim() {
    	return getTextWithJs(referenceIdInAssignCLaim);
    }
    
    public void enterEmpName(String Text) {
		log.info("Entering Text");
		type(empNameInput, Text);
		log.info("Text entered");
	}
    
    public void enterReferenceIdIntoSearch(String Text) {
		log.info("Entering Text");
		type(referenceIdSearch, Text);
		log.info("Text entered");
	}
    public void enterReferenceIdIntoSearchInEmpClaims(String Text) {
    	log.info("Entering Text");
    	type(referenceIdSearchInEmpCLaim, Text);
    	log.info("Text entered");
    }
    
    public void clickOnSearch() {
    	log.info("Clicking on Search button");
    	click(searchBtn);
    	log.info("Search button clicked");
    }
    public void selectEmployee() {
    	log.info("Selecting Employee");
    	click(selectEmpname);
    	log.info("Employee selected");
    }
    
}
