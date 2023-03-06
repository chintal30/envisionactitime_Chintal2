package com.envision.actitime.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.envision.actitime.utility.CommonUtils;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("actitime_screenshots/extent-report"+ CommonUtils.getCurrentDateTime()+".html");
        //ExtentSparkReporter reporter = new ExtentSparkReporter("actitimeReports/SparkActimePlugin.html");
        reporter.config().setReportName("Actitime_Extent_Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company Name", "Envision");
        extentReports.setSystemInfo("Author", "automation team");
        return extentReports;
    }
}
