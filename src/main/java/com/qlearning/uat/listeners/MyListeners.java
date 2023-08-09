package com.qlearning.uat.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qlearning.uat.utils.ExtentReporter;
import com.qlearning.uat.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
	    extentReport = ExtentReporter.generateExtentReport();
		//System.out.println("Execution of project tests started");
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" started executing");
		//System.out.println(testName+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//String testName = result.getName();
		extentTest.log(Status.PASS, testName+" passed successfully");
		//System.out.println(testName+" passed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//String testName = result.getName();
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshoot(driver, testName);
		
//		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destinationScreenshotPath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
//		
//		try {
//			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got failed");
		//System.out.println(result.getThrowable());
		//System.out.println(testName+" got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
		//System.out.println(result.getThrowable());
		//System.out.println(testName+" got skipped");
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		//System.out.println("Finished executing project tests");
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
