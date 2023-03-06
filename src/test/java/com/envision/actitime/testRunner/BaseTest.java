package com.envision.actitime.testRunner;

import com.envision.actitime.logs.Log;
import com.envision.actitime.pageObjects.BasePage;
import com.envision.actitime.pageObjects.TimeTrackPage;
import com.envision.actitime.utility.BrowserFactory;
import com.envision.actitime.utility.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    BasePage basePage;
    TimeTrackPage timeTrackPage;
    WebDriver driver;
    String brname, runmode;

//    @Parameters({"browser_name_to_execute", "execution_mode"})
//    @BeforeTest(groups = {"low", "medium", "high"})
//    public void readBrowserName(@Optional("chrome") String bn, @Optional("active") String rm) { //if you don't give anything in parameters, you can provide Optional here
//        brname = bn;
//        runmode = rm;
//    }
    @Parameters({"browser_name_to_execute", "execution_mode"})
    @BeforeClass(groups = {"low", "medium", "high"})
    public void getBrowserName(@Optional("chrome") String bn, @Optional("active") String rm) { //if you don't give anything in parameters, you can provide Optional here
        brname = bn;
        runmode = rm;
    }

    @BeforeMethod(groups = {"low", "medium", "high"}) //or alwaysRun = true
    public void login() {
        System.out.println("Browser name is = "+brname+" and RunMode is = "+runmode);
        BrowserFactory.openBrowser(brname,runmode);
        BrowserFactory.LaunchWebsitefromPropertyFile();
        basePage = new BasePage(BrowserFactory.openBrowser());
    }

    @AfterMethod(groups = {"low", "medium", "high"})
    public void a_takeScreenshots(ITestResult result) {
//        if (!result.isSuccess()) {
//            CommonUtils.takePageVisibleScreenshot();
//        }
        BrowserFactory.closeAllWindows();
    }

    @BeforeClass(groups = {"low", "medium", "high"})
    public void classLevelSetup() {
        System.out.println(super.getClass().getName() + " has started execution");
        Log.info("Tests is starting!");
    }



    @AfterClass(groups = {"low", "medium", "high"})
    public void teardown() {
        System.out.println(super.getClass().getName() + " has completed execution");
        Log.info("Tests are ending!");
    }

    @BeforeGroups(groups = "high")
    public void caution() {
        System.out.println("don't access this machine for 15 minutes");
    }

    @AfterGroups(groups = "high")
    public void relaxedCaution() {
        System.out.println("now you can access this machine");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
