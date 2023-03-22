package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DriverMethods extends Waits {

    private WebDriver driver;
    private Actions actions;

    private Log log=new Log();

    public DriverMethods(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void click(WebElement element) {
        fluentWaitElementToVisible(element).click();
    }

    public void submit(WebElement element) {
        fluentWaitElementToVisible(element).submit();
    }

    public void sendKeys(WebElement element, CharSequence... keysToSend) {
        fluentWaitElementToVisible(element).sendKeys(keysToSend);
    }

    public void clear(WebElement element) {
        fluentWaitElementToVisible(element).clear();
    }

    public String getTagName(WebElement element) {
        return fluentWaitElementToVisible(element).getTagName();
    }

    public void hoverAndClick(WebElement element) {
        fluentWaitElementToVisible(element);
        actions.moveToElement(element).click().build().perform();
    }

    public void selectValueFromDropdown(WebElement dropDown,By valueLocator){
        actions.moveToElement(dropDown).click().moveToElement(findElement(driver,valueLocator)).build().perform();
    }

    public WebElement findElement(WebDriver driver, By by){
        return fluentWaitElementToVisible(driver,by);
    }

}