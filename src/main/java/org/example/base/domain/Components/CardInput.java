package org.example.base.domain.Components;

import java.util.List;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.CardType;
import org.example.base.domain.Enums.Suit;

@RequiredArgsConstructor
public class CardInput {

    private final List<Card> input;

    private List<Card> getTwos() {
        return input.stream().filter(card -> CardType.TWO.equals(card.getCardType())).toList();
    }

    private List<Card> getThrees() {
        return input.stream().filter(card -> CardType.THREE.equals(card.getCardType())).toList();
    }

    private List<Card> getFours() {
        return input.stream().filter(card -> CardType.FOUR.equals(card.getCardType())).toList();
    }

    private List<Card> getFives() {
        return input.stream().filter(card -> CardType.FIVE.equals(card.getCardType())).toList();
    }

    private List<Card> getSixes() {
        return input.stream().filter(card -> CardType.SIX.equals(card.getCardType())).toList();
    }

    private List<Card> getSevens() {
        return input.stream().filter(card -> CardType.SEVEN.equals(card.getCardType())).toList();
    }

    private List<Card> getEights() {
        return input.stream().filter(card -> CardType.EIGHT.equals(card.getCardType())).toList();
    }

    private List<Card> getNines() {
        return input.stream().filter(card -> CardType.NINE.equals(card.getCardType())).toList();
    }

    private List<Card> getTens() {
        return input.stream().filter(card -> CardType.TEN.equals(card.getCardType())).toList();
    }

    private List<Card> getJacks() {
        return input.stream().filter(card -> CardType.JACK.equals(card.getCardType())).toList();
    }

    private List<Card> getQueens() {
        return input.stream().filter(card -> CardType.QUEEN.equals(card.getCardType())).toList();
    }

    private List<Card> getKings() {
        return input.stream().filter(card -> CardType.KING.equals(card.getCardType())).toList();
    }

    private List<Card> getAces() {
        return input.stream().filter(card -> CardType.ACE.equals(card.getCardType())).toList();
    }

    private List<Card> getSpades() {
        return input.stream().filter(card -> Suit.SPADES.equals(card.getSuit())).toList();
    }

    private List<Card> getClubs() {
        return input.stream().filter(card -> Suit.CLUBS.equals(card.getSuit())).toList();
    }

    private List<Card> getHearts() {
        return input.stream().filter(card -> Suit.HEARTS.equals(card.getSuit())).toList();
    }

    private List<Card> getDiamonds() {
        return input.stream().filter(card -> Suit.DIAMONDS.equals(card.getSuit())).toList();
    }

    public List<List<Card>> getFilteredValues() {
        return Stream.of(getTwos(), getThrees(), getFours(), getFives(), getSixes(), getSevens(),
                        getEights(), getNines(), getTens(), getJacks(), getQueens(), getKings(), getAces())
                .filter(cards -> !cards.isEmpty())
                .toList();
    }


    public List<List<Card>> getFilteredSuits() {
        return Stream.of(getSpades(), getClubs(), getHearts(), getDiamonds())
                .filter(cards -> !cards.isEmpty())
                .toList();
    }

    public List<List<Card>> getPairs() {
        return getFilteredValues().stream().filter(cards -> cards.size() == 2).toList();
    }

    public List<List<Card>> getThreeOfAKinds() {
        return getFilteredValues().stream().filter(cards -> cards.size() == 3).toList();
    }

    public List<List<Card>> getFourOfAKinds() {
        return getFilteredValues().stream().filter(cards -> cards.size() == 4).toList();
    }

}
