package resuable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectHelper extends BrowserInvocation {

    private  Select select;

    public void selectClass(WebElement element){

        select = new Select(element);
    }

    public void selectByIndex(int indexValue, WebElement element){
        selectClass(element);
        select.selectByIndex(indexValue);
    }

    public void selectByValue( WebElement element,String value){
        selectClass(element);
        select.selectByValue(value);
    }

    public void selectByText(String value, WebElement element){
        selectClass(element);
        select.selectByVisibleText(value);
    }


}
