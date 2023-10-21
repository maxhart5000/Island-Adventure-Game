package dev.hartcode.game.pirateGame;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

// Define the Weapon enum, representing various weapons in the game.
public enum Weapon {
    KNIFE(0, 10),
    AXE(0, 30),
    MACHETE(1, 40),
    PISTOL(1, 50);

    public final int hitPoints;
    private final int levelRequired;

    // Constructor to set the level requirement and hit points for each weapon
    Weapon(int levelRequired, int hitPoints) {
        this.levelRequired = levelRequired;
        this.hitPoints = hitPoints;
    }

    // Get the hit points of the weapon
    public int getHitPoints() {
        return hitPoints;
    }

    // Get the level requirement of the weapon
    public int getLevelRequired() {
        return levelRequired;
    }

    // Get a weapon by its first initial character (e.g., 'K' for Knife)
    public static Weapon getWeaponByChar(char firstInitial) {
        for (Weapon w : values()) {
            if (w.name().charAt(0) == firstInitial) {
                return w;
            }
        }
        // Return the first weapon if the character is not found (default)
        return values()[0];
    }

    // Get a list of weapons available at or below a specified level
    public static List<Weapon> getWeaponsByLevel(int levelOfPlay) {
        List<Weapon> weapons = new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(w -> (w.levelRequired > levelOfPlay));
        return weapons;
    }
}
