package com.envision.actitime.testRunner;

import com.envision.actitime.filereader.PropertyFileReader;
import com.envision.actitime.pageObjects.TimeTrackPage;
import com.envision.actitime.utility.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunnerWithPropertyFileInput extends BaseTest{
    @Test(groups="low")
    public void verifyValidLoginTest1(){
        String uname = PropertyFileReader.getPropertyValue("actitime.loginpage.username");
        basePage.enterUserName(uname);
        String pass = PropertyFileReader.getPropertyValue("actitime.loginpage.password");
        basePage.enterPassword(pass);
        basePage.clickLoginButton();
        Assert.assertEquals(basePage.getPageTitle(),PropertyFileReader.getPropertyValue("actitime.timetrackpage.title"));
        timeTrackPage=new TimeTrackPage(BrowserFactory.openBrowser()); // Very Important
        timeTrackPage.clickLogout();
    }

    @Test(groups = "high")
    public void verifyInvalidLoginTest1(){
        String uname = PropertyFileReader.getPropertyValue("actitime.loginpage.invalidusername");
        basePage.enterUserName(uname);
        String pass = PropertyFileReader.getPropertyValue("actitime.loginpage.invalidpassword");
        basePage.enterPassword(pass);
        basePage.clickLoginButton();
        Assert.assertEquals(basePage.getInvalidLoginErrorMessage(),PropertyFileReader.getPropertyValue("actitime.loginpage.errormessage"));
    }
}
