import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CalculatorHomePage {

    WebDriver driver;
    final static public Integer defaultTimeout = 10;
    protected long defaultWaitIntervals = 3;
    public Boolean markElementByDefault = true;
    private String homePageUrl ="https://web2.0calc.com/";
    private int ExactMatchCounter = 0;


    By acceptCookiesButton = By.cssSelector("[id ='cookieconsentallowall']");
    String btnOptionInCalculator = "//*[@id='Btn%s']";
     //By localHistory = By.xpath("//*[@id='histframe']");////*[contains(@class,'btn dropdown-toggle')]
    By localHistory = By.xpath("//*[@id='histframe']");



    String attribute = "title";
    //String equationWithoutResult = "//*[@data-inp=%s]";
    String resultsInLinePartOne ="//*[@id='histframe']//*[@data-inp='%s']";
    String resultsInLinePartTwo ="//*[@title='%s']";
    String optionalResult = "//*[@id='histframe']//*[@title='%s']";
    String opt = "//*[@id='histframe']//*[@title='5'][contains(@class,'r')]";
    By result;

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
// List<WebElement>
    public boolean isHistoryValidated(List<String>equations)
    {////*[@id='histframe']//*[@data-inp='2+3']
        List<WebElement> listOfElements = driver.findElement(localHistory).findElements(By.tagName("li"));


        //  driver.findElement(By.xpath("//*[@id='histframe']//*[@data-inp='2+3']")).findElement(By.xpath("//*[@title='5']")
        int resultsSize = listOfElements.size();

       // for (WebElement currentElement : listOfElements){
        for(WebElement element : listOfElements) {
            for (String currentEquation : equations) {
                if (isExactResult2(currentEquation,equations.get(2))) {
                    ExactMatchCounter++;
                }
            }
        }

        return ExactMatchCounter == resultsSize;
    }


    public Boolean isDisplayed(By locator, Integer... optionalTimeout) {
        Integer timeout = defaultTimeout;
        if (optionalTimeout != null) {
            timeout = (optionalTimeout.length > 0) ? optionalTimeout[0] : defaultTimeout;
        }
        return waitForElementVisibility(locator, timeout);
    }

    public Boolean waitForElementVisibility(By locator, Integer... timeout) {
        try {
            WebElement element = waitGenerator(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebDriverWait waitGenerator(Integer... timeout) {
        return new WebDriverWait(driver, (timeout.length > 0) ? timeout[0] : defaultTimeout, defaultWaitIntervals);
    }

    public boolean isExactResult(String expectedResults) {
        this.result = By.xpath(String.format(optionalResult,expectedResults));
            try {//driver.findElement(By.xpath("//*[@data-inp='2+3']"))
              String w =  driver.findElement(result).getAttribute(attribute);
            }
            catch (Exception e){
                System.out.println(e);
                System.out.println("\n" + String.format("Expected result = %s ,but did not receive it",expectedResults));
                return false;
            }
            return true;
    }
    public boolean isExactResult2(String expectedResults, String equation) {
        this.result = By.xpath(String.format(optionalResult,expectedResults));
        this.result = By.xpath(String.format(optionalResult,equation));
        try {
            //String w =  driver.findElement(result).getAttribute(attribute);
            WebElement w = driver.findElement(result);
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("\n" + String.format("Expected result = %s ,but did not receive it",expectedResults));
            return false;
        }
        return true;
    }


    public void typeInCalculator(String...items) {

       for(String currentItem : items){
            By clickOnNumber = By.xpath(String.format(btnOptionInCalculator, currentItem));
            clickOnItem(clickOnNumber);
        }
    }

}
