package org.example.base.domain.Helpers;

import static org.example.base.domain.Enums.Card.ACE_DIAMONDS;
import static org.example.base.domain.Enums.Card.EIGHT_DIAMONDS;
import static org.example.base.domain.Enums.Card.FIVE_CLUBS;
import static org.example.base.domain.Enums.Card.FOUR_CLUBS;
import static org.example.base.domain.Enums.Card.FOUR_SPADES;
import static org.example.base.domain.Enums.Card.JACK_HEARTS;
import static org.example.base.domain.Enums.Card.JACK_SPADES;
import static org.example.base.domain.Enums.Card.KING_HEARTS;
import static org.example.base.domain.Enums.Card.KING_SPADES;
import static org.example.base.domain.Enums.Card.NINE_CLUBS;
import static org.example.base.domain.Enums.Card.NINE_DIAMONDS;
import static org.example.base.domain.Enums.Card.QUEEN_CLUBS;
import static org.example.base.domain.Enums.Card.SEVEN_SPADES;
import static org.example.base.domain.Enums.Card.THREE_CLUBS;
import static org.example.base.domain.Enums.Card.THREE_DIAMONDS;
import static org.example.base.domain.Enums.Card.THREE_HEARTS;
import static org.example.base.domain.Enums.Card.TWO_SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.base.domain.Enums.CardType.EIGHT;
import static org.example.base.domain.Enums.CardType.JACK;
import static org.example.base.domain.Enums.CardType.KING;

import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.CardType;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

class GeneralUtilTest {

    @Test
    void getHighCard_returnsHighestValue() {
        List<Card> input = List.of(JACK_SPADES, THREE_CLUBS, TWO_SPADES, FIVE_CLUBS, KING_SPADES, EIGHT_DIAMONDS, SEVEN_SPADES);
        Integer result = GeneralUtil.getHighestCardValue(input);
        assertThat(result).isEqualTo(KING.getValue());
    }


    @Test
    void getLabelFromCards() {
        String result = GeneralUtil.getLabelFromCards(List.of(FIVE_CLUBS, ACE_DIAMONDS, JACK_HEARTS));
        assertThat(result).isEqualTo("5C,AD,JH");
    }

    @Test
    void getHighestCardTypeValue_returnsHighestValue() {
        List<CardType> input = List.of(EIGHT, JACK);
        Integer result = GeneralUtil.getHighestCardTypeValue(input);
        assertThat(result).isEqualTo(JACK.getValue());
    }

    @Test
    void getHighestCardValue_returnsHighestValue() {
        List<Card> input = List.of(EIGHT_DIAMONDS, QUEEN_CLUBS, FOUR_SPADES);
        val result = GeneralUtil.getHighestCardValue(input);
        assertThat(result).isEqualTo(QUEEN_CLUBS.getValue());
    }

    @Test
    void orderCardsByValue_returnsListOfCardsWithMostValuableFirst() {
        List<Card> input = List.of(FIVE_CLUBS, ACE_DIAMONDS, JACK_HEARTS, KING_SPADES, FOUR_CLUBS, NINE_DIAMONDS, THREE_HEARTS);
        List<Card> result = GeneralUtil.orderCardsByValue(input);
        assertThat(result).containsExactly(ACE_DIAMONDS, KING_SPADES, JACK_HEARTS, NINE_DIAMONDS, FIVE_CLUBS, FOUR_CLUBS, THREE_HEARTS);
    }

    @Test
    void getCardsFromLabel() {
        String input = "3D,9C,5C,AD,KH";
        List<Card> result = GeneralUtil.getCardsFromLabel(input);
        assertThat(result).containsExactly(THREE_DIAMONDS, NINE_CLUBS, FIVE_CLUBS, ACE_DIAMONDS, KING_HEARTS);
    }

    @Test
    void compareByValueThenSuit() {
        String input = "4D,9C,AH,KS,9S";
        String expected = "AH,KS,9S,9C,4D";
        assertThat(GeneralUtil.getCardsFromLabel(input).stream().sorted(GeneralUtil.orderByValueDescThenSuit).toList())
                .isEqualTo(GeneralUtil.getCardsFromLabel(expected));
    }
}