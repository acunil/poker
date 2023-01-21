package Helpers;

import Enums.Card;
import Enums.CardType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static Enums.Card.*;
import static Enums.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;

class GeneralUtilTest {

    @Test
    void getHighCard_returnsHighestValue() {
        List<Card> input = List.of(JACK_SPADES, THREE_CLUBS, TWO_SPADES, FIVE_CLUBS, KING_SPADES, EIGHT_DIAMONDS, SEVEN_SPADES);
        Integer result = GeneralUtil.getHighestCardValue(input);
        assertThat(result).isEqualTo(KING.getValue());
    }


    @Test
    void getLabels() {
        String result = GeneralUtil.getLabels(List.of(FIVE_CLUBS, ACE_DIAMONDS, JACK_HEARTS));
        assertThat(result).isEqualTo("5C,AD,JH");
    }

    @Test
    void getHighestCardValue_returnsHighestValue() {
        List<CardType> input = List.of(EIGHT, JACK);
        Integer result = GeneralUtil.getHighestCardTypeValue(input);
        assertThat(result).isEqualTo(JACK.getValue());
    }

    @Test
    void orderCardsByValue_returnsListOfCardsWithMostValuableFirst() {
        List<Card> input = List.of(FIVE_CLUBS, ACE_DIAMONDS, JACK_HEARTS);
        List<Card> result = GeneralUtil.orderCardsByValue(input);
        assertThat(result).isEqualTo(List.of(ACE_DIAMONDS, JACK_HEARTS, FIVE_CLUBS));
    }

    @Test
    void getCardsFromLabel() {
        String input = "3D,9C,5C,AD,KH";
        List<Card> result = GeneralUtil.getCardsFromLabel(input);
        assertThat(result).isEqualTo(List.of(THREE_DIAMONDS, NINE_CLUBS, FIVE_CLUBS, ACE_DIAMONDS, KING_HEARTS));
    }
}