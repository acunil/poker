package org.example.base.domain.Enums;

import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CardType {
    TWO("Two",2, "2"),
    THREE("Three",3, "3"),
    FOUR("Four",4, "4"),
    FIVE("Five", 5, "5"),
    SIX("Six", 6, "6"),
    SEVEN("Seven", 7, "7"),
    EIGHT("Eight", 8, "8"),
    NINE("Nine", 9, "9"),
    TEN("Ten", 10, "T"),
    JACK("Jack", 11, "J"),
    QUEEN("Queen", 12, "Q"),
    KING("King", 13, "K"),
    ACE("Ace",14, "A");

    @Getter
    public final String name;

    @Getter
    public final Integer value;

    @Getter
    public final String label;

    @Override
    public String toString() {
        return name;
    }

    public static CardType fromLabel(String label) {
        for (CardType cardType : CardType.values()) {
            if (Objects.equals(cardType.getLabel(), label)) {
                return cardType;
            }
        }
        return null;
    }

    public Integer compare(CardType target) {
        return value.compareTo(target.getValue());
    }
}
