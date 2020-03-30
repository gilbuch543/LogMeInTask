package enums;

public enum Number {
    One("1"), Two("2"), Three("3"), Four("4"), Five("5"), Six("6"), Seven("7"),
    Eight("8") , Nine("9"), Ten("10"), Zero("0");

    private String value;



    Number(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
