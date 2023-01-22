package org.example.base.domain.Helpers;

import org.example.base.domain.Components.Hand;
import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.HandType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.example.base.domain.Enums.Card.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.base.domain.Helpers.GeneralUtil.getCardsFromLabel;

class IdentifyHandUtilTest {

    public static final String HIGH_CARD_INPUT = "8S,5C,AS,KH,TD,2H,3D";
    public static final String HIGH_CARD_OUTPUT = "AS,KH,TD,8S,5C";
    public static final String PAIR_INPUT = "4D,JH,KC,4C,3H,AS,8D";
    public static final String PAIR_OUTPUT = "4C,4D,AS,KC,JH";
    public static final String TWO_PAIR_INPUT = "AD,KS,KC,4C,3H,AH,8D";
    public static final String TWO_PAIR_OUTPUT = "AH,AD,KS,KC,8D";
    public static final String THREE_KIND_INPUT = "9S,9H,4C,9C,8D,KH,AD";
    public static final String THREE_KIND_OUTPUT = "9S,9C,9H,AD,KH";
    public static final String FOUR_KIND_INPUT = "9H,9C,4C,9S,8D,KH,9D";
    public static final String FOUR_KIND_OUTPUT = "9S,9C,9H,9D,KH";

    @Test
    void getHighCardHand() {
        List<Card> input = getCardsFromLabel(HIGH_CARD_INPUT);
        Hand result = IdentifyHandUtil.getHighCardHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.HIGH_CARD);
        assertThat(result.getCards()).isEqualTo(getCardsFromLabel(HIGH_CARD_OUTPUT));
    }

    @Test
    void getPairHand() {
        List<Card> input = getCardsFromLabel(PAIR_INPUT);
        Hand result = IdentifyHandUtil.getPairHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.PAIR);
        assertThat(result.getCards()).isEqualTo(getCardsFromLabel(PAIR_OUTPUT));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            THREE_KIND_INPUT,
            TWO_PAIR_INPUT,
            HIGH_CARD_INPUT,
            FOUR_KIND_INPUT
    })
    void getPairHand_givenNotExactlyPair_returnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getPairHand(cards)).isNull();
    }

    @Test
    void getTwoPairHand() {
        List<Card> input = getCardsFromLabel(TWO_PAIR_INPUT);
        List<Card> expected = getCardsFromLabel(TWO_PAIR_OUTPUT);
        Hand result = IdentifyHandUtil.getTwoPairHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.TWO_PAIR);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            THREE_KIND_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            FOUR_KIND_INPUT
    })
    void getTwoPairHand_givenNotExactlyTwoPair_returnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getTwoPairHand(cards)).isNull();
    }

    @Test
    void getThreeOfAKindHand() {
        List<Card> input = getCardsFromLabel(THREE_KIND_INPUT);
        List<Card> expected = getCardsFromLabel(THREE_KIND_OUTPUT);
        Hand result = IdentifyHandUtil.getThreeOfAKindHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.THREE_KIND);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            TWO_PAIR_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            FOUR_KIND_INPUT
    })
    void getThreeOfAKindHand_givenNotExactlyThreeOfAKind_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getThreeOfAKindHand(cards)).isNull();
    }

    @Test
    void getFourOfAKindHand() {
        List<Card> input = getCardsFromLabel(FOUR_KIND_INPUT);
        List<Card> expected = getCardsFromLabel(FOUR_KIND_OUTPUT);
        Hand result = IdentifyHandUtil.getFourOfAKindHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.FOUR_KIND);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            TWO_PAIR_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            THREE_KIND_INPUT
    })
    void getFourOfAKindHand_givenNotExactlyFourOfAKind_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getFourOfAKindHand(cards)).isNull();
    }

    @Test
    void verifyInput_givenInputWithWrongSize_throwsException() {
        assertThatThrownBy(() -> IdentifyHandUtil.verifyInput(List.of(EIGHT_DIAMONDS)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cards input size must be 7");
    }

    @Test
    void verifyInput_givenInputContainsDuplicates_throwsException() {
        assertThatThrownBy(() -> IdentifyHandUtil.verifyInput(List.of(EIGHT_DIAMONDS, EIGHT_DIAMONDS, FIVE_CLUBS, JACK_SPADES, ACE_SPADES, TWO_CLUBS, KING_SPADES)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cards input must all be unique: 8D,8D,5C,JS,AS,2C,KS");
    }
}