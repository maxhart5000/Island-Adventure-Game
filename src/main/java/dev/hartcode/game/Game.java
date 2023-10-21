package dev.hartcode.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Abstract class representing a game
public abstract class Game<T extends Player> {
    private final String gameName;
    private final List<T> players = new ArrayList<>();
    private Map<Character, GameAction> standardActions = null;

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    // Get standard game actions available to all players
    public Map<Character, GameAction> getStandardActions() {
        if (standardActions == null) {
            standardActions = new LinkedHashMap<>(Map.of(
                    'I', new GameAction('I', "Print Player Info", this::printPlayer),
                    'Q', new GameAction('G', "Quit Game", this::quitGame)
            ));
        }
        return standardActions;
    }

    // Create a new player with a given name
    public abstract T createNewPlayer(String name);

    // Get game-specific actions available to a player
    public abstract Map<Character, GameAction> getGameActions(int playerIndex);

    // Add a player to the game
    final int addPlayers(String name) {
        T player = createNewPlayer(name);
        if (player != null) {
            players.add(player);
            return players.size() - 1;
        }
        return -1;
    }

    // Get a player by their index
    protected final T getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    // Execute a game action for a player
    public boolean executeGameAction(int player, GameAction action) {
        return action.action().test(player);
    }

    // Action to print player information
    public boolean printPlayer(int playerIndex) {
        Player player = players.get(playerIndex);
        System.out.println(player);
        return false;
    }

    // Action to quit the game
    public boolean quitGame(int playerIndex) {
        Player player = players.get(playerIndex);
        System.out.println("Sorry to see you go, " + player.name());
        return true;
    }
}
