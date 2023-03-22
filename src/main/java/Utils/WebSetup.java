package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import Listeners.TestListener;


@Listeners({TestListener.class})
public class WebSetup {

    public WebDriver driver = null;

    private static Logger logger = LogManager.getRootLogger();

    @BeforeSuite
    @Parameters({"browser", "url","headless"})
    public void launchBrowser(@Optional("chrome") String browser,@Optional("https://admin.devakhada.com/") String url,@Optional("false") boolean headless){
        logger.info("Launching the "+browser+" browser");
        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
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
        logger.info(browser+" Browser Launched");
        setURL(url);
    }

    public void setURL(String URL){
        logger.info("Opening URL:- "+URL);
        driver.get(URL);
    }

    @AfterSuite
    public void quitBrowserSession(){
        logger.info("Closing the browser session");
        driver.quit();
    }

    public void closeWindow(){
        logger.info("Closing the browser window");
        driver.close();
    }
}