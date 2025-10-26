package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class PimPage extends BasePage{
	
	private Logger log = LogManager.getLogger(PimPage.class); // Logger instance

    public PimPage(WebDriver driver) {
        super(driver);
    }
    
    private By pimPage=By.xpath("//span[normalize-space()='PIM']");
    private By addBtn=By.xpath("//button[normalize-space()='Add']");
    private By firstName=By.xpath("//input[@placeholder='First Name']");
    private By lastName=By.xpath("//input[@placeholder='Last Name']");
    private By createLoginDetailsToggle=By.xpath("//span[contains(@class, 'oxd-switch-input')]");
    private By userName=By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private By password=By.xpath("(//input[@type='password'])[1]");
    private By confirmPassword=By.xpath("(//input[@type='password'])[2]");
    private By saveBtn=By.xpath("//button[normalize-space()='Save']");
    private By successMessage=By.xpath("//p[.='Success']");
    private By searchEmp=By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private By searchBtn=By.xpath("//button[normalize-space()='Search']");
    private By userCheckBox=By.xpath("(//div[@class='oxd-table-card']//div//div)[1]");
    private By deleteUserBtn=By.xpath("//i[@class='oxd-icon bi-trash']");
    private By deleteConfirm=By.xpath("//button[normalize-space()='Yes, Delete']");
    private By editUserBtn=By.xpath("//i[@class='oxd-icon bi-pencil-fill']");
    private By getLastName=By.xpath("//div[contains(text(),'Juvvalapati')]");
    
    
    
    
    public void enterUserName(String name) {
        log.info("Entering Username: " + name);
        type(userName, name);
        log.info("Username entered: " + name);
    }
    public void enterFirstName(String name) {
    	log.info("Entering firstname: " + name);
    	type(firstName, name);
    	log.info("firstname entered: " + name);
    }
    public void enterLastName(String name) {
    	log.info("Entering lastname: " + name);
    	type(lastName, name);
    	log.info("lastname entered: " + name);
    }

    public void enterPassword(String pass) {
        log.info("Entering Password");
        type(password, pass);
        log.info("Password entered");
    }

    public void enterConfirmPassword(String confirmPass) {
        log.info("Entering Confirm Password");
        type(confirmPassword, confirmPass);
        log.info("Confirm Password entered");
    }

    public void clickOnAddBtn() {
        log.info("Clicking on Add button");
        click(addBtn);
        log.info("Add button clicked");
    }
    public void clickOnSave()
    {
    	log.info("Clicking on Save button");
        click(saveBtn);
        log.info("Save button clicked");
    }
    
    public void navigateToPIM()
    {
    	log.info("Navigating to PIM Page");
    	click(pimPage);
    	log.info("Inside PIM Page");
    }
    
    public void enableCreateLoginDetails()
    {
    	log.info("enabling login details toggle");
    	click(createLoginDetailsToggle);
    	log.info("login details toggle enabled");
    }
    
    public boolean isPIMsaved()
    {
    	return isDisplayed(successMessage);
    }
    public void enterEmpNameIntoSearch(String name)
    {
        log.info("Entering Username: " + name);
    	type(searchEmp,name);
    	log.info(" Username Entered: " + name);
    }
    
    public void clickOnSearch()
    {
    	log.info("Clicking on search button");
    	click(searchBtn);
    	log.info("Search Button CLicked");
    }
    
    public void selectUserCheckBox()
    {
    	log.info("selecting user checkbox");
    	click(userCheckBox);
    	log.info("user checkbox selected");
    }
    
    public void clickOnDelete()
    {
    	log.info("Clicking on delete button");
    	click(deleteUserBtn);
    	log.info("delete Button CLicked");
    }
    public void clickOnConfirmDelete()
    {
    	log.info("Clicking on confirm delete button");
    	click(deleteConfirm);
    	log.info("Confirm delete Button CLicked");
    }
    
    public boolean isSuccessMessageDisplayed()
    {
    	return isDisplayed(successMessage);
    }
    public void clickOnEdit()
    {
    	log.info("Clicking on edit button");
    	click(editUserBtn);
    	log.info("edit Button CLicked");
    }
    public boolean isLastNameChanged()
    {
    	return isDisplayed(getLastName);
    }

}
