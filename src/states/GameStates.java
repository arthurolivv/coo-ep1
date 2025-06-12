package states;

public enum GameStates {
    ACTIVE(1),
    INACTIVE(0),
    EXPLODING(2);

    private final int value;

    GameStates(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
