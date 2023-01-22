package org.example.base.domain.Components;

import static org.example.base.domain.Enums.CardType.*;
import static org.example.base.domain.Enums.Suit.*;

import java.util.Comparator;
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
        return getMatches(TWO);
    }

    private List<Card> getThrees() {
        return getMatches(THREE);
    }

    private List<Card> getFours() {
        return getMatches(FOUR);
    }

    private List<Card> getFives() {
        return getMatches(FIVE);
    }

    private List<Card> getSixes() {
        return getMatches(SIX);
    }

    private List<Card> getSevens() {
        return getMatches(SEVEN);
    }

    private List<Card> getEights() {
        return getMatches(EIGHT);
    }

    private List<Card> getNines() {
        return getMatches(NINE);
    }

    private List<Card> getTens() {
        return getMatches(TEN);
    }

    private List<Card> getJacks() {
        return getMatches(JACK);
    }

    private List<Card> getQueens() {
        return getMatches(QUEEN);
    }

    private List<Card> getKings() {
        return getMatches(KING);
    }

    private List<Card> getAces() {
        return getMatches(ACE);
    }

    private List<Card> getSpades() {
        return getMatches(SPADES);
    }

    private List<Card> getClubs() {
        return getMatches(CLUBS);
    }

    private List<Card> getHearts() {
        return getMatches(HEARTS);
    }

    private List<Card> getDiamonds() {
        return getMatches(DIAMONDS);
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

    private List<Card> getMatches(CardType cardType) {
        return input.stream().filter(card -> cardType.equals(card.getCardType()))
                .sorted(Comparator.comparing(Card::getSuit))
                .toList();
    }

    private List<Card> getMatches(Suit suit) {
        return input.stream().filter(card -> suit.equals(card.getSuit()))
                .sorted(Comparator.comparing(Card::getValue))
                .toList();
    }

}
