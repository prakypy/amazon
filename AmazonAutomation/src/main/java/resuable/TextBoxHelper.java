package resuable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TextBoxHelper extends BrowserInvocation {
    public void enterTextValue(By locater,String textValue){

        driver.findElement(locater).sendKeys(textValue);
    }
    public void enterTextValueWithEnterKey(By locater,String textValue){

        driver.findElement(locater).sendKeys(textValue + Keys.ENTER);
    }

    public String getTextValue(By locater){
      String textValue =  driver.findElement(locater).getText();
      return textValue;
    }

    public void clearTextValue(By locater){
        driver.findElement(locater).clear();

    }
}
