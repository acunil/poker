package org.example.base.domain.Helpers;

import java.util.*;
import java.util.stream.Stream;

import lombok.val;
import org.example.base.domain.Components.CardInput;
import org.example.base.domain.Components.Hand;
import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.CardType;
import org.example.base.domain.Enums.HandType;
import lombok.NonNull;

public class IdentifyHandUtil {

    public static Hand getHighCardHand(List<Card> input) {
        verifyInput(input);
        return new Hand(HandType.HIGH_CARD, GeneralUtil.getHighestCards(input, 5));
    }

    public static Hand getPairHand(@NonNull List<Card> input) {
        verifyInput(input);
        ArrayList<Card> singles = new ArrayList<>();
        ArrayList<Card> duplicates = new ArrayList<>();
        input.forEach(card -> {
            if (singles.stream().anyMatch(single -> single.getValue().equals(card.getValue()))) {
                duplicates.add(card);
            } else {
                singles.add(card);
            }
        });
        if (duplicates.size() != 1) {
            return null;
        }

        CardType pairType = duplicates.get(0).getCardType();
        List<Card> unpairedCards = input.stream().filter(card -> card.getCardType() != pairType).toList();
        List<Card> pairedCards = input.stream().filter(card -> !unpairedCards.contains(card)).sorted().toList();
        ArrayList<Card> result = new ArrayList<>();
        result.addAll(pairedCards);
        result.addAll(GeneralUtil.getHighestCards(unpairedCards, 3));
        return new Hand(HandType.PAIR, result);
    }

    public static Hand getTwoPairHand(@NonNull List<Card> input) {
        verifyInput(input);
        CardInput cardInput = new CardInput(input);
        List<List<Card>> pairs = cardInput.getPairs();
        if (pairs.size() != 2 || !cardInput.getThreeOfAKinds().isEmpty()) {
            return null;
        } else {
            val allPairs = new ArrayList<>(pairs.stream().flatMap(Collection::stream).toList());
            allPairs.sort(Comparator.comparingInt(Card::getValue));
            Collections.reverse(allPairs);
            val singleCards = input.stream().filter(card -> !allPairs.contains(card)).toList();
            val highCard = GeneralUtil.getHighestCards(singleCards, 1);
            val result = Stream.concat(allPairs.stream(), highCard.stream()).toList();
            return new Hand(HandType.TWO_PAIR, result);
        }
    }

    protected static void verifyInput(@NonNull List<Card> input) {
        if (input.size() != GeneralUtil.HOLDEM_INPUT_SIZE) {
            throw new IllegalArgumentException("Cards input size must be " + GeneralUtil.HOLDEM_INPUT_SIZE);
        }
        if (new HashSet<>(input).size() != 7) {
            throw new IllegalArgumentException("Cards input must all be unique: " + GeneralUtil.getLabelFromCards(input));
        }
    }

}
