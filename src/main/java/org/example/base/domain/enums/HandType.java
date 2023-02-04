package org.example.base.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HandType {
    ROYAL_FLUSH("Royal Flush", 10),
    STRAIGHT_FLUSH("Straight Flush", 9),
    FOUR_KIND("Four of a Kind", 8),
    FULL_HOUSE("Full House", 7),
    FLUSH("Flush", 6),
    STRAIGHT("Straight", 5),
    THREE_KIND("Three of a Kind", 4),
    TWO_PAIR("Two Pair", 3),
    PAIR("Pair", 2),
    HIGH_CARD("High Card", 1);

    @Getter
    final String name;

    @Getter
    final Integer rank;

    @Override
    public final String toString() {
        return name;
    }

}
