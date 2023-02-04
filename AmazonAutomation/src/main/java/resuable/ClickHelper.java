package resuable;


import org.openqa.selenium.By;

public class ClickHelper extends BrowserInvocation {

    public void clickButton(By locater){
       driver.findElement(locater).click();
    }
}
