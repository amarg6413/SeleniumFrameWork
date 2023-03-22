package Utils;

import Listeners.TestListener;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverMethods extends Waits{

    private WebDriver driver;

    public DriverMethods(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public void click(WebElement element){
        TestListener.test.get().log(Status.INFO,"Clicking on element ");
        fluentWaitElementToVisible(element).click();
    }

    public void submit(WebElement element){
        TestListener.test.get().log(Status.INFO,"Submitting it");
        fluentWaitElementToVisible(element).submit();
    }

    public void sendKeys(WebElement element,CharSequence... keysToSend){
        TestListener.test.get().log(Status.INFO,"Entering keys:- "+keysToSend.toString());
        fluentWaitElementToVisible(element).sendKeys(keysToSend);
    }

    public void clear(WebElement element){
        TestListener.test.get().log(Status.INFO,"Clearing data for element");
        fluentWaitElementToVisible(element).clear();
    }

    public String getTagName(WebElement element){
        TestListener.test.get().log(Status.INFO,"Clearing data for element");
        return fluentWaitElementToVisible(element).getTagName();
    }
}