package dev.hartcode.game.pirateGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public record Town(String name, String island, int level, List<Loot> loot, List<Feature> features, List<Combatant> opponents) {
    public Town {
        // Generate loot for the town based on the town's level
        loot = randomReduced(new ArrayList<>(EnumSet.allOf(Loot.class)), level + 2);

        // Generate town features based on the town's level
        features = randomReduced(new ArrayList<>(EnumSet.allOf(Feature.class)), level + 3);

        opponents = new ArrayList<>();

        // Add opponents based on the town's level
        if (level == 0) {
            opponents.add(new Islander("Captain Jack Sparrow", Weapon.KNIFE));
        } else {
            opponents.add(new Islander("Captain Hook", Weapon.MACHETE));
            opponents.add(new Soldier("Jason Bourne", Weapon.PISTOL));
        }
    }

    public Town(String name, String island, int level) {
        this(name, island, level, null, null, null);
    }

    private <T> List<T> randomReduced(List<T> list, int size) {
        // Shuffle the list and select a sublist of the specified size
        Collections.shuffle(list);
        return list.subList(0, size);
    }

    @Override
    public String toString() {
        return name + ", " + island;
    }

    public String information() {
        return "Town: " + this + "\n\tLoot = " + loot +
                "\n\tFeatures = " + features +
                "\n\tOpponents = " + opponents;
    }

    public List<Loot> loot() {
        // Return a copy of the loot list
        return (loot == null) ? null : new ArrayList<>(loot);
    }

    public List<Feature> features() {
        // Return a copy of the features list
        return (features == null) ? null : new ArrayList<>(features);
    }

    public List<Combatant> opponents() {
        // Return a copy of the opponents list
        return (opponents == null) ? null : new ArrayList<>(opponents);
    }
}
