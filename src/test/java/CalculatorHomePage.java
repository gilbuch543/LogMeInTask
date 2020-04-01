import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CalculatorHomePage {

    WebDriver driver;
    final static public Integer defaultTimeout = 10;
    protected long defaultWaitIntervals = 3;
    private String homePageUrl = "https://web2.0calc.com/";
    private int ExactMatchCounter = 0;


    By acceptCookiesButtonLocator = By.cssSelector("[id ='cookieconsentallowall']");
    String btnOptionInCalculator = "//*[@id='Btn%s']";
    By localHistoryLocator = By.xpath("//*[@id='histframe']");
    String attribute = "title";
    String resultsInLinePartOne = "//*[@id='histframe']//*[@data-inp='%s']";
    String resultsInLinePartTwo = "//*[@id='histframe']//*[@title='%s']";
    By homePageLocator = By.xpath("//*[@id='menuhome']//*[text()='Home']");
    By result;

    public CalculatorHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        driver.navigate().to(homePageUrl);
        Assert.assertTrue("Calculator home page not loaded", isDisplayed(homePageLocator));
        clickAcceptCookies();
    }


    public void clickAcceptCookies() {
        clickOnItem(acceptCookiesButtonLocator);
    }


    public void clickOnItem(By locator) {
        System.out.println(String.format("Trying to click on %s", locator));
        driver.findElement(locator).click();
        System.out.println(String.format("Clicked on %s", locator));
    }

    public boolean isHistoryValidated(ArrayList<Formula> formulas) {
        /**
         *          get list of formulas and verify equation and results in history list - calculator page
         *
         */
        List<WebElement> listOfElements = driver.findElement(localHistoryLocator).findElements(By.tagName("li"));

        int index = 0;
        for (WebElement element : listOfElements) {
            System.out.println(String.format("Checking equation : %s ", formulas.get(index).getEquation()));
            System.out.println(String.format("Checking equation result : %s ", formulas.get(index).getResult()));
            if (isEquationValidated(formulas.get(index).getEquation(), element) && isResultValidated(formulas.get(index).getResult(), element)) {
                ExactMatchCounter++;
                index++;
            }
        }
        return formulas.size() == ExactMatchCounter && listOfElements.size() == formulas.size();
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

    public boolean isResultValidated(String expectedResults) {
        return isResultValidated(expectedResults, null);
    }


    public boolean isEquationValidated(String equation, WebElement element) {
        this.result = By.xpath(String.format(resultsInLinePartOne, equation));
        try {

            WebElement w = element.findElement(result);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\n" + String.format("Expected Equation  = %s ,but no matching element", equation));
            return false;
        }
        return true;
    }

    public boolean isResultValidated(String equation, WebElement element) {
        this.result = By.xpath(String.format(resultsInLinePartTwo, equation));
        try {
            if (element == null) {
                driver.findElement(result).getAttribute(attribute);
            } else {
                element.findElement(result);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\n" + String.format("Expected result = %s ,but no matching element", equation));
            return false;
        }
        return true;
    }


    public void typeInCalculator(String... items) {
        for (String currentItem : items) {
            By clickOnNumber = By.xpath(String.format(btnOptionInCalculator, currentItem));
            clickOnItem(clickOnNumber);
        }
    }

}
