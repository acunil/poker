package Enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CardType {
    ACE("Ace",1, "A"),
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
    KING("King", 13, "K");

    final String name;
    final Integer value;
    final String label;

    @Override
    public String toString() {
        return name;
    }
}
