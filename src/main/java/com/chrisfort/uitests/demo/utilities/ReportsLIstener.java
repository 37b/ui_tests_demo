package com.chrisfort.uitests.demo.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Created by cpfort on 8/28/17.
 */
public class ReportsLIstener implements IReporter {

    private ExtentReports extent;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String
        outputDirectory) {

        extent = new ExtentReports()

    }

}
