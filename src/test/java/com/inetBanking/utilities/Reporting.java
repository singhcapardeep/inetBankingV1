package com.inetBanking.utilities;
// Listener class used to generate Extent reports
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest eLogger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName ="Test-Report-"+timeStamp+" .html";
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName); // specify location
		//try {
			//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		spark.config().setDocumentTitle("InetBanking Test Project"); //Title of report
		spark.config().setReportName("Functional Automation Test Report"); // name of the report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);// location the chart
		spark.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "pardeep");
		extent.attachReporter(spark);
		

		
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		eLogger=extent.createTest(tr.getName());
		eLogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));// send the passed information
		
	}
	
	public void onTestFailure(ITestResult tr)
	{
		eLogger=extent.createTest(tr.getName()); //create new entry in the report
		eLogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));// send the failed  information
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		if(f.exists())
		{
			try {
				eLogger.fail("Screeshot is below:" + eLogger.addScreenCaptureFromPath(screenshotPath));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		eLogger = extent.createTest(tr.getName());// create new entry in the report
		eLogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	

}
