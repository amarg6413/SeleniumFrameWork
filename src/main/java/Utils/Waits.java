package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Waits{

    private WebDriver driver;
    private WebDriverWait wait;
    private Wait<WebDriver> fluentWait;

    Waits(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        fluentWait= new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class)
                .withMessage("Waiting for visibility of element");
    }

    public WebElement fluentWaitElementToVisible(WebElement element) {
        return (WebElement) fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement fluentWaitElementToVisible(WebDriver driver,By by){
        return (WebElement) fluentWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
}
