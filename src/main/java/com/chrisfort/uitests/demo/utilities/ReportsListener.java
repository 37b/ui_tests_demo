package com.chrisfort.uitests.demo.utilities;

import com.chrisfort.uitests.demo.CONFIG.ApplicationConfiguration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cpfort on 8/28/17.
 */
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class ReportsListener implements IReporter {

    private ExtentReports extent;

    @Value("${report.location}")
    private String reportLocation;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
        String outputDirectory) {

        extent = new ExtentReports(reportLocation + File.separator + "index.html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for(ISuiteResult iSuiteResult : result.values()) {
                ITestContext context = iSuiteResult.getTestContext();

                buildTestNodes(context.getPassedTests(), LogStatus.PASS);

            }

        }
    }


    private void buildTestNodes(IResultMap testResult, LogStatus status) {
        ExtentTest test;

        if (testResult.size() > 0) {
            for (ITestResult result : testResult.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
                String description = result.getMethod().getDescription();
                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
