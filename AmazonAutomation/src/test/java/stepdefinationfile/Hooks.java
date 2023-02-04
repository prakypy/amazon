package stepdefinationfile;

import io.cucumber.java.*;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import resuable.BrowserInvocation;

public class Hooks extends BrowserInvocation {

    @Before
    public void preCondition(){
       String path = System.getProperty("user.dir") + "/src/test/resources/log4j2.properties";
       PropertyConfigurator.configure(path);
    }

    @AfterStep
    public void captureScreenshot(Scenario sc){

     byte[] screenshot =   ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
     sc.attach(screenshot,"image/png",null);

    }

    @After
    public void postCondition(){
        driver.close();
        driver.quit();
    }

}
