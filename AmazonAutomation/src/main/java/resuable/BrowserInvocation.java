package resuable;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BrowserInvocation {

    public static WebDriver driver;
    public static Logger log = null;
    public  Properties prob;
    public WebDriver browserCode() throws IOException {

        String path = System.getProperty("user.dir");
        FileInputStream fis = new FileInputStream(new File(path + "/src/main/resources/utilities/config.Properties"));
        prob = new Properties();
        prob.load(fis);
        switch (prob.getProperty("browser").toLowerCase()){

            case "chrome":
                System.setProperty("webdriver.chrome.driver",path + "/src/main/resources/driver/chromedriver.exe");
                driver  = new ChromeDriver();
                break;
            case "edge":
                driver  = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Pass the valid browser name");
        }
        driver.navigate().to(prob.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }

}
