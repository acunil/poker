package org.example.base.domain.components;

import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.Stage;
import org.example.base.domain.utils.GeneralUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    public static final String PRE_FLOP_REGEX =
        "Player: Red \\| Position: 0 \\| Chips: 100 \\| Pocket: \\w\\w,\\w\\w\\n"
            + "Player: Blue \\| Position: 1 \\| Chips: 150 \\| Pocket: \\w\\w,\\w\\w\\n";
    public static final String FLOP_REGEX = "Flop: \\w\\w,\\w\\w,\\w\\w\\n";
    public static final String TURN_REGEX = "Turn: \\w\\w\\n";
    public static final String RIVER_REGEX = "River: \\w\\w\\n";
    Round round;
    Player player1;
    Player player2;

    @BeforeEach
    void setUp() {
        round = new Round();
        player1 = new Player("Red", 100);
        player2 = new Player("Blue", 150);
        player1.setPosition(0);
        player2.setPosition(1);
        round.setPlayers(List.of(player1, player2));
        round.dealPocketCards();
    }

    @Test
    void dealPocketCards_setsDeckToFullDeck() {
        String shuffledDeckLabel = GeneralUtils.getLabelFromCards(round.getDeck());
        assertThat(shuffledDeckLabel).hasSize(3 * 52 - 1); // 52 cards formatted "XY," less the final ','
    }

    @Test
    void dealPocketCards_givesEachPlayerUniquePocketCards() {
        List<Card> player1PocketCards = player1.getPocketCards();
        List<Card> player2PocketCards = player2.getPocketCards();
        assertThat(List.of(player1PocketCards, player2PocketCards)).allMatch(cards -> cards.size() == 2);
        assertThat(player1PocketCards).noneMatch(player2PocketCards::contains);
    }

    @Test
    void dealPocketCards_logsCardsDealt() {
        assertThat(round.getRoundLog()).hasSize(1);
        assertThat(round.getRoundLog().get(0)).matches(PRE_FLOP_REGEX);
    }

    @Test
    void dealFlop_logsCardsDealt() {
        round.dealFlop();
        assertThat(round.getRoundLog()).hasSize(2);
        assertThat(round.getRoundLog().get(1)).matches(FLOP_REGEX);
    }

    @Test
    void dealTurn_logsCardsDealt() {
        round.dealFlop();
        round.dealTurn();
        assertThat(round.getRoundLog()).hasSize(3);
        assertThat(round.getRoundLog().get(2)).matches(TURN_REGEX);
    }

    @Test
    void dealRiver_logsCardsDealt() {
        round.dealFlop();
        round.dealTurn();
        round.dealRiver();
        assertThat(round.getRoundLog()).hasSize(4);
        assertThat(round.getRoundLog().get(3)).matches(RIVER_REGEX);
    }

    @Test
    void printRoundLog() {
        round.dealFlop();
        round.dealTurn();
        round.dealRiver();
        String regex = PRE_FLOP_REGEX + FLOP_REGEX + TURN_REGEX + RIVER_REGEX;
        assertThat(round.printRoundLog()).matches(regex);
    }


    @Test
    void stageIsProgressed() {
        assertThat(round.getStage()).isEqualTo(Stage.PREFLOP);
        round.dealFlop();
        assertThat(round.getStage()).isEqualTo(Stage.FLOP);
        round.dealTurn();
        assertThat(round.getStage()).isEqualTo(Stage.TURN);
        round.dealRiver();
        assertThat(round.getStage()).isEqualTo(Stage.RIVER);
    }

    @Test
    void incrementPot() {
        assertThat(round.getPot()).isZero();
        round.incrementPot(5);
        assertThat(round.getPot()).isEqualTo(5);
        round.incrementPot(11);
        assertThat(round.getPot()).isEqualTo(16);
    }

    @Test
    void getActivePlayers() {
        Player player3 = new Player("Yellow", 0);
        player3.setActive(false);
        assertThat(round.getActivePlayers())
            .hasSize(2)
            .allMatch(Player::isActive)
            .containsOnly(player1, player2);
    }

    @Test
    void distributePot() {
        round.incrementPot(120);
        round.dealFlop();
        round.dealTurn();
        round.dealRiver();
        round.distributePot();
        ArrayList<String> roundLog = round.getRoundLog();
        String potReport = roundLog.get(roundLog.size() - 1);
        String regex = "Final pot size: 120 \\| Winners?: \\w+(, \\w+)*";
        assertThat(potReport).matches(regex);
    }


}