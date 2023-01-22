package org.example.base.domain.Helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

    protected static void verifyInput(@NonNull List<Card> input) {
        if (input.size() != GeneralUtil.HOLDEM_INPUT_SIZE) {
            throw new IllegalArgumentException("Cards input size must be " + GeneralUtil.HOLDEM_INPUT_SIZE);
        }
        if (new HashSet<>(input).size() != 7) {
            throw new IllegalArgumentException("Cards input must all be unique: " + GeneralUtil.getLabelFromCards(input));
        }
    }

}
