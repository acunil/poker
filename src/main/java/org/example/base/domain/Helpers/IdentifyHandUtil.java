package org.example.base.domain.Helpers;

import static org.example.base.domain.Enums.HandType.FOUR_KIND;
import static org.example.base.domain.Enums.HandType.PAIR;
import static org.example.base.domain.Enums.HandType.THREE_KIND;
import static org.example.base.domain.Enums.HandType.TWO_PAIR;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import lombok.val;
import org.example.base.domain.Components.CardInput;
import org.example.base.domain.Components.Hand;
import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.HandType;
import lombok.NonNull;

public class IdentifyHandUtil {

    public static Hand getHighCardHand(List<Card> input) {
        verifyInput(input);
        return new Hand(HandType.HIGH_CARD, GeneralUtil.getHighestCards(input, 5));
    }

    public static Hand getPairHand(@NonNull List<Card> input) {
        verifyInput(input);
        CardInput cardInput = new CardInput(input);
        List<List<Card>> matches = cardInput.getPairs();
        if (matches.size() != 1) {
            return null;
        } else {
            return getHand(PAIR, input, matches);
        }
    }

    public static Hand getTwoPairHand(@NonNull List<Card> input) {
        verifyInput(input);
        CardInput cardInput = new CardInput(input);
        List<List<Card>> matches = cardInput.getPairs();
        if (matches.size() < 2) {
            return null;
        } else if (matches.size() == 3) {
            return getHand(TWO_PAIR, input, List.of(getCardsSorted(matches).subList(0, 4)));
        } else {
            return getHand(TWO_PAIR, input, matches);
        }
    }

    public static Hand getThreeOfAKindHand(@NonNull List<Card> input) {
        verifyInput(input);
        CardInput cardInput = new CardInput(input);
        List<List<Card>> threeKinds = cardInput.getThreeOfAKinds();
        if (threeKinds.size() != 1) {
            return null;
        } else {
            return getHand(THREE_KIND, input, threeKinds);
        }
    }

    public static Hand getFourOfAKindHand(@NonNull List<Card> input) {
        verifyInput(input);
        CardInput cardInput = new CardInput(input);
        List<List<Card>> fourKinds = cardInput.getFourOfAKinds();
        if (fourKinds.isEmpty()) {
            return null;
        } else {
            return getHand(FOUR_KIND, input, fourKinds);
        }
    }

    private static Hand getHand(HandType handType, List<Card> input, List<List<Card>> matches) {
        ArrayList<Card> allMatches = getCardsSorted(matches);
        val singleCards = input.stream().filter(card -> !allMatches.contains(card)).toList();
        val highestCards = GeneralUtil.getHighestCards(singleCards, 5 - allMatches.size());
        val result = Stream.concat(allMatches.stream(), highestCards.stream()).toList();
        return new Hand(handType, result);
    }

    private static ArrayList<Card> getCardsSorted(List<List<Card>> matches) {
        ArrayList<Card> allMatches = new ArrayList<>(matches.stream().flatMap(Collection::stream).toList());
        allMatches.sort(GeneralUtil.orderByValueDescThenSuit);
        return allMatches;
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
