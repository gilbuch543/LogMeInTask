package enums;

public enum OperatorType {

    Increment("Plus") ,Decrement("Minus"), multiply("Mult"), divide("Div"), calculate("Calc");

    private String value;



    OperatorType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
