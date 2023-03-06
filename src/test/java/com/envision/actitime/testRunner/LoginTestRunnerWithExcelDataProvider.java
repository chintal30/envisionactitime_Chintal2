package com.envision.actitime.testRunner;

import com.envision.actitime.filereader.ExcelReaderAndDataProvider;
import com.envision.actitime.filereader.PropertyFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunnerWithExcelDataProvider extends BaseTest {
    @Test(groups = "low", dataProvider = "excel_valid_login_tests", dataProviderClass = ExcelReaderAndDataProvider.class)
    public void verifyValidLoginTest3(Object uname, Object pass) {
        System.out.println("Excel valid data login testing started");
        basePage.enterUserName(uname.toString());
        basePage.enterPassword(pass.toString());
        basePage.clickLoginButton();
        Assert.assertEquals(basePage.getPageTitle(), PropertyFileReader.getPropertyValue("actitime.timetrackpage.title"));
    }

    @Test(groups = "medium", dataProvider = "excel_invalid_login_tests", dataProviderClass = ExcelReaderAndDataProvider.class)
    public void verifyInvalidLoginTest3(Object uname, Object pass) {
        System.out.println("Excel invalid data login testing started");
        basePage.enterUserName(uname.toString());
        basePage.enterPassword(pass.toString());
        basePage.clickLoginButton();
        Assert.assertEquals(basePage.getInvalidLoginErrorMessage(), PropertyFileReader.getPropertyValue("actitime.loginpage.errormessage"));
    }

}
