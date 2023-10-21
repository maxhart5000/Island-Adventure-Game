package dev.hartcode;

import dev.hartcode.game.GameConsole;
import dev.hartcode.game.pirateGame.PirateGame;

public class GameController {
    public static void main(String[] args) {
        // Initialize a GameConsole with a PirateGame
        var console = new GameConsole<>(new PirateGame("The Pirate Game"));

        // Add a player and play the game
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}
