package Helpers;

import Enums.Card;
import lombok.val;

import java.util.HashSet;
import java.util.List;

public class IdentifyHandUtil {

    public static List<Card> getHighCardHand(List<Card> input) {
        verifyHand(input);
        val x = input.stream().sorted();
        return null; // TODO
    }

    public static List<Card> getPairHand(List<Card> input) {
        return null; // TODO
    }

    protected static void verifyHand(List<Card> input) {
        if (input == null) {
            throw new IllegalArgumentException("Cards input must not be null");
        }
        if (input.size() != GeneralUtil.HOLDEM_INPUT_SIZE) {
            throw new IllegalArgumentException("Cards input size must be " + GeneralUtil.HOLDEM_INPUT_SIZE);
        }
        if (new HashSet<>(input).size() != 7) {
            throw new IllegalArgumentException("Cards input must all be unique: " + GeneralUtil.getLabels(input));
        }
    }

}
