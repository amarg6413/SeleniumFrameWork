package Utils;

import org.openqa.selenium.WebDriver;

public class DriverMethods {

    private WebDriver driver;

    public DriverMethods(WebDriver driver){
        this.driver=driver;
    }

    public void checkMethod() {
        System.out.println("This is check method");
    }
}