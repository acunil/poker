package org.example.base.domain.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.Stage;
import org.example.base.domain.utils.DealUtils;
import org.example.base.domain.utils.GeneralUtils;
import org.example.base.domain.utils.RoundUtils;

import java.util.ArrayList;
import java.util.List;

import static org.example.base.domain.utils.GeneralUtils.HOLDEM_POCKET_SIZE;

@RequiredArgsConstructor
public class Round {

    @Getter
    private List<Card> deck;

    @Getter
    @Setter
    private List<Player> players;

    @Getter
    private Stage stage = Stage.PREFLOP;

    @Getter
    private Integer pot = 0;

    @Getter
    private List<Card> flop;

    @Getter
    private Card turn;

    @Getter
    protected Card river;

    @Getter
    private final ArrayList<String> roundLog = new ArrayList<>();


    public void dealPocketCards() {
        checkStage(Stage.PREFLOP);
        deck = DealUtils.shuffledDeck();
        int nextCardIndex = 0;
        for (Player player : players) {
            player.setPocketCards(deck.subList(nextCardIndex, nextCardIndex + HOLDEM_POCKET_SIZE));
            nextCardIndex += HOLDEM_POCKET_SIZE;
        }
        logCardsDealt();
    }


    public void dealFlop() {
        checkStage(Stage.PREFLOP);
        progressStage();
        int cardsDealtAlready = players.size() * HOLDEM_POCKET_SIZE;
        flop = deck.subList(cardsDealtAlready, cardsDealtAlready + 3);
        logCardsDealt();
    }

    public void dealTurn() {
        checkStage(Stage.FLOP);
        progressStage();
        int cardsDealtAlready = players.size() * HOLDEM_POCKET_SIZE + 3;
        turn = deck.get(cardsDealtAlready);
        logCardsDealt();
    }

    public void dealRiver() {
        checkStage(Stage.TURN);
        progressStage();
        int cardsDealtAlready = players.size() * HOLDEM_POCKET_SIZE + 4;
        river = deck.get(cardsDealtAlready);
        logCardsDealt();
    }

    private void progressStage() {
        if (stage == Stage.PREFLOP) {
            stage = Stage.FLOP;
        } else if (stage == Stage.FLOP) {
            stage = Stage.TURN;
        } else if (stage == Stage.TURN) {
            stage = Stage.RIVER;
        }
    }

    @SuppressWarnings("unused")
    public List<Card> getCommunityCards() {
        ArrayList<Card> communityCards = new ArrayList<>(flop);
        if (stage == Stage.FLOP) {
            return communityCards;
        }
        if (stage == Stage.TURN) {
            communityCards.add(turn);
            return communityCards;
        }
        if (stage == Stage.RIVER) {
            communityCards.add(river);
            return communityCards;
        }
        return List.of();
    }

    private void logCardsDealt() {
        if (stage == Stage.PREFLOP) {
            StringBuilder playerLogs = new StringBuilder();
            players.forEach(player -> playerLogs.append(player.logStatus()));
            roundLog.add(playerLogs.toString());
        }
        if (stage == Stage.FLOP) {
            String flopLog = "Flop: " + GeneralUtils.getLabelFromCards(flop) + "\n";
            roundLog.add(flopLog);
        }
        if (stage == Stage.TURN) {
            String turnLog = "Turn: " + turn.getLabel() + "\n";
            roundLog.add(turnLog);
        }
        if (stage == Stage.RIVER) {
            String riverLog = "River: " + river.getLabel() + "\n";
            roundLog.add(riverLog);
        }
    }

    private void checkStage(Stage stage) {
        if (this.stage != stage) {
            throw new IllegalStateException(String.format("Stage must be %s. Current stage: %s", stage, this.stage));
        }
    }

    public String printRoundLog() {
        return String.join("", roundLog);
    }

    public void incrementPot(Integer amount) {
        pot += amount;
    }

    public List<Player> getWinningPlayers() {
        return RoundUtils.getWinningPlayers(this);
    }
}
