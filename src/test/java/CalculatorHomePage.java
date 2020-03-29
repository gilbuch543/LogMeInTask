import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalculatorHomePage {

    WebDriver driver;
    final static public Integer defaultTimeout = 10;
    protected long defaultWaitIntervals = 3;
    public Boolean markElementByDefault = true;
    private String homePageUrl ="https://web2.0calc.com/";


    By acceptCookiesButton = By.cssSelector("[id ='cookieconsentallowall']");
    String btnOptionInCalculator = "//*[@id='Btn%s']";

     By localHistory = By.xpath("//*[@id='histframe']");




    By searchButton = By.xpath("//*[@id='searchbox-searchbutton']");
    By SearchInputLocator = By.xpath("//*[@id='gs_lc50']/*[@aria-label='חיפוש במפות Google']");//Sorry driver show google maps in hebrew
    By zoomInButton = By.xpath("//*[contains(@class,'widget-zoom-in')]");
    String searchPageIndicator = "//*[contains(@class,'section-hero-header-title-description')]//*[text()='רומא']";
    By Input = By.xpath("//*[@id='input']");
    String x = "//*[@id='input']";

    public CalculatorHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        driver.navigate().to(homePageUrl);
        clickAcceptCookies();
    }


    public void clickAcceptCookies(){
        try {
            System.out.println("Trying to find button");
            if (isDisplayed(acceptCookiesButton, 3)) {
                System.out.println(String.format("element %s displayed", acceptCookiesButton));
                clickOnItem(acceptCookiesButton);
            }
        } catch (Exception e) {
            System.out.println(String.format("Unable to find button %s", acceptCookiesButton));
        }
    }




    public void clickOnItem(By locator) {
        System.out.println(String.format("Trying to click on %s",locator));
        driver.findElement(locator).click();
        System.out.println(String.format("Clicked on %s",locator));
    }

    public WebElement getHistory(){
        return driver.findElement(localHistory).findElement(By.xpath("//*[@id='histframe']//*[@title='%s']"));
    }


    public void highlightElement(By locator) {
        WebElement elem = driver.findElement(locator);
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
    }


    public Boolean isDisplayed(By locator, Integer... optionalTimeout) {
        Integer timeout = defaultTimeout;
        if (optionalTimeout != null) {
            timeout = (optionalTimeout.length > 0) ? optionalTimeout[0] : defaultTimeout;
        }
        return waitForElementVisibility(locator, timeout);
    }

    public Boolean waitForElementVisibility(By locator, Integer... timeout) {
        return this.waitForElementVisibility(locator, markElementByDefault, timeout);
    }

    public Boolean waitForElementVisibility(By locator, Boolean activateMarkElement, Integer... timeout) {
        try {
            WebElement element = waitGenerator(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element == null) {
                return false;
            }
           // if (activateMarkElement) {
              //  highlightElement(locator);
          //  }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebDriverWait waitGenerator(Integer... timeout) {
        return new WebDriverWait(driver, (timeout.length > 0) ? timeout[0] : defaultTimeout, defaultWaitIntervals);
    }

    public void searchForLocation(String location) {
        driver.findElement(SearchInputLocator).sendKeys(location);
    }

    public void zoomIn() {
        driver.findElement(zoomInButton).click();
    }

    public String WaitForResults() {
        WebDriverWait wait = new WebDriverWait(this.driver, 5L);
        WebElement wb1 = wait.until(ExpectedConditions.presenceOfElementLocated(Input));
        if (!wb1.isDisplayed()) {
            System.out.println("Not finished loading");
        }
        wait.until(ExpectedConditions.attributeContains(By.id("title"),"value", "5"));

        return wb1.getText();
    }

    public void type(String item) {
        By clickOnNumber = By.xpath(String.format(btnOptionInCalculator,item));
        clickOnItem(clickOnNumber);


    }

   // public void validateResults() {
     //  Integer result = getResult();

  //  }

  /*  public Integer getResult(Number result) {
        WebDriverWait wait = new WebDriverWait(this.driver, 5L);
        WebElement wb1 =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchPageIndicator)));
        wait.until(ExpectedConditions.attributeContains(By.id("input"),"value", result));
        return driver.findElement(Input).getAttribute("value");
   // }*/
}
