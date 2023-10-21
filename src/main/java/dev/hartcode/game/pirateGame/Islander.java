package dev.hartcode.game.pirateGame;

// Islander is a specific type of combatant in the PirateGame
public final class Islander extends Combatant {

    // Constructor for the Islander class
    public Islander(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
