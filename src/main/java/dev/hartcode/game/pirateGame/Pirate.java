package dev.hartcode.game.pirateGame;

import java.util.*;
import java.util.Random;

// Pirate is a specific type of combatant in the PirateGame
public final class Pirate extends Combatant {
    private final List<Town> townsVisited = new LinkedList<>();
    List<Loot> loot;
    List<Feature> features;
    List<Combatant> opponents;

    // Constructor for the Pirate class
    public Pirate(String name) {
        super(name, Map.of("level", 0, "townIndex", 0));
        visitTown();
    }

    // Pirate uses their weapon in a battle
    boolean useWeapon() {
        int count = opponents.size();
        if (count > 0) {
            int opponentIndex = count - 1;
            if (count > 1) {
                opponentIndex = new Random().nextInt(count + 1);
            }
            Combatant combatant = opponents.get(opponentIndex);
            if (super.useWeapon(combatant)) {
                opponents.remove(opponentIndex);
            } else {
                return combatant.useWeapon(this);
            }
        }
        return false;
    }

    // Visit the next town on the Pirate's journey
    boolean visitTown() {
        List<Town> levelTowns = PirateGame.getTowns(value("level"));
        if (levelTowns == null) return true;
        Town town = levelTowns.get(value("townIndex"));
        if (town != null) {
            townsVisited.add(town);
            loot = town.loot();
            opponents = town.opponents();
            features = town.features();
            return false;
        }
        return true;
    }

    // Check if the Pirate has town features to experience
    boolean hasExperiences() {
        return (features != null && !features.isEmpty());
    }

    // Check if the Pirate has opponents to battle
    boolean hasOpponents() {
        return (opponents != null && !opponents.isEmpty());
    }

    // Find loot during the Pirate's journey and update the score
    boolean findLoot() {
        if (!loot.isEmpty()) {
            Loot item = loot.remove(0);
            System.out.println("Found " + item + "!");
            adjustValue("score", item.getValue());
            System.out.println(name() + "'s score is now " + this.value("score"));
            if (loot.isEmpty()) {
                return visitNextTown();
            }
        }
        return false;
    }

    // Experience town features during the Pirate's journey and update the health
    boolean experienceFeature() {
        if (!features.isEmpty()) {
            Feature item = features.remove(0);
            System.out.println("You found a " + item.name());
            adjustHealth(item.getHealthPoints());
            System.out.println(name() + "'s health is now " + value("health"));
        }
        return (value("health") <= 0);
    }

    // Get information about the Pirate's journey and the towns visited
    public String information() {
        var current = ((LinkedList<Town>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).name());
        return "----> " + current + "\n" + super.information() + "\n\ttownsVisited = " + Arrays.toString(simpleNames);
    }

    // Visit the next town in the Pirate's journey and update the level and score
    private boolean visitNextTown() {
        int townIndex = value("townIndex");
        List<Town> towns = PirateGame.getTowns(value("level"));
        if (towns == null) {
            return true;
        }
        if (townIndex >= (towns.size() - 1)) {
            System.out.println("Levelling up! Bonus: 500 points!");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValue("townIndex", 0);
        } else {
            System.out.println("Sailing to the next town! Bonus: 50 points!");
            adjustValue("score", 50);
            adjustValue("townIndex", 1);
        }
        return visitTown();
    }
}
