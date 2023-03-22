package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporters {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;

    private static String platform;
    private static String reportFileName = "Report.html";
    private static String fileLocation = System.getProperty("user.dir") + "/TestReport";
    private static String reportFileLoc = fileLocation + "/" + reportFileName;

    public static ExtentReports getReportInstance(){
        if(extent!=null)
            return extent;
        createInstance();
        return extent;
    }

    public static ExtentReports createInstance(){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportFileLoc);
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", platform);
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("File", reportFileName);
        return extent;
    }

}
