package dev.hartcode.game.pirateGame;

// Define the Soldier class, which is a specific type of Combatant
public final class Soldier extends Combatant {

    // Constructor for creating a Soldier with a name and a weapon
    public Soldier(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
