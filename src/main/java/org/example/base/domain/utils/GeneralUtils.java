package org.example.base.domain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.CardType;
import lombok.val;
import org.apache.commons.lang3.StringUtils;

public class GeneralUtils {

    private GeneralUtils() {
        throw new IllegalStateException("GeneralUtils is a static class");
    }

    public static final int HOLDEM_INPUT_SIZE = 7;

    public static Integer getHighestCardTypeValue(List<CardType> input) {
        Optional<CardType> highestCardType = input.stream().max(Comparator.comparingInt(CardType::getValue));
        assert (highestCardType.isPresent());
        return highestCardType.get().getValue();
    }

    public static Integer getHighestCardValue(List<Card> input) {
        Optional<Card> highestCard = input.stream().max(Comparator.comparingInt(Card::getValue));
        assert (highestCard.isPresent());
        return highestCard.get().getValue();
    }

    public static String getLabelFromCards(List<Card> input) {
        return StringUtils.join(input.stream().map(Card::getLabel).toList(), ",");
    }

    protected static List<Card> orderCardsByValue(List<Card> input) {
        val arrayList = new ArrayList<>(input);
        arrayList.sort(ORDER_BY_VALUE_DESC_THEN_SUIT);
        return arrayList;
    }

    public static List<Card> getCardsFromLabel(String label) {
        List<String> labels = Arrays.stream(label.split(",")).toList();
        return labels.stream().map(Card::fromLabel).toList();
    }

    public static List<Card> getHighestCards(List<Card> cards, int count) {
        return orderCardsByValue(cards).subList(0, count);
    }

    public static final Comparator<Card> ORDER_BY_VALUE_DESC_THEN_SUIT = Comparator.comparing(Card::getValue)
            .reversed()
            .thenComparing(Card::getDeckIndex);

}
