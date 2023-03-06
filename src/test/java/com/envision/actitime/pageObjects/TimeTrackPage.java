package com.envision.actitime.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimeTrackPage extends BasePage{
    private WebDriver driver;
    public TimeTrackPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(id="logoutLink")
    WebElement logoutLink;
    public void clickLogout(){
        logoutLink.click();
    }
}
