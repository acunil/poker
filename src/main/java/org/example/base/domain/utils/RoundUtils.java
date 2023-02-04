package org.example.base.domain.utils;

import org.example.base.domain.components.Player;
import org.example.base.domain.components.Round;

import java.util.ArrayList;
import java.util.List;

public class RoundUtils {

    private RoundUtils() {
        throw new IllegalStateException("RoundUtils is a static class");
    }

    public static List<Player> getWinningPlayers(Round round) {
        ArrayList<Player> winningPlayers = new ArrayList<>();
        return winningPlayers;
    }
}
