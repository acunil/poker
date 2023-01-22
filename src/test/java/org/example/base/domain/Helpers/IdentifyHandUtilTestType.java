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

class IdentifyHandUtilTest {

    @Test
    void getHighCardHand() {
        List<Card> input = List.of(KING_SPADES, JACK_HEARTS, FOUR_SPADES, FIVE_DIAMONDS, NINE_DIAMONDS, TWO_HEARTS, TEN_CLUBS);
        Hand result = IdentifyHandUtil.getHighCardHand(input);
        assertThat(result.getHandType()).isEqualTo(HandType.HIGH_CARD);
        assertThat(result.getCards()).containsExactly(KING_SPADES, JACK_HEARTS, TEN_CLUBS, NINE_DIAMONDS, FIVE_DIAMONDS);
    }

    @Test
    void getPairHand() {
        List<Card> input = List.of(FOUR_HEARTS, JACK_HEARTS, FOUR_SPADES, FIVE_DIAMONDS, NINE_DIAMONDS, KING_HEARTS, TEN_CLUBS);
        Hand result = IdentifyHandUtil.getPairHand(input);
        assertThat(result.getHandType()).isEqualTo(HandType.PAIR);
        assertThat(result.getCards()).containsExactly(FOUR_SPADES, FOUR_HEARTS, KING_HEARTS, JACK_HEARTS, TEN_CLUBS);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "AS,AC,AH,KH,4C,3H,8D",
            "AS,AC,KH,KC,4C,3H,8D",
            "AS,KH,TD,8S,5C,2H,3D"
    })
    void getPairHand_givenNotExactlyPair_returnsNull(String input) {
        List<Card> cards = GeneralUtil.getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getPairHand(cards)).isNull();
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