package dev.hartcode.game;

import java.util.function.Predicate;

// Represents a game action
public record GameAction(char key, String prompt, Predicate<Integer> action) {
}
