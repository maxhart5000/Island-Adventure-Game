package dev.hartcode.game;

import java.util.Scanner;

// Game console to manage game interactions
public final class GameConsole<T extends Game<? extends Player>> {
    private final T game;
    private static final Scanner myScanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }

    // Add a player to the game
    public int addPlayer() {
        // Prompt the user for their name
        System.out.println("Enter your name: ");
        String name = myScanner.nextLine();

        // Welcome the player to the game
        System.out.printf("Welcome to %s, %s!%n%n", game.getGameName(), name);
        return game.addPlayers(name);
    }

    // Start playing the game
    public void playGame(int playerIndex) {
        boolean done = false;
        while (!done) {
            // Get available game actions for the player
            var gameActions = game.getGameActions(playerIndex);

            // Display the available actions to the player
            System.out.println("Select from one of the following Actions: ");
            for (Character c : gameActions.keySet()) {
                String prompt = gameActions.get(c).prompt();
                System.out.println("\t" + prompt + "(" + c + ")");
            }

            // Prompt the player to enter their next action
            System.out.println("Enter Next Action: ");
            char nextMove = myScanner.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = gameActions.get(nextMove);

            if (gameAction != null) {
                System.out.println("-".repeat(40));
                // Execute the selected game action
                done = game.executeGameAction(playerIndex, gameAction);
                if (!done) {
                    System.out.println("-".repeat(40));
                }
            }
        }
    }
}
