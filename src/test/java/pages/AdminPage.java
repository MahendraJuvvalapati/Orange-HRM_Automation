package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class AdminPage extends BasePage {

	private Logger log = LogManager.getLogger(LoginPage.class); // Logger instance

    public AdminPage(WebDriver driver) {
        super(driver);
    }
    
    private By adminPage=By.xpath("//span[normalize-space()='Admin']");
    private By addBtn=By.xpath("//button[normalize-space()='Add']");
    private By userRoleDropdpwn=By.xpath("(//div[contains(text(),'-- Select --')])[1]");
    private By selectAdmin=By.xpath("//span[contains(text(),'Admin')]");
    private By statusDropdown=By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private By selectEnabled=By.xpath("//span[normalize-space()='Enabled']");
    private By empName=By.xpath("//input[@placeholder='Type for hints...']");
    private By selectEmpname=By.xpath("((//div[@role='listbox'])[1]//span)[1]");    
    private By userName=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By password=By.xpath("(//input[@type='password'])[1]");
    private By confirmPassword=By.xpath("(//input[@type='password'])[2]");
    private By saveBtn=By.xpath("//button[normalize-space()='Save']");
    private By createdUser=By.xpath("//div[contains(text(),'Mahendra')]");
    private By searchUser=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By searchBtn=By.xpath("//button[normalize-space()='Search']");
    private By userCheckBox=By.xpath("(//div[@class='oxd-table-card']//div//div)[1]");
    private By deleteUserBtn=By.xpath("//i[@class='oxd-icon bi-trash']");
    private By deleteConfirm=By.xpath("//button[normalize-space()='Yes, Delete']");
    private By successMessage=By.xpath("//p[.='Success']");
    
    
    
    public void selectAdminUserRole() {
        log.info("Selecting Admin role");
        click(userRoleDropdpwn);
        click(selectAdmin);
        log.info("User Role selected: Admin");
    }

    public void selectEmpName(String name) {
        log.info("Typing Employee Name: " + name);
        type(empName, name);
        click(selectEmpname);
        log.info("Employee Name selected");
    }

    public void selectStatus() {
        log.info("Selecting Status Enabled");
        click(statusDropdown);
        click(selectEnabled);
        log.info("Status selected Enabled");
    }

    public void enterUserName(String name) {
        log.info("Entering Username: " + name);
        type(userName, name);
        log.info("Username entered: " + name);
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
    public void navigateToAdmin()
    {
    	log.info("Navigating to Admin Page");
    	click(adminPage);
    	log.info("Inside Admin Page");
    }
    
    public boolean isAdminUserCreated()
    {
    	return isSuccessMessageDisplayed()||isDisplayed(createdUser);
    }
    
    public void enterUserNameIntoSearch(String name)
    {
        log.info("Entering Username: " + name);
    	type(searchUser,name);
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
    
}
