package Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum HandType {
    ROYAL_FLUSH("Royal flush", 1),
    STRAIGHT_FLUSH("Straight flush", 2),
    FOUR_KIND("Four of a kind", 3),
    FULL_HOUSE("Full house", 4),
    FLUSH("Flush", 5),
    STRAIGHT("Straight", 6),
    THREE_KIND("Three of a kind", 7),
    TWO_PAIR("Two pair", 8),
    PAIR("Pair", 9),
    HIGH_CARD("High card", 10);

    @Getter
    final String name;

    @Getter
    final Integer rank;

}
