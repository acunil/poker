package org.example.base.domain.utils;

import lombok.val;
import org.example.base.domain.components.Hand;
import org.example.base.domain.components.Player;
import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.HandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.base.domain.utils.GeneralUtils.getCardsFromLabel;

import java.util.List;


class RoundUtilsTest {

    Player player1;
    Player player2;
    List<Card> communityCards;

    @BeforeEach
    void setUp() {
        player1 = new Player("Player1", 100);
        player2 = new Player("Player2", 150);
        communityCards = getCardsFromLabel("KC,TS,4H,9C,4C");
        player1.setPocketCards(getCardsFromLabel("4S,TD"));
        player2.setPocketCards(getCardsFromLabel("AC,6C"));
    }

    @Test
    void getPlayerHands() {
        Hand p1ExpectedHand = new Hand(HandType.FULL_HOUSE, getCardsFromLabel("4S,4C,4H,TS,TD"));
        Hand p2ExpectedHand = new Hand(HandType.FLUSH, getCardsFromLabel("AC,KC,9C,6C,4C"));

        List<Hand> result = RoundUtils.getPlayerHands(List.of(player1, player2), communityCards);

        assertThat(result).hasSize(2);

        val p1Hand = result.get(0);
        assertThat(p1Hand.getHandType()).isEqualTo(p1ExpectedHand.getHandType());
        assertThat(p1Hand.getCards()).containsAll(p1ExpectedHand.getCards());

        val p2Hand = result.get(1);
        assertThat(p2Hand.getHandType()).isEqualTo(p2ExpectedHand.getHandType());
        assertThat(p2Hand.getCards()).containsAll(p2ExpectedHand.getCards());
    }

    @Test
    void getWinningPlayers_returnsSingleWinner() {
        val result = RoundUtils.getWinningPlayers(List.of(player1, player2), communityCards);
        assertThat(result).hasSize(1);
        Player winningPlayer = result.get(0);
        assertThat(winningPlayer.getName()).isEqualTo(player1.getName());
    }

    @Test
    void getWinningPlayers_returnsMultipleWinners() {
        player1.setPocketCards(getCardsFromLabel("KS,2D"));
        player2.setPocketCards(getCardsFromLabel("KH,3D"));

        val result = RoundUtils.getWinningPlayers(List.of(player1, player2), communityCards);
        assertThat(result).hasSize(2);
        Player winningPlayer1 = result.get(0);
        assertThat(winningPlayer1.getName()).isEqualTo(player1.getName());
        Player winningPlayer2 = result.get(1);
        assertThat(winningPlayer2.getName()).isEqualTo(player2.getName());
    }
}