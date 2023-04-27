package Listeners;
import Utils.Log;
import Utils.Reporters;
import Utils.WebSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
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
        Object testClass = result.getInstance();
        WebDriver driver=((WebSetup) testClass).getDriver();
        String scpth = result.getName()+ LocalTime.now();
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileLocation = System.getProperty("user.dir") + "/TestReport/";
        try {
            FileUtils.copyFile(file,  new File(fileLocation+scpth+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String path=fileLocation+scpth+".png";
//        TestListener.test.get().log(Status.FAIL,"Screenshot " ).addScreenCaptureFromPath(path,scpth);
        TestListener.test.get().log(Status.FAIL,"Screenshot ", MediaEntityBuilder.createScreenCaptureFromPath(path).build() );
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