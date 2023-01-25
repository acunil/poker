package org.example.base.domain.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HandType {
    ROYAL_FLUSH("Royal flush", 10),
    STRAIGHT_FLUSH("Straight flush", 9),
    FOUR_KIND("Four of a kind", 8),
    FULL_HOUSE("Full house", 7),
    FLUSH("Flush", 6),
    STRAIGHT("Straight", 5),
    THREE_KIND("Three of a kind", 4),
    TWO_PAIR("Two pair", 3),
    PAIR("Pair", 2),
    HIGH_CARD("High card", 1);

    @Getter
    final String name;

    @Getter
    final Integer rank;

}
