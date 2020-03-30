package enums;

public enum CalculatorOperatorType {

    ClearScreen("Clear");

    private String value;



    CalculatorOperatorType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
