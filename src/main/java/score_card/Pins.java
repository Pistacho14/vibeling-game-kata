package score_card;

public enum Pins {
    SPAIR("/"),
    STRIKE("X"),
    FOUL("0"),
    TOTAL_PINS(10),
    ZERO(0),
    ONE(1),
    LAST_FRAME_ROLLS(3),
    ROLLS_STRIKE(1),
    NORMAL_FRAMES_ROLLS(2);

    private final String symbol;
    private final int numericValue;

    Pins(String symbol) {
        this.symbol = symbol;
        this.numericValue = Character.isDigit(symbol.charAt(0)) ? Integer.parseInt(symbol) : 0;
    }

    Pins(int numericValue) {
        this.symbol = Integer.toString(numericValue);
        this.numericValue = numericValue;
    }

    public String symbol() {
        return symbol;
    }

    public int numericValue() {
        return numericValue;
    }
}
