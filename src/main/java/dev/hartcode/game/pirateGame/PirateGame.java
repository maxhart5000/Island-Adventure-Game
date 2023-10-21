package dev.hartcode.game.pirateGame;

import dev.hartcode.game.Game;
import dev.hartcode.game.GameAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// PirateGame is a specific game type that extends the Game class
public class PirateGame extends Game<Pirate> {

    private static final List<List<Town>> levelMap;

    // Static block for loading the game data
    static {
        levelMap = new ArrayList<>();
        System.out.println("Loading Data...");
        loadData();
        if (levelMap.isEmpty()) {
            throw new RuntimeException("Could not load data, try again later...");
        }
        System.out.println("Data loaded successfully");
    }

    // Constructor for PirateGame
    public PirateGame(String gameName) {
        super(gameName);
    }

    // Create a new Pirate player with the given name
    @Override
    public Pirate createNewPlayer(String name) {
        return new Pirate(name);
    }

    // Define the available game actions for a Pirate player
    @Override
    public Map<Character, GameAction> getGameActions(int playerIndex) {
        Pirate pirate = getPlayer(playerIndex);
        System.out.println(pirate);
        List<Weapon> weapons = Weapon.getWeaponsByLevel(pirate.value("level"));

        Map<Character, GameAction> map = new LinkedHashMap<>();

        // Add weapon use actions if there are opponents
        if (pirate.hasOpponents()) {
            for (Weapon weapon : weapons) {
                char init = weapon.name().charAt(0);
                map.put(init, new GameAction(init, "Use the " + weapon + " ", this::useWeapon));
            }
        }

        // Add a 'Find Loot' action
        map.put('F', new GameAction('F', "Find Loot", this::findLoot));

        // Add an 'Experience Town Feature' action if there are town features to experience
        if (pirate.hasExperiences()) {
            map.put('X', new GameAction('X', "Experience Town Feature", this::experienceFeature));
        }

        // Include standard game actions
        map.putAll(getStandardActions());
        return map;
    }

    // Load the game data, including towns and their details
    private static void loadData() {
        // Level 1 Towns
        levelMap.add(new ArrayList<>(List.of(
                new Town("Isle Of Man", "UK", 0),
                new Town("Isle Of White", "UK", 0),
                new Town("Shetland Islands", "UK", 0)
        )));

        // Level 2 Towns
        levelMap.add(new ArrayList<>(List.of(
                new Town("Denpasar", "Bali", 1),
                new Town("Ubud", "Bali", 1),
                new Town("Bueleng", "Bali", 1)
        )));
    }

    // Get a list of towns based on the player's level
    public static List<Town> getTowns(int level) {
        if (level <= levelMap.size() - 1) {
            return levelMap.get(level);
        }
        return null;
    }

    // Helper method to handle weapon usage for a player
    private boolean useWeapon(int playerIndex) {
        return getPlayer(playerIndex).useWeapon();
    }

    // Execute a game action for a player (e.g., changing the current weapon)
    @Override
    public boolean executeGameAction(int player, GameAction action) {
        getPlayer(player).setCurrentWeapon(Weapon.getWeaponByChar(action.key()));
        return super.executeGameAction(player, action);
    }

    // Print information about a player's journey
    @Override
    public boolean printPlayer(int playerIndex) {
        System.out.println(getPlayer(playerIndex).information());
        return false;
    }

    // Handle the 'Find Loot' action for a player
    private boolean findLoot(int playerIndex) {
        return getPlayer(playerIndex).findLoot();
    }

    // Handle the 'Experience Town Feature' action for a player
    private boolean experienceFeature(int playerIndex) {
        return getPlayer(playerIndex).experienceFeature();
    }
}
