package org.example.base.domain.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.val;
import org.example.base.domain.components.CardInput;
import org.example.base.domain.components.Hand;
import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.CardType;
import org.example.base.domain.enums.HandType;
import lombok.NonNull;

import static org.example.base.domain.enums.HandType.*;
import static org.example.base.domain.utils.GeneralUtils.ORDER_BY_VALUE_DESC_THEN_SUIT;

public class IdentifyHandUtils {

    private IdentifyHandUtils() {
        throw new IllegalStateException("IdentifyHandUtils is a static class");
    }

    /* HANDS */

    public static Hand getBestHand(List<Card> input) {
        Hand bestHand = getStraightFlushHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getFourOfAKindHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getFullHouseHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getFlushHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getStraightHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getThreeOfAKindHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getTwoPairHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        bestHand = getPairHand(input);
        if (bestHand != null) {
            return bestHand;
        }
        return getHighCardHand(input);
    }

    public static Hand getBestHand(String input) {
        return getBestHand(GeneralUtils.getCardsFromLabel(input));
    }



    public static Hand getHighCardHand(List<Card> input) {
        verifyInput(input);
        return new Hand(HandType.HIGH_CARD, GeneralUtils.getHighestCards(input, 5));
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

    public static Hand getFullHouseHand(@NonNull List<Card> input) {
        verifyInput(input);
        CardInput cardInput = new CardInput(input);
        val threeKinds = cardInput.getThreeOfAKinds();
        if (threeKinds.isEmpty()) {
            return null;
        }
        val fullHouse = new ArrayList<Card>();
        val pairs = cardInput.getPairs();
        if (!pairs.isEmpty()) {
                fullHouse.addAll(threeKinds.get(0).stream().sorted(ORDER_BY_VALUE_DESC_THEN_SUIT).toList());
            if (pairs.size() == 1) {
                fullHouse.addAll(3, pairs.get(0).stream().sorted(ORDER_BY_VALUE_DESC_THEN_SUIT).toList());
            } else {
                ArrayList<Card> flattenedPairs = new ArrayList<>();
                flattenedPairs.addAll(pairs.get(0));
                flattenedPairs.addAll(pairs.get(1));
                flattenedPairs.sort(ORDER_BY_VALUE_DESC_THEN_SUIT);
                fullHouse.addAll(3, flattenedPairs.subList(0, 2));
            }
        }

        if (threeKinds.size() == 2) {
            ArrayList<Card> flattenedThreeKinds = new ArrayList<>();
            flattenedThreeKinds.addAll(threeKinds.get(0));
            flattenedThreeKinds.addAll(threeKinds.get(1));
            flattenedThreeKinds.sort(ORDER_BY_VALUE_DESC_THEN_SUIT);
            fullHouse.addAll(flattenedThreeKinds.subList(0, 5));
        }

        if (fullHouse.isEmpty()) {
            return null;
        } else {
            return new Hand(FULL_HOUSE, fullHouse);
        }
    }

    public static Hand getFlushHand(@NonNull List<Card> input) {
        verifyInput(input);
        ArrayList<Card> suited = getSuited(input);
        if (suited.isEmpty()) {
            return null;
        }
        return new Hand(FLUSH, suited.subList(0, 5));
    }

    public static Hand getStraightHand(@NonNull List<Card> input) {
        verifyInput(input);
        ArrayList<Card> straight = getStraight(input);
        if (straight.isEmpty()) {
            return null;
        }
        return new Hand(STRAIGHT, straight);
    }

    public static Hand getStraightFlushHand(@NonNull List<Card> input) {
        verifyInput(input);
        ArrayList<Card> suited = getSuited(input);
        if (suited.isEmpty()) {
            return null;
        }
        val straightFlush = getStraight(suited);
        if (straightFlush.isEmpty()) {
            return null;
        } else if (straightFlush.stream().anyMatch(card -> card.getCardType().equals(CardType.ACE)
                && straightFlush.stream().anyMatch(card1 -> card1.getCardType().equals(CardType.KING)))) {
            return new Hand(ROYAL_FLUSH, straightFlush);
        } else {
            return new Hand(STRAIGHT_FLUSH, straightFlush);
        }
    }

    /* HELPERS */

    private static ArrayList<Card> getSuited(List<Card> input) {
        CardInput cardInput = new CardInput(input);
        val flushes = cardInput.getFilteredSuits().stream()
                .filter(matches -> matches.size() >= 5)
                .toList();
        if (flushes.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<Card> suited = new ArrayList<>(flushes.get(0));
        suited.sort(ORDER_BY_VALUE_DESC_THEN_SUIT);
        return suited;
    }

    private static ArrayList<Card> getStraight(List<Card> input) {
        ArrayList<Integer> values = new ArrayList<>(input.stream()
                .map(Card::getValue)
                .collect(Collectors.toSet())
                .stream()
                .sorted(Comparator.reverseOrder())
                .toList());
        if (values.size() < 5) {
            return new ArrayList<>();
        }
        if (input.stream().anyMatch(card -> CardType.ACE.equals(card.getCardType()))) {
            values.add(1);
        }

        ArrayList<Integer> validStraightValues = new ArrayList<>();
        for (int i = 0; i < values.size() - 4; i++) {
            boolean fiveConsecutive = values.get(i) == values.get(i + 4) + 4;
            if (fiveConsecutive) {
                validStraightValues.addAll(values.subList(i, i + 5));
                break;
            }
        }
        if (validStraightValues.size() != 5) {
            return new ArrayList<>();
        }

        ArrayList<Card> straight = new ArrayList<>();
        List<Card> inputSorted = input.stream().sorted(ORDER_BY_VALUE_DESC_THEN_SUIT).toList();
        for (Integer value : validStraightValues) {
            Integer actualValue = value == 1 ? 14 : value;
            Card match = inputSorted.stream().filter(card -> card.getValue().equals(actualValue))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Unexpectedly no match found by getStraight: "
                            + GeneralUtils.getLabelFromCards(inputSorted)));
            straight.add(match);
        }
        return straight;
    }

    private static Hand getHand(HandType handType, List<Card> input, List<List<Card>> matches) {
        ArrayList<Card> allMatches = getCardsSorted(matches);
        val singleCards = input.stream().filter(card -> !allMatches.contains(card)).toList();
        val highestCards = GeneralUtils.getHighestCards(singleCards, 5 - allMatches.size());
        val result = Stream.concat(allMatches.stream(), highestCards.stream()).toList();
        return new Hand(handType, result);
    }

    private static ArrayList<Card> getCardsSorted(List<List<Card>> matches) {
        ArrayList<Card> allMatches = new ArrayList<>(matches.stream().flatMap(Collection::stream).toList());
        allMatches.sort(ORDER_BY_VALUE_DESC_THEN_SUIT);
        return allMatches;
    }

    protected static void verifyInput(@NonNull List<Card> input) {
        if (input.size() != GeneralUtils.HOLDEM_INPUT_SIZE) {
            throw new IllegalArgumentException("Cards input size must be " + GeneralUtils.HOLDEM_INPUT_SIZE);
        }
        if (new HashSet<>(input).size() != 7) {
            throw new IllegalArgumentException("Cards input must all be unique: " + GeneralUtils.getLabelFromCards(input));
        }
    }

}
