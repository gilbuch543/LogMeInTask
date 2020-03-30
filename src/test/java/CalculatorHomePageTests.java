import enums.CalculatorOperatorType;
import enums.Number;
import enums.MathOperatorType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculatorHomePageTests extends BaseFrontEnd {
    private CalculatorHomePage homePage;
    private String expectedResultEquationThree="20";
    private String expectedResultEquationFour="0.5";
    private Integer countCalculations = 0;

    @Before
    public void setup() {
        homePage = PageFactory.initElements(driver, CalculatorHomePage.class);
    }

    @Test
    public void TestCalculateEquations() {
        /**
         *  Create api on provider and verify it on PD
         *      Test steps:
         *          1. Open/Navigate to calculator home page(accept cookies in navigation)
         *          2. Calculate '2 + 3 = 5'
         *          3. Wait for results and verify results are as expected '5'
         *          4. Calculate '10 - 2 = 8'
         *          5. Wait for results and verify results are as expected '8'
         *          6. Calculate '(10 - 2)*2! = 20'
         *          7. Wait for results and verify result isn't '20' (result not as expected '20')
         *          8. Calculate 'Sin(30)'
         *
         */

        List<String> totalParametersInEquation = new ArrayList<String>();//= 0.5
        // 1. Open/Navigate to calculator home page(accept cookies in navigation)
        homePage.navigate();

        // 2. Calculate '2 + 3 = 5'

        homePage.typeInCalculator(Number.Two.getValue(),
                MathOperatorType.Increment.getValue(),
                Number.Three.getValue());
        totalParametersInEquation.add(Number.Two.getValue()+ MathOperatorType.getOperatorByMark(MathOperatorType.Increment)+ Number.Three.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());
       // homePage.isHistoryValidated(totalEquations);
        totalParametersInEquation.add(Number.Five.getValue());
        // 3. Wait for results and verify results are as expected
       Assert.assertTrue(String.format("result should be %s+%s =%s",Number.Two.getValue(),Number.Three.getValue(),Number.Five.getValue()),
               homePage.isExactResult(Number.Five.getValue()));

       //  4. Calculate '10 - 2 = 8'
        totalParametersInEquation.add(Number.One.getValue()+Number.Zero.getValue()+MathOperatorType.getOperatorByMark(MathOperatorType.Decrement)+Number.Two.getValue());
        homePage.typeInCalculator(CalculatorOperatorType.ClearScreen.getValue());
        homePage.typeInCalculator(Number.One.getValue(),Number.Zero.getValue(),MathOperatorType.Decrement.getValue(),Number.Two.getValue());
        totalParametersInEquation.add(Number.Eight.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        homePage.isHistoryValidated(totalParametersInEquation);

        // 5. Wait for results and verify results are as expected '8'
        Assert.assertTrue(String.format("result should be %s-%s =%s",Number.Ten.getValue(),Number.Two.getValue(),Number.Eight.getValue()),
                homePage.isExactResult(Number.Eight.getValue()));

        // 6. Calculate '(10 - 2)*2! = 20'
        homePage.typeInCalculator(CalculatorOperatorType.ClearScreen.getValue());
        homePage.typeInCalculator(MathOperatorType.LeftParentheses.getValue(),
                Number.One.getValue(),
                Number.Zero.getValue(),
                MathOperatorType.Decrement.getValue(),
                Number.Two.getValue(),
                MathOperatorType.RightParentheses.getValue(),
                MathOperatorType.multiply.getValue(),
                Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());
       /* homePage.typeInCalculator(Number.One.getValue());
        homePage.typeInCalculator(Number.Zero.getValue());
        homePage.typeInCalculator(MathOperatorType.Decrement.getValue());
        homePage.typeInCalculator(Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.RightParentheses.getValue());
        homePage.typeInCalculator(MathOperatorType.multiply.getValue());
        homePage.typeInCalculator(Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.Calculate.getValue());*/

        // 3. Wait for expectedResult not '20'
        Assert.assertFalse(String.format("result of: (%s-%s)*%s  is not %s"
                ,Number.Ten.getValue(),Number.Two.getValue(),Number.Two.getValue(),expectedResultEquationThree),
                homePage.isExactResult(expectedResultEquationThree));

        homePage.typeInCalculator(CalculatorOperatorType.ClearScreen.getValue());
        homePage.typeInCalculator(MathOperatorType.Sine.getValue(),
                Number.Three.getValue(), Number.Zero.getValue(),
                MathOperatorType.RightParentheses.getValue());

        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 3. Wait for results and verify results are as expected (0.5)
        Assert.assertTrue(String.format("result of : %s(%s) =%s"
                , MathOperatorType.Sine.getValue(),Number.Three.getValue()+Number.Zero.getValue(),expectedResultEquationFour),
                homePage.isExactResult(expectedResultEquationFour));
       // homePage.isHistoryValidated();
        //homePage.validateResults();
    }


    @Test
    public void TestCalculateEquation2() {
        /**
         *  Create api on provider and verify it on PD
         *      Test steps:
         *          1. Open/Navigate to calculator home page(accept cookies in navigation)
         *          2. Calculate '10 - 2 = 8'
         *          3. Wait for results and verify results are as expected
         */


        // 1. Open/Navigate to calculator home page(accept cookies in navigation)
        homePage.navigate();

        // 2. Calculate '10 - 2 = 8'
        homePage.typeInCalculator(Number.One.getValue());
        homePage.typeInCalculator(Number.Zero.getValue());
        homePage.typeInCalculator(MathOperatorType.Decrement.getValue());
        homePage.typeInCalculator(Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 3. Wait for results and verify results are as expected
        Assert.assertTrue(String.format("result should be %s-%s =%s",Number.Ten.getValue(),Number.Two.getValue(),Number.Eight.getValue()),
                homePage.isExactResult(Number.Eight.getValue()));
    }

    @Test
    public void TestCalculateEquation3() {
        /**
         *  Create api on provider and verify it on PD
         *      Test steps:
         *          1. Open/Navigate to calculator home page(accept cookies in navigation)
         *          2. Calculate '(10 - 2)*2! = 20'
         *          3. Wait for results and verify result isn't '20' (result not as expected)
         */


        // 1. Open/Navigate to calculator home page(accept cookies in navigation)
        homePage.navigate();

        // 2. Calculate '(10 - 2)*2! = 20'
        homePage.typeInCalculator(MathOperatorType.LeftParentheses.getValue());
        homePage.typeInCalculator(Number.One.getValue());
        homePage.typeInCalculator(Number.Zero.getValue());
        homePage.typeInCalculator(MathOperatorType.Decrement.getValue());
        homePage.typeInCalculator(Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.RightParentheses.getValue());
        homePage.typeInCalculator(MathOperatorType.multiply.getValue());
        homePage.typeInCalculator(Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 3. Wait for expectedResult
        Assert.assertFalse(String.format("result of: (%s-%s)*%s  is not %s"
                ,Number.Ten.getValue(),Number.Two.getValue(),Number.Two.getValue(),expectedResultEquationThree),
                homePage.isExactResult(expectedResultEquationThree));
    }

    @Test
    public void TestCalculateEquation4() {
        /**
         *  Create api on provider and verify it on PD
         *      Test steps:
         *          1. Open/Navigate to calculator home page(accept cookies in navigation)
         *          2. Calculate 'Sin(30)
         *          3. Wait for results and verify results are as expected (0.5)
         */


        // 1. Open/Navigate to calculator home page(accept cookies in navigation)
        homePage.navigate();

        // 2. Calculate Sin(30)
        homePage.typeInCalculator(MathOperatorType.Sine.getValue());
        homePage.typeInCalculator(Number.Three.getValue());
        homePage.typeInCalculator(Number.Zero.getValue());
        homePage.typeInCalculator(MathOperatorType.RightParentheses.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 3. Wait for results and verify results are as expected (0.5)
        Assert.assertTrue(String.format("result of : %s(%s) =%s"
                , MathOperatorType.Sine.getValue(),Number.Three.getValue()+Number.Zero.getValue(),expectedResultEquationFour),
                homePage.isExactResult(expectedResultEquationFour));
    }
}
