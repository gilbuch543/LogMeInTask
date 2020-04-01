public class Formula {
    private String equation;
    private String result;


    Formula(String equation, String result) {
        this.equation = equation;
        this.result = result;
    }

    public String toString() {
        return equation + "=" + result;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String equation) {
        this.result = result;
    }
}
