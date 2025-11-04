package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class MyInfoPage extends BasePage{

	private Logger log = LogManager.getLogger(MyInfoPage.class);

	public MyInfoPage(WebDriver driver) {
		super(driver);
	}
	
	private By myInfoPage = By.xpath("//span[normalize-space()='My Info']");
	private By firstName=By.xpath("//input[@placeholder='First Name']");
    private By lastName=By.xpath("//input[@placeholder='Last Name']");
    private By empId=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By saveBtn=By.xpath("(//button[normalize-space()='Save'])[1]");
    private By successMessage=By.xpath("//p[.='Success']");
    private By profileName=By.xpath("//p[@class='oxd-userdropdown-name']");
    private By profileImage=By.xpath("//div[@class='orangehrm-edit-employee-image']");
    private By addImage=By.xpath("//input[@type='file']");
    
    
    public void enterEmpId(String id) {
    	log.info("Entering empId: " + id);
    	type(empId, id);
    	log.info("empId entered: " + id);
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
    
    public void clickOnAddImageBtn(String img) {
        log.info("Clicking on Add button");
        uploadFile(addImage,img);
        log.info("Add button clicked");
    }
    public void clickOnSave()
    {
    	log.info("Clicking on Save button");
        click(saveBtn);
        log.info("Save button clicked");
    }
    
    public void navigateToMyInfo()
    {
    	log.info("Navigating to MyInfo Page");
    	click(myInfoPage);
    	log.info("Inside MyInfo Page");
    }
    
    public boolean isSuccessMessageDisplayed()
    {
    	return isDisplayed(successMessage);
    }
    
    public void clickOnProfileImage()
    {
    	log.info("clicking on profile image");
    	click(profileImage);
    	log.info("profile image clicked");
    }
    
    public boolean validateProfileNameChange(String Name)
    {
    	return getText(profileName).equals(Name);
    }
}
