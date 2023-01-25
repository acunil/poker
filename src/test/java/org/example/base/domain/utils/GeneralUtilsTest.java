package org.example.base.domain.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.base.domain.enums.Card.ACE_DIAMONDS;
import static org.example.base.domain.enums.Card.EIGHT_DIAMONDS;
import static org.example.base.domain.enums.Card.FIVE_CLUBS;
import static org.example.base.domain.enums.Card.FOUR_CLUBS;
import static org.example.base.domain.enums.Card.FOUR_SPADES;
import static org.example.base.domain.enums.Card.JACK_HEARTS;
import static org.example.base.domain.enums.Card.JACK_SPADES;
import static org.example.base.domain.enums.Card.KING_HEARTS;
import static org.example.base.domain.enums.Card.KING_SPADES;
import static org.example.base.domain.enums.Card.NINE_CLUBS;
import static org.example.base.domain.enums.Card.NINE_DIAMONDS;
import static org.example.base.domain.enums.Card.QUEEN_CLUBS;
import static org.example.base.domain.enums.Card.SEVEN_SPADES;
import static org.example.base.domain.enums.Card.THREE_CLUBS;
import static org.example.base.domain.enums.Card.THREE_DIAMONDS;
import static org.example.base.domain.enums.Card.THREE_HEARTS;
import static org.example.base.domain.enums.Card.TWO_SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.base.domain.enums.CardType.EIGHT;
import static org.example.base.domain.enums.CardType.JACK;
import static org.example.base.domain.enums.CardType.KING;

import org.example.base.domain.enums.Card;
import org.example.base.domain.enums.CardType;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class GeneralUtilsTest {

    @Test
    void getHighCard_returnsHighestValue() {
        List<Card> input = List.of(JACK_SPADES, THREE_CLUBS, TWO_SPADES, FIVE_CLUBS, KING_SPADES, EIGHT_DIAMONDS, SEVEN_SPADES);
        Integer result = GeneralUtils.getHighestCardValue(input);
        assertThat(result).isEqualTo(KING.getValue());
    }


    @Test
    void getLabelFromCards() {
        String result = GeneralUtils.getLabelFromCards(List.of(FIVE_CLUBS, ACE_DIAMONDS, JACK_HEARTS));
        assertThat(result).isEqualTo("5C,AD,JH");
    }

    @Test
    void getHighestCardTypeValue_returnsHighestValue() {
        List<CardType> input = List.of(EIGHT, JACK);
        Integer result = GeneralUtils.getHighestCardTypeValue(input);
        assertThat(result).isEqualTo(JACK.getValue());
    }

    @Test
    void getHighestCardValue_returnsHighestValue() {
        List<Card> input = List.of(EIGHT_DIAMONDS, QUEEN_CLUBS, FOUR_SPADES);
        val result = GeneralUtils.getHighestCardValue(input);
        assertThat(result).isEqualTo(QUEEN_CLUBS.getValue());
    }

    @Test
    void orderCardsByValue_returnsListOfCardsWithMostValuableFirst() {
        List<Card> input = List.of(FIVE_CLUBS, ACE_DIAMONDS, JACK_HEARTS, KING_SPADES, FOUR_CLUBS, NINE_DIAMONDS, THREE_HEARTS);
        List<Card> result = GeneralUtils.orderCardsByValue(input);
        assertThat(result).containsExactly(ACE_DIAMONDS, KING_SPADES, JACK_HEARTS, NINE_DIAMONDS, FIVE_CLUBS, FOUR_CLUBS, THREE_HEARTS);
    }

    @Test
    void getCardsFromLabel() {
        String input = "3D,9C,5C,AD,KH";
        List<Card> result = GeneralUtils.getCardsFromLabel(input);
        assertThat(result).containsExactly(THREE_DIAMONDS, NINE_CLUBS, FIVE_CLUBS, ACE_DIAMONDS, KING_HEARTS);
    }

    @Test
    void compareByValueThenSuit() {
        String input = "4D,9C,AH,KS,9S";
        String expected = "AH,KS,9S,9C,4D";
        assertThat(GeneralUtils.getCardsFromLabel(input).stream().sorted(GeneralUtils.ORDER_BY_VALUE_DESC_THEN_SUIT).toList())
                .isEqualTo(GeneralUtils.getCardsFromLabel(expected));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "9c,5D:\"9c\"",
            "AC, 6S:\" 6S\"",
            "kC,AD:\"kC\"",
            "GG,5H,4D:\"GG\""
    }, delimiter = ':')
    void getCardsFromLabel_throwsException(String label, String invalid) {
        assertThatThrownBy(() -> GeneralUtils.getCardsFromLabel(label))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid card label: " + invalid );
    }

}