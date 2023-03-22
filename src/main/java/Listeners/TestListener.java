package Listeners;
import Utils.Log;
import Utils.Reporters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

public class TestListener implements ITestListener {

    public static ExtentReports extent = Reporters.getReportInstance();
    public static ThreadLocal<ExtentTest> test =new ThreadLocal<ExtentTest>();

//    private static Logger logger= LogManager.getRootLogger();
    private Log log=new Log();

    @Override
    public void onFinish(ITestContext contextFinish) {
        log.info(Status.INFO,"Test Suite is Ending");
        extent.flush();
    }

    @Override
    public void onStart(ITestContext contextStart) {
        log.info(Status.INFO,"Test Suite is starting");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(Status.INFO,"Method failed with certain success percentage" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(Status.FAIL,"Method failed " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn(Status.WARNING,"Method skipped " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getName()+ " started"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(Status.PASS,"Method passed" + result.getName());
    }
}