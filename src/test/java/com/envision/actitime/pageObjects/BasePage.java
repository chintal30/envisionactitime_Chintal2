package com.envision.actitime.pageObjects;


import com.envision.actitime.utility.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@name='username']")
    private WebElement usernameTextBox;

    @FindBy(name = "pwd")
    private WebElement passwordTextBox;

    @FindBy(id = "loginButton")
    private WebElement submitButton;

    @FindBy(xpath = "//td[@valign='top']/span[@class='errormsg']")
    private WebElement errorMessageText;

    public void enterUserName(String userNameInput) {
        usernameTextBox.sendKeys(userNameInput);
    }

    public void enterPassword(String passwordInput) {
        passwordTextBox.sendKeys(passwordInput);
    }

    public void clickLoginButton() {
        // WebElement submitButton = super.getElement("actitime.loginpage.submit_button");
        submitButton.click();
    }

    public String getInvalidLoginErrorMessage() {
        return errorMessageText.getText();

    }
    public String getPageTitle(){
        CommonUtils.waitDefininte(3);
        return driver.getTitle();
    }
}
