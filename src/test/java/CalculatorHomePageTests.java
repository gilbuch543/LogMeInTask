import enums.CalculatorOperatorType;
import enums.Number;
import enums.MathOperatorType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class CalculatorHomePageTests extends BaseFrontEnd {
    private CalculatorHomePage homePage;
    private String expectedResultEquationThree = "20";
    private String expectedResultEquationFour = "0.5";
    private String realResultEquationThree = "16";

    @Before
    public void setup() {
        homePage = PageFactory.initElements(driver, CalculatorHomePage.class);
    }

    @Test
    public void TestCalculateEquations() {
        /**
         *  Calculate formulas and verify history list
         *      Test steps:
         *          1. Open/Navigate to calculator home page(accept cookies in navigation)
         *          2. Calculate '2 + 3 = 5'
         *          3. Wait for results and verify results are as expected '5'
         *          4. Calculate '10 - 2 = 8'
         *          5. Wait for results and verify results are as expected '8'
         *          6. Calculate '(10 - 2)*2! = 20'
         *          7. Wait for results and verify result isn't '20' (result not as expected '20')
         *          8. Calculate 'Sin(30)'
         *          9. Wait for results and verify results are as expected '0.5'
         *          10. Validate correct history of all formulas above
         *
         */

        ArrayList<Formula> allFormulasParameters = new ArrayList<Formula>();

        // 1. Open/Navigate to calculator home page(accept cookies in navigation)
        homePage.navigate();

        // 2. Calculate '2 + 3 = 5'
        homePage.typeInCalculator(Number.Two.getValue(),
                MathOperatorType.Increment.getValue(),
                Number.Three.getValue());

        Formula currentFormula = new Formula(Number.Two.getValue() + MathOperatorType.getOperatorByMark(MathOperatorType.Increment) + Number.Three.getValue(), (Number.Five.getValue()));
        allFormulasParameters.add(currentFormula);
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 3. Wait for results and verify results are as expected '5'
        Assert.assertTrue(String.format("Result should be %s+%s =%s", Number.Two.getValue(), Number.Three.getValue(), Number.Five.getValue()),
                homePage.isResultValidated(Number.Five.getValue()));

        //  4. Calculate '10 - 2 = 8'
        homePage.typeInCalculator(CalculatorOperatorType.ClearScreen.getValue());
        currentFormula = new Formula(Number.One.getValue() + Number.Zero.getValue() + MathOperatorType.getOperatorByMark(MathOperatorType.Decrement) + Number.Two.getValue(), Number.Eight.getValue());
        allFormulasParameters.add(currentFormula);
        homePage.typeInCalculator(Number.One.getValue(), Number.Zero.getValue(), MathOperatorType.Decrement.getValue(), Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 5. Wait for results and verify results are as expected '8'
        Assert.assertTrue(String.format("Result should be %s-%s =%s", Number.Ten.getValue(), Number.Two.getValue(), Number.Eight.getValue()),
                homePage.isResultValidated(Number.Eight.getValue()));

        // 6. Calculate '(10 - 2)*2! = 20'
        homePage.typeInCalculator(CalculatorOperatorType.ClearScreen.getValue());
        currentFormula = new Formula(MathOperatorType.getOperatorByMark(MathOperatorType.LeftParentheses) + Number.One.getValue() + Number.Zero.getValue()
                + MathOperatorType.getOperatorByMark(MathOperatorType.Decrement) + Number.Two.getValue() + MathOperatorType.getOperatorByMark(MathOperatorType.RightParentheses) +
                MathOperatorType.getOperatorByMark(MathOperatorType.multiply) + Number.Two.getValue(), realResultEquationThree);
        allFormulasParameters.add(currentFormula);
        homePage.typeInCalculator(MathOperatorType.LeftParentheses.getValue(),
                Number.One.getValue(),
                Number.Zero.getValue(),
                MathOperatorType.Decrement.getValue(),
                Number.Two.getValue(),
                MathOperatorType.RightParentheses.getValue(),
                MathOperatorType.multiply.getValue(),
                Number.Two.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 7. Wait for results and verify result isn't '20' (result not as expected '20')'
        Assert.assertFalse(String.format("Result of: (%s-%s)*%s = %s , wrong result"
                , Number.Ten.getValue(), Number.Two.getValue(), Number.Two.getValue(), expectedResultEquationThree),
                homePage.isResultValidated(expectedResultEquationThree));

        // 8. Calculate 'Sin(30)'
        homePage.typeInCalculator(CalculatorOperatorType.ClearScreen.getValue());
        currentFormula = new Formula(MathOperatorType.getOperatorByMark(MathOperatorType.Sine) +
                MathOperatorType.getOperatorByMark(MathOperatorType.LeftParentheses) +
                Number.Three.getValue() + Number.Zero.getValue() + MathOperatorType.getOperatorByMark(MathOperatorType.RightParentheses), expectedResultEquationFour);
        allFormulasParameters.add(currentFormula);
        homePage.typeInCalculator(MathOperatorType.Sine.getValue(),
                Number.Three.getValue(), Number.Zero.getValue(),
                MathOperatorType.RightParentheses.getValue());
        homePage.typeInCalculator(MathOperatorType.Equal.getValue());

        // 9. Wait for results and verify results are as expected '0.5'
        Assert.assertTrue(String.format("Result of : %s(%s) =%s"
                , MathOperatorType.Sine.getValue(), Number.Three.getValue() + Number.Zero.getValue(), expectedResultEquationFour),
                homePage.isResultValidated(expectedResultEquationFour));

        // 10. Validate correct history of all formulas above
        Assert.assertTrue("Validation failed, History should match all formulas", homePage.isHistoryValidated(allFormulasParameters));
    }
}
