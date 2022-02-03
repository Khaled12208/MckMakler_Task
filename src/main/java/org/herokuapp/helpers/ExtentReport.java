package org.herokuapp.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExtentReport {

    private ExtentSparkReporter reporter;
    private ExtentReports extent;

    public ExtentReports startReport()
    {
        reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\test-output\\Report.html");

        try {
            reporter.loadXMLConfig(System.getProperty("user.dir")+"\\src\\main\\resources\\org\\herokuapp\\extent-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        reporter.config().setReportName(addr.getHostName());
        reporter.config().setDocumentTitle("test execution results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        return  extent;
    }
}
