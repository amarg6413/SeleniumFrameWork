package Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

public class TestListener implements ITestListener {

    private static Logger logger= LogManager.getLogger(TestListener.class);
    @Override
    public void onFinish(ITestContext contextFinish) {
        logger.always().log("Test Suite is Ending");
    }

    @Override
    public void onStart(ITestContext contextStart) {
        logger.always().log("Test Suite is starting");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Method failed with certain success percentage" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Method failed" + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Method skipped" + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Method started" + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Method passed" + result.getName());
    }
}