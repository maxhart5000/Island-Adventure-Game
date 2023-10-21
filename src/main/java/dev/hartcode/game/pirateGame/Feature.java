package dev.hartcode.game.pirateGame;

// Define the Feature enum, representing various town features with health points.
public enum Feature {
    ALLIGATOR(-45),
    ALOE(5),
    SPRING(25),
    JELLYFISH(-10),
    PINEAPPLE(10),
    SNAKE(-25),
    SUN_POISON(-15);

    private final int healthPoints;

    // Constructor to set the health points for each feature
    Feature(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    // Get the health points associated with a feature
    public int getHealthPoints() {
        return healthPoints;
    }
}
