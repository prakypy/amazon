package stepdefinationfile;

import excelhandling.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resuable.BrowserInvocation;
import resuable.ClickHelper;
import resuable.SelectHelper;
import resuable.TextBoxHelper;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AmazonValidationSteps {
    private  WebDriver driver;
    private TextBoxHelper textBoxHelper;
    private ClickHelper clickHelper;

   private List <String>  addLinks;
    private BrowserInvocation browserInvocation;
    private String beforeSortValue;
    private String afterSortValue;

    private SelectHelper selectHelper;

    public AmazonValidationSteps(){
        browserInvocation = new BrowserInvocation();
        textBoxHelper = new TextBoxHelper();
        clickHelper =new ClickHelper();
        selectHelper = new SelectHelper();
    }
    @Given("User login and navigate to homepage of application")
    public void userLoginAndNavigateToHomepageOfApplication() throws IOException, InterruptedException {

        driver = browserInvocation.browserCode();
        clickHelper.clickButton(By.id("nav-link-accountList-nav-line-1"));
        textBoxHelper.enterTextValue(By.id("ap_email"),browserInvocation.prob.getProperty("username"));
        clickHelper.clickButton(By.id("continue"));
        textBoxHelper.enterTextValue(By.id("ap_password"),browserInvocation.prob.getProperty("password"));
        clickHelper.clickButton(By.id("signInSubmit"));
        Thread.sleep(2000);
    }
    @When("User clicks on Sign out Button")
    public void userClicksOnSignOutButton() {
        WebElement accountInfo = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        Actions accountHover = new Actions(driver);
        accountHover.clickAndHold(accountInfo).build().perform();
        driver.findElement(By.linkText("Sign Out")).click();

    }
    @Then("Verify Whether the user signout succesfully")
    public void verifyWhetherTheUserSignoutSuccesfully() {
       String title = driver.getTitle();
       Assert.assertEquals(title,"Amazon Sign-In");
    }

    @When("user selects the product and add the product to kart")
    public void userSelectsTheProductAndAddTheProductToKart() {
        textBoxHelper.enterTextValueWithEnterKey(By.id("twotabsearchtextbox"),ExcelReader.readExcel("SearchData",0,0));
        WebElement result = driver.findElement(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']"));
        result.findElements(By.xpath("//div[@class='sg-row']/div[2]/div/div/div/h2/a")).get(2).click();
        clickHelper.clickButton(By.id("add-to-cart-button"));
    }
    @Then("verify the added product")
    public void verifyTheProductDetails() {
        String successMessage = textBoxHelper.getTextValue(By.xpath("//div[@class='a-box a-alert-inline a-alert-inline-success sw-atc-message']/following-sibling::span"));
        if(successMessage.contains("Added to Cart")){
            Assert.assertTrue(true);
        }
        String addCartCount = textBoxHelper.getTextValue(By.xpath("//div[@id='nav-cart-count-container']/span"));
        Assert.assertEquals(addCartCount,"1");
    }
    @When("User iterate the available links in Login Page")
    public void userIterateTheAvailableLinksInLoginPage() {
        addLinks = new ArrayList<String>();
        Set<String> windowsProps = driver.getWindowHandles();
        for (String win:windowsProps){
            String title =driver.switchTo().window(win).getTitle();
            addLinks.add(title);
        }
    }
    @Then("User Verify the Hyperlinks")
    public void userVerifyTheHyperlinks() {

        for (String linkName:addLinks){
            if(linkName.equals("Amazon Password Assistance")){

                Assert.assertTrue(true);

            }
            else if(linkName.contains("Account & Login")){
                Assert.assertTrue(true);

            }
            else if (linkName.contains("Sign-In")) {
                Assert.assertTrue(true);
                
            }
        }
    }

    @Given("User Navigate to Home Page and opens the hyperlinks")
    public void userNavigateToHomePage() throws IOException {

        driver = browserInvocation.browserCode();
        clickHelper.clickButton(By.id("nav-link-accountList-nav-line-1"));
        driver.findElement(By.partialLinkText("Need help")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='a-expander-content a-expander-inline-content a-expander-inner a-expander-content-expanded']/a")));
        List<WebElement> openLinkCount = driver.findElements(By.xpath("//div[@class='a-expander-content a-expander-inline-content a-expander-inner a-expander-content-expanded']/a"));
        for(WebElement link:openLinkCount ){
            String keyAction = Keys.chord(Keys.CONTROL,Keys.ENTER);
            link.sendKeys(keyAction);
        }

    }

    @And("User Removes the Product from the Kart")
    public void userRemovesTheProductFromTheKart() {
        clickHelper.clickButton(By.id("sw-gtc"));
        clickHelper.clickButton(By.xpath("//input[contains(@name,'submit.delete')]"));
    }

    @Then("Verify the removed Product")
    public void verifyTheRemovedProduct() {

        String successMessage = textBoxHelper.getTextValue(By.xpath("//h1[contains(text(),'Your Amazon Cart is empty')]"));
        if(successMessage.contains("Your Amazon Cart is empty")){
            Assert.assertTrue(true);
        }
        String addCartCount = textBoxHelper.getTextValue(By.xpath("//div[@id='nav-cart-count-container']/span"));
        Assert.assertEquals(addCartCount,"0");
    }

    @When("user navigates to the clothing session")
    public void userNavigatesToTheClothingSession() {

        clickHelper.clickButton(By.id("nav-hamburger-menu"));
        clickHelper.clickButton(By.xpath("//div[@id='hmenu-content']/ul/li[11]"));
        clickHelper.clickButton(By.xpath("//div[@id='hmenu-content']//ul[@class='hmenu-compress-section']/li[6]"));
        clickHelper.clickButton(By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']/li[3]"));

    }
    @And("User selects the product by sorting option")
    public void userSelectsTheProductBySortingOption() {
        beforeSortValue = textBoxHelper.getTextValue(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));
        selectHelper.selectByIndex(1,driver.findElement(By.id("s-result-sort-select")));
        afterSortValue = textBoxHelper.getTextValue(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));

            }

    @Then("Verfiy the sorted product by its value")
    public void verfiyTheSortedProductByItsValue() {

        if(!afterSortValue.equals(beforeSortValue)){
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue("Sorting doesn't take place",false);
        }
    }

    @And("User selects the product by filter option")
    public void userSelectsTheProductByFilterOption() {
        clickHelper.clickButton(By.xpath("//div[@id='brandsRefinements']/ul[2]/li[2]/span/a/div"));
    }

    @Then("Verfiy the filtered product is displayed")
    public void verfiyTheFilteredProductIsDisplayed() {
       String product = textBoxHelper.getTextValue(By.xpath("//*[contains(text(),'adidas') and  contains(@class,'a-size-base')]"));

       if(product.contains("adidas")){
           Assert.assertTrue(true);
       }
       else{
           Assert.assertTrue("Product Not filtered",false);
       }
          }

    @Then("Verify the Page navigation")
    public void verifyThePageNavigation() {

        String productFirstPage = textBoxHelper.getTextValue(By.xpath("//*[contains(text(),'adidas') and  contains(@class,'a-size-base')]"));
        clickHelper.clickButton(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"));
        String productSecondPage = textBoxHelper.getTextValue(By.xpath("//*[contains(text(),'adidas') and  contains(@class,'a-size-base')]"));
        if(!productSecondPage.equals(productFirstPage)){
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue("Navigation is not success",false);
        }
    }


}
