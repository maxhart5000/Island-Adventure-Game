package dev.hartcode.game.pirateGame;

// Define the Loot enum, representing various loot items with their values.
public enum Loot {
    SILVER_COIN(5),
    GOLD_COINS(10),
    GOLD_RING(125),
    PEARL_NECKLACE(250),
    SAPPHIRE(500);

    private final int value;

    // Constructor to set the value for each loot item
    Loot(int value) {
        this.value = value;
    }

    // Get the value associated with a loot item
    public int getValue() {
        return value;
    }
}
