package Helpers;

import Enums.Card;
import Enums.CardType;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralUtil {
    public static final int HOLDEM_INPUT_SIZE = 7;

    public static Integer getHighestCardTypeValue(List<CardType> input) {
        return input.stream().max(Comparator.comparingInt(CardType::getValue)).get().getValue();
    }

    public static Integer getHighestCardValue(List<Card> input) {
        return input.stream().max(Comparator.comparingInt(Card::getValue)).get().getValue();
    }

    public static String getLabels(List<Card> input) {
        return StringUtils.join(input.stream().map(Card::getLabel).collect(Collectors.toList()), ",");
    }

    public static List<Card> orderCardsByValue(List<Card> input) {
        return input.stream().sorted(Card::compare).sorted(Collections.reverseOrder()).toList();
    }

    public static List<Card> getCardsFromLabel(String label) {
        List<String> labels = Arrays.stream(label.split(",")).toList();
        return labels.stream().map(Card::fromLabel).collect(Collectors.toList());
    }

}
