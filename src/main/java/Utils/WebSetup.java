package Utils;

import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import Listeners.TestListener;

import java.nio.file.Paths;


@Listeners({TestListener.class})
public class WebSetup {

    public WebDriver driver = null;

    private Log log=new Log();
    @BeforeSuite
    @Parameters({"browser", "url","headless"})
    public void launchBrowser(@Optional("chrome") String browser,@Optional("https://admin.devakhada.com/") String url,@Optional("false") boolean headless){
        log.info(Status.INFO,"Launching the "+browser+" browser");
        if(browser.equals("chrome")) {
//            WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--remote-allow-origins=*");
            if(headless) options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        }else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("start-maximized");
            if(headless) options.addArguments("--headless=new");
            driver = new FirefoxDriver(options);
        }
        log.info(Status.INFO,browser+" Browser Launched");
        setURL(url);
    }

    public void setURL(String URL){
        log.info(Status.INFO,"Opening URL:- "+URL);
        driver.get(URL);
    }

    @AfterSuite
    public void quitBrowserSession(){
        log.info(Status.INFO,"Closing the browser session");
        driver.quit();
    }

    public void closeWindow(){
        log.info(Status.INFO,"Closing the browser window");
        driver.close();
    }
}