package org.example.base.domain.utils;

import org.example.base.domain.components.Hand;

import java.util.ArrayList;
import java.util.List;

public class CompareHandsUtils {

    private CompareHandsUtils() {
        throw new IllegalStateException("CompareHandsUtils is a static class");
    }

    public static List<Hand> getWinningHands(List<Hand> hands) {
        ArrayList<Hand> winningHands = new ArrayList<>();
        winningHands.add(hands.get(0));
        for (int i = 1; i < hands.size(); i++) {
            Hand currentHand = hands.get(i);
            Integer compare = currentHand.compare(winningHands.get(0));
            if (compare == 0) {
                winningHands.add(currentHand);
            } else if (compare == 1) {
                winningHands = new ArrayList<>();
                winningHands.add(currentHand);
            }
        }
        return winningHands;
    }

}
