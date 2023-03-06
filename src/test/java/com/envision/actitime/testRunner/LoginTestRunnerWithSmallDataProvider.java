package com.envision.actitime.testRunner;

import com.envision.actitime.filereader.PropertyFileReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import static com.envision.actitime.extentReports.ExtentTestManager.startTest; //importan
public class LoginTestRunnerWithSmallDataProvider extends BaseTest {

    String[][] smallLoginUsers = {
            {"input1", "valid", "admin", "manager"},
            {"input2", "valid", "trainee", "trainee"},
            {"input3", "invalid", "admin123", "manager"},
            {"input4", "invalid", "trainee", "trainee123"},
    };

    @DataProvider(name = "small_valid_login_data")
    public Object[][] testData1(Method methodName) {
        ArrayList<String[]> outerArrayList = new ArrayList<>();
        for (String[] singleArray : smallLoginUsers) {
            ArrayList<String> innerArrayList = new ArrayList<>();
            if (singleArray[1].equalsIgnoreCase("valid")) {
                for (int index = 2; index < singleArray.length; index++) {
                    innerArrayList.add(singleArray[index]);
                }
                outerArrayList.add(innerArrayList.toArray(new String[0]));
            }
        }
        System.out.println(Arrays.deepToString(outerArrayList.toArray(new String[0][0])));
        return outerArrayList.toArray(new String[0][0]);
    }

    @DataProvider(name = "small_invalid_login_data")
    public Object[][] testData2(Method methodName) {
        ArrayList<String[]> outerArrayList = new ArrayList<>();
        for (String[] singleArray : smallLoginUsers) {
            ArrayList<String> innerArrayList = new ArrayList<>();
            if (singleArray[1].equalsIgnoreCase("invalid")) {
                for (int index = 2; index < singleArray.length; index++) {
                    innerArrayList.add(singleArray[index]);
                }
                outerArrayList.add(innerArrayList.toArray(new String[0]));
            }
        }
        System.out.println(Arrays.deepToString(outerArrayList.toArray(new String[0][0])));
        return outerArrayList.toArray(new String[0][0]);
    }

    @Test(groups = "low", dataProvider = "small_valid_login_data", dataProviderClass = LoginTestRunnerWithSmallDataProvider.class,
            description = "Verifying valid login data from sample data")
    public void verifyValidLoginTest2(String uname, String pass, Method method) {
        startTest(method.getName(), "Verifying valid login data from sample data"); // for all methods
        basePage.enterUserName(uname);
        basePage.enterPassword(pass);
        basePage.clickLoginButton();
        Assert.assertEquals(basePage.getPageTitle(), PropertyFileReader.getPropertyValue("actitime.timetrackpage.title"));
    }

    @Test(groups = "high", dataProvider = "small_invalid_login_data", dataProviderClass = LoginTestRunnerWithSmallDataProvider.class)
    public void verifyInvalidLoginTest2(String uname, String pass) {
        basePage.enterUserName(uname);
        basePage.enterPassword(pass);
        basePage.clickLoginButton();
        Assert.assertEquals(basePage.getInvalidLoginErrorMessage(),PropertyFileReader.getPropertyValue("actitime.loginpage.errormessage"));
    }
}
