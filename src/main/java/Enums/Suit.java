package Enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Suit {
    SPADES("Spades"),
    CLUBS("Clubs"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds");

    final String name;

    @Override
    public String toString() {
        return name;
    }

    public char getLabel() {
        return name.charAt(0);
    }
}
