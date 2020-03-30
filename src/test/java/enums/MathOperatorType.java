package enums;

public enum MathOperatorType {

    Increment("Plus") ,Decrement("Minus"), multiply("Mult"), divide("Div"), Equal("Calc"),
    LeftParentheses("ParanL") ,RightParentheses("ParanR") , Sine("Sin");

    private String value;


    public static String getOperatorByMark(MathOperatorType operator) {
            switch(operator){

                case Increment:
                    return "+";
                case Decrement:
                    return "-";

                case multiply:
                    return "*";

              //  case Equal:
                //    return "=\u00a0";
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
