package org.example.base.domain.Helpers;

import org.example.base.domain.Components.Hand;

import java.util.ArrayList;
import java.util.List;

public class CompareHandsUtil {

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
