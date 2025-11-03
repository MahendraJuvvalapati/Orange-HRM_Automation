package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class DirectoryPage extends BasePage{

	private Logger log = LogManager.getLogger(DirectoryPage.class);

	public DirectoryPage(WebDriver driver) {
		super(driver);
	}
	
	private By directoryPage = By.xpath("//span[normalize-space()='Directory']");
	private By empNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private By searchBtn=By.xpath("//button[normalize-space()='Search']");    
    private By selectEmpname=By.xpath("((//div[@role='listbox'])[1]//span)[1]");    
    private By recordsFound=By.xpath("//span[@class='oxd-text oxd-text--span']");
    private By empNameInRecord=By.xpath("(//div[@class='orangehrm-container']//p)[1]");
    private By empNameInExpandedRecord=By.xpath("(//div[@class='orangehrm-corporate-directory-sidebar']//p)[1]");
    private By closeRecord=By.xpath("(//i[@class='oxd-icon bi-arrow-right'])[2]");
    
    
    
    public void navigateToDirectoryPage() {
		log.info("Navigating to directory Page");
		click(directoryPage);
		log.info("Inside directory Page");
	}
    
    public void clickOnSearch() {
    	log.info("Clicking on Search button");
    	click(searchBtn);
    	log.info("Search button clicked");
    }

    public void enterEmpName(String Text) {
		log.info("Entering Text");
		type(empNameInput, Text);
		log.info("Text entered");
	}
    
    public void selectEmployee() {
    	log.info("Selecting Employee");
    	click(selectEmpname);
    	log.info("Employee selected");
    }
    
    public boolean verifyRecordsFound()
    {
    	return getText(recordsFound).equals("(1) Record Found");
    }
    
    public String getTextFromSearchEmployee()
    {
    	return getTextWithJs(empNameInput);
    }
    
    public boolean verifyEmpNameInRecords()
    {
    	return isDisplayed(empNameInRecord) && getText(empNameInRecord).equals(getTextFromSearchEmployee());
    }
    
    public void clickOnRecord() {
    	log.info("Clicking on Record Result");
    	click(empNameInRecord);
    	log.info("Record Result clicked");
    }
    
    public boolean verifyEmpNameInExpandedRecords()
    {
    	return isDisplayed(empNameInExpandedRecord) && getText(empNameInExpandedRecord).equals(getTextFromSearchEmployee());
    }
    
    public boolean validateExpandRecord()
    {
    	boolean shouldVisible= isDisplayed(closeRecord);
    	click(closeRecord);
    	boolean shouldNotVisible=isDisplayed(closeRecord);
    	
    	return shouldVisible && !shouldNotVisible;
    }
    
}
