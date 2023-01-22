package org.example.base.domain.Enums;

import static org.example.base.domain.Enums.CardType.*;
import static org.example.base.domain.Enums.Suit.*;

import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Card {
    TWO_SPADES(1, TWO, SPADES),
    THREE_SPADES(2, THREE, SPADES),
    FOUR_SPADES(3, FOUR, SPADES),
    FIVE_SPADES(4, FIVE, SPADES),
    SIX_SPADES(5, SIX, SPADES),
    SEVEN_SPADES(6, SEVEN, SPADES),
    EIGHT_SPADES(7, EIGHT, SPADES),
    NINE_SPADES(8, NINE, SPADES),
    TEN_SPADES(9, TEN, SPADES),
    JACK_SPADES(10, JACK, SPADES),
    QUEEN_SPADES(11, QUEEN, SPADES),
    KING_SPADES(12, KING, SPADES),
    ACE_SPADES(13, ACE, SPADES),
    TWO_CLUBS(14, TWO, CLUBS),
    THREE_CLUBS(15, THREE, CLUBS),
    FOUR_CLUBS(16, FOUR, CLUBS),
    FIVE_CLUBS(17, FIVE, CLUBS),
    SIX_CLUBS(18, SIX, CLUBS),
    SEVEN_CLUBS(19, SEVEN, CLUBS),
    EIGHT_CLUBS(20, EIGHT, CLUBS),
    NINE_CLUBS(21, NINE, CLUBS),
    TEN_CLUBS(22, TEN, CLUBS),
    JACK_CLUBS(23, JACK, CLUBS),
    QUEEN_CLUBS(24, QUEEN, CLUBS),
    KING_CLUBS(25, KING, CLUBS),
    ACE_CLUBS(26, ACE, CLUBS),
    TWO_HEARTS(27, TWO, HEARTS),
    THREE_HEARTS(28, THREE, HEARTS),
    FOUR_HEARTS(29, FOUR, HEARTS),
    FIVE_HEARTS(30, FIVE, HEARTS),
    SIX_HEARTS(31, SIX, HEARTS),
    SEVEN_HEARTS(32, SEVEN, HEARTS),
    EIGHT_HEARTS(33, EIGHT, HEARTS),
    NINE_HEARTS(34, NINE, HEARTS),
    TEN_HEARTS(35, TEN, HEARTS),
    JACK_HEARTS(36, JACK, HEARTS),
    QUEEN_HEARTS(37, QUEEN, HEARTS),
    KING_HEARTS(38, KING, HEARTS),
    ACE_HEARTS(39, ACE, HEARTS),
    TWO_DIAMONDS(40, TWO, DIAMONDS),
    THREE_DIAMONDS(41, THREE, DIAMONDS),
    FOUR_DIAMONDS(42, FOUR, DIAMONDS),
    FIVE_DIAMONDS(43, FIVE, DIAMONDS),
    SIX_DIAMONDS(44, SIX, DIAMONDS),
    SEVEN_DIAMONDS(45, SEVEN, DIAMONDS),
    EIGHT_DIAMONDS(46, EIGHT, DIAMONDS),
    NINE_DIAMONDS(47, NINE, DIAMONDS),
    TEN_DIAMONDS(48, TEN, DIAMONDS),
    JACK_DIAMONDS(39, JACK, DIAMONDS),
    QUEEN_DIAMONDS(50, QUEEN, DIAMONDS),
    KING_DIAMONDS(51, KING, DIAMONDS),
    ACE_DIAMONDS(52, ACE, DIAMONDS);

    @Getter
    final Integer deckIndex;

    @Getter
    final CardType cardType;

    @Getter
    final Suit suit;

    @Override
    public String toString() {
        return getCardName();
    }
    
    public String getCardName() {
        return cardType + " of " + suit;
    }
    
    public String getLabel() {
        return cardType.getLabel() + suit.getLabel();
    }
    
    public Integer getValue() {
        return cardType.getValue();
    }

    public Integer compare(Card target) {
        return this.getValue().compareTo(target.getValue());
    }

    public boolean isHigherThan(Card target) {
        return this.compare(target) == 1;
    }

    public boolean isLowerThan(Card target) {
        return this.compare(target) == -1;
    }

    public boolean isSameAs(Card target) {
        return this.compare(target) == 0;
    }

    public static Card fromLabel(String label) {
        for (Card card : Card.values()) {
            if (Objects.equals(card.getLabel(), label)) {
                return card;
            }
        }
        return null;
    }
    
}
