package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNg {

	public static ExtentReports getReportsObject() {
		String extentReportpath = System.getProperty("user.dir") + "//reports//index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportpath);
		
		reporter.config().setDocumentTitle("Web Automation Results");
		reporter.config().setReportName("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Upendra");
		return extent;
		
	}
}
