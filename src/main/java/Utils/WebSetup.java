package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebSetup {

    public WebDriver driver = null;

    private static Logger logger = LogManager.getLogger(WebSetup.class);
    public void launchBrowser(){
        logger.info("Launching the browser");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
//        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        logger.info("Browser Launched");
        setURL("https://google.com");
    }

    public void setURL(String URL){
        logger.info("Opening URL:- "+URL);
        driver.get(URL);
    }

    public void quitBrowserSession(){
        logger.info("Closing the browser session");
        driver.quit();
    }

    public void closeWindow(){
        logger.info("Closing the browser session");
        driver.close();
    }

    public static void main(String[] args) throws InterruptedException {
        WebSetup browserSetUp = new WebSetup();
        browserSetUp.launchBrowser();
        WebDriver driver1= browserSetUp.driver;
        browserSetUp.setURL("https://www.instagram.com");
        Thread.sleep(6000);
        driver1.findElement(By.xpath("//input[@name='username']")).sendKeys("aryan.pal4545");
        driver1.findElement(By.xpath("//input[@name='password']")).sendKeys("Aryan123321");
        driver1.findElement(By.xpath("//div[text()='Log in']/parent::button")).click();

        //        browserSetUp.setURL("https://www.google.com");
        //        browserSetUp.closeWindow();
        browserSetUp.quitBrowserSession();
        //        logger.warn("ghkuh");
        //        logger.debug("hhgfv");
        //        logger.fatal("kgkh");
        //        logger.trace("kghhk");
        //        logger.error("kgiugb");
    }


}