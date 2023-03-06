package com.envision.actitime.listeners;

import com.aventstack.extentreports.Status;
import com.envision.actitime.extentReports.ExtentTestManager;
import com.envision.actitime.testRunner.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import static com.envision.actitime.extentReports.ExtentTestManager.getTest;

public class Retry implements IRetryAnalyzer { //this class for FailRun class

    private int count = 0;
    private static int maxTry = 2; //Run the failed test 3 (1 + maxTry) times

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                     //Check if test not succeed
            if (count < maxTry) {                           //Check if maxTry count is reached
                count++;                                    //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE); //Mark test as failed and take base64Screenshot
                extendReportsFailOperations(iTestResult);   //ExtentReports fail operations
                return true;                                //Tells TestNG to re-run the test
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);     //If test passes, TestNG marks it as passed
        }
        return false;
    }

    public void extendReportsFailOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

}
