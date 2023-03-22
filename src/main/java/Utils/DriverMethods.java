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

    public void checkMethod() {
        System.out.println("This is check method");
    }

    public void click(WebElement element){
        TestListener.test.get().log(Status.INFO,"Clicking on element");
        fluentWaitElementToVisible(element).click();
    }
}