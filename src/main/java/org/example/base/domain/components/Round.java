package org.example.base.domain.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.Stage;
import org.example.base.domain.utils.DealUtils;
import org.example.base.domain.utils.GeneralUtils;

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
    private List<Card> flop;

    @Getter
    private Card turn;

    @Getter
    protected Card river;

    private StringBuilder roundLog = new StringBuilder();


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
    }

    public void dealTurn() {
        checkStage(Stage.FLOP);
        progressStage();
        int cardsDealtAlready = players.size() * HOLDEM_POCKET_SIZE + 3;
        turn = deck.get(cardsDealtAlready);
    }

    public void dealRiver() {
        checkStage(Stage.TURN);
        progressStage();
        int cardsDealtAlready = players.size() * HOLDEM_POCKET_SIZE + 4;
        river = deck.get(cardsDealtAlready);
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
            players.forEach(player -> roundLog.append(player.logStatus()));
        }
        if (stage == Stage.FLOP) {
            roundLog.append("Flop: ")
                    .append(GeneralUtils.getLabelFromCards(flop))
                    .append("\n");
        }
        if (stage == Stage.TURN) {
            roundLog.append("Turn: ")
                    .append(turn.getLabel())
                    .append("\n");
        }
        if (stage == Stage.RIVER) {
            roundLog.append("Turn: ")
                    .append(river.getLabel())
                    .append("\n");
        }
    }

    protected String getRoundLog() {
        return roundLog.toString();
    }

    private void checkStage(Stage stage) {
        if (this.stage != stage) {
            throw new IllegalStateException(String.format("Stage must be %s. Current stage: %s", stage, this.stage));
        }
    }

}
