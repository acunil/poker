package org.example.base.domain.components;

import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.Stage;
import org.example.base.domain.utils.GeneralUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class RoundTest {

    Round round;
    Player player1;
    Player player2;

    @BeforeEach
    void setUp() {
        round = new Round();
        player1 = new Player("Red", 100, 0);
        player2 = new Player("Blue", 150, 1);
        round.setPlayers(List.of(player1, player2));
        round.dealPocketCards();
    }

    @Test
    void dealPocketCards_setsDeckToFullDeck() {
        String shuffledDeckLabel = GeneralUtils.getLabelFromCards(round.getDeck());
        assertThat(shuffledDeckLabel).hasSize(3 * 52 - 1);
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
        String regex = "Player: Red \\| Position: 0 \\| Chips: 100 \\| Pocket: \\w\\w,\\w\\w\\n"
                + "Player: Blue \\| Position: 1 \\| Chips: 150 \\| Pocket: \\w\\w,\\w\\w\\n";
        assertThat(round.getRoundLog()).matches(regex);
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

}