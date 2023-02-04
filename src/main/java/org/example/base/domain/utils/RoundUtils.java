package org.example.base.domain.utils;

import org.example.base.domain.components.Hand;
import org.example.base.domain.components.Player;
import org.example.base.domain.enums.Card;

import static org.example.base.domain.utils.GeneralUtils.getLabelFromCards;

import java.util.List;

public class RoundUtils {

    private RoundUtils() {
        throw new IllegalStateException("RoundUtils is a static class");
    }

    public static List<Hand> getPlayerHands(List<Player> players, List<Card> communityCards) {
        return players.stream().map(player -> player.getBestHand(communityCards)).toList();
    }

    public static List<Player> getWinningPlayers(List<Player> players, List<Card> communityCards) {
        List<Hand> activeHands = RoundUtils.getPlayerHands(players, communityCards);
        List<Hand> winningHands = CompareHandsUtils.getWinningHands(activeHands);
        List<String> winningHandsCardsLabels = winningHands.stream()
            .map(hand -> getLabelFromCards(hand.getCards()))
            .toList();
        return players.stream()
            .filter(player -> {
                String labelFromPlayerCards = getLabelFromCards(player.getBestHand(communityCards).getCards());
                return winningHandsCardsLabels.contains(labelFromPlayerCards);
            })
            .toList();
    }
}
