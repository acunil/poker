package org.example.base.domain.enums;

import java.util.Objects;

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

    public String getLabel() {
        return String.valueOf(name.charAt(0));
    }

    public static Suit fromLabel(String label) {
        for (Suit suit : Suit.values()) {
            if (Objects.equals(suit.getLabel(), label)) {
                return suit;
            }
        }
        return null;
    }
}
