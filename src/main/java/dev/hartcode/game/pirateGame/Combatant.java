package dev.hartcode.game.pirateGame;

import dev.hartcode.game.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Declare an abstract class Combatant, which implements the Player interface,
// and is part of the sealed interface hierarchy with Islander, Soldier, and Pirate.
public sealed abstract class Combatant implements Player permits Islander, Soldier, Pirate {
    private final String name;
    private final Map<String, Integer> gameData;
    private Weapon currentWeapon;

    // Constructor for Combatant, which takes a name.
    public Combatant(String name) {
        this.name = name;
    }

    // Constructor for Combatant, which takes a name and an initial game data map.
    public Combatant(String name, Map<String, Integer> gameData) {
        this.name = name;
        // Initialize the gameData map with the provided data.
        this.gameData.putAll(gameData);
    }

    // Initialize gameData with default values for health and score.
    {
        gameData = new HashMap<>(Map.of(
                "health", 100,
                "score", 0
        ));
    }

    // Get the current weapon of the Combatant.
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    // Set the current weapon of the Combatant.
    void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    // Get the value associated with a specific attribute (e.g., "health" or "score").
    int value(String name) {
        return gameData.get(name);
    }

    // Set the value associated with a specific attribute.
    protected void setValue(String name, int value) {
        gameData.put(name, value);
    }

    // Adjust the value associated with a specific attribute by a given adjustment.
    protected void adjustValue(String name, int adj) {
        // Use the compute method to modify the value based on the adjustment.
        gameData.compute(name, (k, v) -> v += adj);
    }

    // Adjust the health attribute by a given adjustment while ensuring it stays within the range [0, 100].
    protected void adjustHealth(int adj) {
        int health = value("health");
        health += adj;
        // Ensure that health does not go below 0 and does not exceed 100.
        health = (health < 0) ? 0 : Math.min(health, 100);
        setValue("health", health);
    }

    // Attempt to use the current weapon against an opponent Combatant.
    boolean useWeapon(Combatant opponent) {
        System.out.print(name + " used the " + currentWeapon);
        if (new Random().nextBoolean()) {
            System.out.print(" and HIT *** " + opponent.name() + " ***");
            opponent.adjustHealth(-currentWeapon.getHitPoints());
            System.out.printf("%n%s's Health: %d, %s's Health: %d%n", name, value("health"), opponent.name, opponent.value("health"));
            adjustValue("score", 50);
        } else {
            System.out.print(" and missed!\n");
        }
        return (opponent.value("health") <= 0);
    }

    // Get the name of the Combatant.
    @Override
    public String name() {
        return name;
    }

    // Get a string representation of the Combatant, which is its name.
    @Override
    public String toString() {
        return name;
    }

    // Get information about the Combatant, including their name and game data.
    public String information() {
        return name + " " + gameData;
    }
}
