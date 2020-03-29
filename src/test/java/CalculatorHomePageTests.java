import enums.Number;
import enums.OperatorType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class CalculatorHomePageTests extends BaseFrontEnd {
    public CalculatorHomePage homePage;


    @Before
    public void setup() {
        homePage = PageFactory.initElements(driver, CalculatorHomePage.class);
    }

    @Test
    public void TestVerifyButtonClickResult() {
        /**
         *  Create api on provider and verify it on PD
         *      Test steps:
         *          1. Open/Navigate to calculator home page(accept cookies in navigation)
         *          2. Calculate '2 + 3 = 5'
         *          3. Calculate '10 - 2 = 8'
         *          4. Calculate '(10-2)*2!=20
         *          5. Calculate 'sin(30) = 0.5'
         *          6. Validate correct history of all formulas above (by useing calculator history) -
         *             there are 4 formulas in history list
         *          7. Take a screenshot
         *          8. Zoom in
         *
         *
         */


        // 1. Open/Navigate to calculator home page(accept cookies in navigation)
        homePage.navigate();
        // 2. 2. Calculate '2 + 3 = 5'
        homePage.type(Number.Two.getValue());
        homePage.type(OperatorType.Increment.getValue());
        homePage.type(Number.Five.getValue());
        homePage.type(OperatorType.calculate.getValue());
        homePage.getHistory();
    }
}
