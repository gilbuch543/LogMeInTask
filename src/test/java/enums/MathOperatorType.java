package enums;

public enum MathOperatorType {

    Increment("Plus"), Decrement("Minus"), multiply("Mult"), divide("Div"), Equal("Calc"),
    LeftParentheses("ParanL"), RightParentheses("ParanR"), Sine("Sin");

    private String value;


    public static String getOperatorByMark(MathOperatorType operator) {
        switch (operator) {

            case Increment:
                return "+";
            case Decrement:
                return "-";

            case multiply:
                return "*";

            case LeftParentheses:
                return "(";

            case RightParentheses:
                return ")";

            case Sine:
                return "sin";

        }
        return "";
    }


    MathOperatorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
