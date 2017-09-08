package com.chrisfort.uitests.demo.utilities;

import com.chrisfort.uitests.demo.CONFIG.ApplicationConfiguration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(ReportsListener.class);

    private ExtentReports extent;

    @Value("${report.location}")
    private String reportLocation;

    @Value("${report.screenshots.location}")
    private String reportScreenshotLocation;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
        String outputDirectory) {

        extent = new ExtentReports(
            TestExecutionWithDriverListener.getReportLocation() + File.separator + "index.html",
            true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult iSuiteResult : result.values()) {
                ITestContext context = iSuiteResult.getTestContext();

                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }

            extent.flush();
            extent.close();
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

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = String.format("Test %sed", status.toString().toLowerCase());

                if (null != result.getThrowable()) {
                    message = result.getThrowable().getMessage();
                }
                String img = test.addScreenCapture(
                    "./" + TestExecutionWithDriverListener.getReportScreenshotsLocation()
                        + File.separator + test.getTest().getName() + ".png");

                test.log(status, message);
                test.log(status, "Description: " + description);
                test.log(status, "Screenshot", "Image: " + img);

                extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
