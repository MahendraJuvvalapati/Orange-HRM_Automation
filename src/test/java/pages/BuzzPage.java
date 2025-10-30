package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class BuzzPage extends BasePage {

	private Logger log = LogManager.getLogger(BuzzPage.class);

	public BuzzPage(WebDriver driver) {
		super(driver);
	}

	private By buzzPage = By.xpath("//span[normalize-space()='Buzz']");
	private By buzzInput = By.xpath("//textarea[@placeholder=\"What's on your mind?\"]");
	private By sharePhotosBtn = By.xpath("//button[normalize-space()='Share Photos']");
	private By shareVideoBtn = By.xpath("//button[normalize-space()='Share Video']");
	private By postBtn = By.xpath("//button[normalize-space()='Post']");
	private By postBody = By.xpath("(//div[@class='orangehrm-buzz-post-body']//p)[1]");
	private By postThreeDots = By.xpath("(//div[@class='orangehrm-buzz-post-header-config'])[1]");
	private By editBtn = By.xpath("//p[normalize-space()='Edit Post']");
	private By deletePostBtn = By.xpath("//p[normalize-space()='Delete Post']");
	private By successMessage = By.xpath("//p[.='Success']");
	private By deleteConfirm = By.xpath("//button[normalize-space()='Yes, Delete']");
	private By editInput = By.xpath("(//textarea[@class='oxd-buzz-post-input'])[2]");
	private By postBtnEdit = By.xpath("(//button[normalize-space()='Post'])[2]");
	private By addPhotos = By.xpath("//input[@type='file']");
	private By shareBtn = By.xpath("//button[normalize-space()='Share']");
	private By videoUrlInput = By.xpath("//textarea[@placeholder='Paste Video URL']");

	public void navigateToBuzzPage() {
		log.info("Navigating to Buzz Page");
		click(buzzPage);
		log.info("Inside Buzz Page");
	}

	public void enterBuzzText(String Text) {
		log.info("Entering Text");
		type(buzzInput, Text);
		log.info("Text entered");
	}

	public void enterTextInEdit(String Text) {
		log.info("Entering Text");
		type(editInput, Text);
		log.info("Text entered");
	}

	public void clickOnPostinEdit() {
		log.info("Clicking on post button");
		click(postBtnEdit);
		log.info("post button clicked");
	}

	public void clickOnPost() {
		log.info("Clicking on post button");
		click(postBtn);
		log.info("post button clicked");
	}

	public String getPostBody() {
		return getText(postBody);
	}

	public boolean verifyCreatedPost(String ActualText) {
		return getPostBody().equals(ActualText);
	}

	public boolean isSuccessMessageDisplayed() {
		return isDisplayed(successMessage);
	}

	public void clickOnDelete() {
		log.info("Clicking on delete button");
		click(deletePostBtn);
		log.info("delete Button CLicked");
	}

	public void clickOnConfirmDelete() {
		log.info("Clicking on confirm delete button");
		click(deleteConfirm);
		log.info("Confirm delete Button CLicked");
	}

	public void clickOnEdit() {
		log.info("Clicking on edit button");
		click(editBtn);
		log.info("edit Button CLicked");
	}

	public void clickOnPostOptions() {
		log.info("Clicking on post  options");
		click(postThreeDots);
		log.info("post Options Button CLicked");
	}

	public void clickOnSharePhotos() {
		log.info("Clicking on Share photos");
		click(sharePhotosBtn);
		log.info("share photos Button CLicked");
	}

	public void clickOnAddPhotos(String source) {
		log.info("Uploading photo from: " + source);
		uploadFile(addPhotos, source);
		log.info("Photo upload triggered successfully.");
	}

	public void clickOnShare() {
		log.info("Clicking on Share button");
		click(shareBtn);
		log.info("share  Button CLicked");
	}

	public void clickOnShareVideo() {
		log.info("Clicking on Share video");
		click(shareVideoBtn);
		log.info("share video Button CLicked");
	}
	
	public void enterVideoUrl(String url) {
		log.info("Entering video Url");
		type(videoUrlInput,url);
		log.info(" video url entered");
	}
	
}
