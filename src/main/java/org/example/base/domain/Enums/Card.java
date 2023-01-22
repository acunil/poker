package org.example.base.domain.Enums;

import static org.example.base.domain.Enums.CardType.*;
import static org.example.base.domain.Enums.Suit.*;

import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Card {
    ACE_SPADES(1, ACE, SPADES),
    TWO_SPADES(2, TWO, SPADES),
    THREE_SPADES(3, THREE, SPADES),
    FOUR_SPADES(4, FOUR, SPADES),
    FIVE_SPADES(5, FIVE, SPADES),
    SIX_SPADES(6, SIX, SPADES),
    SEVEN_SPADES(7, SEVEN, SPADES),
    EIGHT_SPADES(8, EIGHT, SPADES),
    NINE_SPADES(9, NINE, SPADES),
    TEN_SPADES(10, TEN, SPADES),
    JACK_SPADES(11, JACK, SPADES),
    QUEEN_SPADES(12, QUEEN, SPADES),
    KING_SPADES(13, KING, SPADES),
    ACE_CLUBS(14, ACE, CLUBS),
    TWO_CLUBS(15, TWO, CLUBS),
    THREE_CLUBS(16, THREE, CLUBS),
    FOUR_CLUBS(17, FOUR, CLUBS),
    FIVE_CLUBS(18, FIVE, CLUBS),
    SIX_CLUBS(19, SIX, CLUBS),
    SEVEN_CLUBS(20, SEVEN, CLUBS),
    EIGHT_CLUBS(21, EIGHT, CLUBS),
    NINE_CLUBS(22, NINE, CLUBS),
    TEN_CLUBS(23, TEN, CLUBS),
    JACK_CLUBS(24, JACK, CLUBS),
    QUEEN_CLUBS(25, QUEEN, CLUBS),
    KING_CLUBS(26, KING, CLUBS),
    ACE_HEARTS(27, ACE, HEARTS),
    TWO_HEARTS(28, TWO, HEARTS),
    THREE_HEARTS(29, THREE, HEARTS),
    FOUR_HEARTS(30, FOUR, HEARTS),
    FIVE_HEARTS(31, FIVE, HEARTS),
    SIX_HEARTS(32, SIX, HEARTS),
    SEVEN_HEARTS(33, SEVEN, HEARTS),
    EIGHT_HEARTS(34, EIGHT, HEARTS),
    NINE_HEARTS(35, NINE, HEARTS),
    TEN_HEARTS(36, TEN, HEARTS),
    JACK_HEARTS(37, JACK, HEARTS),
    QUEEN_HEARTS(38, QUEEN, HEARTS),
    KING_HEARTS(39, KING, HEARTS),
    ACE_DIAMONDS(40, ACE, DIAMONDS),
    TWO_DIAMONDS(41, TWO, DIAMONDS),
    THREE_DIAMONDS(42, THREE, DIAMONDS),
    FOUR_DIAMONDS(43, FOUR, DIAMONDS),
    FIVE_DIAMONDS(44, FIVE, DIAMONDS),
    SIX_DIAMONDS(45, SIX, DIAMONDS),
    SEVEN_DIAMONDS(46, SEVEN, DIAMONDS),
    EIGHT_DIAMONDS(47, EIGHT, DIAMONDS),
    NINE_DIAMONDS(48, NINE, DIAMONDS),
    TEN_DIAMONDS(49, TEN, DIAMONDS),
    JACK_DIAMONDS(50, JACK, DIAMONDS),
    QUEEN_DIAMONDS(51, QUEEN, DIAMONDS),
    KING_DIAMONDS(52, KING, DIAMONDS);

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
