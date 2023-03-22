package Listeners;
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
//    public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();

    private static Logger logger= LogManager.getRootLogger();

    @Override
    public void onFinish(ITestContext contextFinish) {
        logger.info("Test Suite is Ending");
        extent.flush();
    }

    @Override
    public void onStart(ITestContext contextStart) {
        logger.info("Test Suite is starting");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("Method failed with certain success percentage" + result.getName());
        test.get().log(Status.INFO,"Method failed with certain success percentage" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Method failed" + result.getName());
        test.get().log(Status.FAIL,"Method failed " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Method skipped " + result.getName());
        test.get().log(Status.WARNING,"Method skipped " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " started!"));
        test.set(extent.createTest(result.getName()+ " started"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Method passed" + result.getName());
        test.get().log(Status.PASS,"Method passed" + result.getName());
    }
}