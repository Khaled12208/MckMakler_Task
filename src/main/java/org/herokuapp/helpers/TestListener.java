package org.herokuapp.helpers;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.herokuapp.helpers.Helpers;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;


public class TestListener implements ITestListener {

	Helpers helper = new Helpers();
	ExtentReport report = new ExtentReport();
	ExtentReports reporter = report.startReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = reporter.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

		helper.log().info("Test " + result.getMethod().getMethodName() + " started");
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "TestPass");
		helper.log().info("Test " + result.getMethod().getMethodName() + " Success");
	}

	public void onTestSkipped(ITestResult result) {
		helper.log().info("Test " + result.getMethod().getMethodName() + " Skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onFinish(ITestContext context) {
		helper.log().info("Test " + context.getName() + " Finished ");
		reporter.flush();


	}


	public void onTestFailure(ITestResult result) {
		helper.log().info("Test " + result.getName() + " Failed ");
		if(result.getThrowable() != null) {
			  StringWriter sw = new StringWriter();
			  PrintWriter pw = new PrintWriter(sw);
			  result.getThrowable().printStackTrace(pw);
			helper.log().error(sw.toString());
			extentTest.get().fail(result.getThrowable());
		}


	}



}
