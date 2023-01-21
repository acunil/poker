package Helpers;

import Enums.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static Enums.Card.*;
import static Enums.Card.KING_SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IdentifyHandUtilTest {

    @Test
    void getHighCardHand() {
        List<Card> input = List.of(KING_SPADES, JACK_HEARTS, FOUR_SPADES, FIVE_DIAMONDS, NINE_DIAMONDS, TWO_HEARTS, TEN_CLUBS);
        List<Card> result = IdentifyHandUtil.getHighCardHand(input);
        assertThat(result).containsExactly(KING_SPADES, JACK_HEARTS, TEN_CLUBS, NINE_DIAMONDS, FIVE_DIAMONDS);
    }

    @Test
    void getPairHand() {
        List<Card> input = List.of(KING_SPADES, JACK_HEARTS, FOUR_SPADES, FIVE_DIAMONDS, NINE_DIAMONDS, KING_HEARTS, TEN_CLUBS);
        List<Card> result = IdentifyHandUtil.getPairHand(input);
        assertThat(result).containsExactly(KING_SPADES, KING_HEARTS, JACK_HEARTS, TEN_CLUBS, NINE_DIAMONDS);
    }


    @Test
    void verifyHand_givenNullInput_throwsException() {
        assertThatThrownBy(() -> IdentifyHandUtil.verifyHand(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cards input must not be null");
    }

    @Test
    void verifyHand_givenInputWithWrongSize_throwsException() {
        assertThatThrownBy(() -> IdentifyHandUtil.verifyHand(List.of(EIGHT_DIAMONDS)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cards input size must be 7");
    }

    @Test
    void verifyHand_givenInputContainsDuplicates_throwsException() {
        assertThatThrownBy(() -> IdentifyHandUtil.verifyHand(List.of(EIGHT_DIAMONDS, EIGHT_DIAMONDS, FIVE_CLUBS, JACK_SPADES, ACE_SPADES, TWO_CLUBS, KING_SPADES)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cards input must all be unique: 8D,8D,5C,JS,AS,2C,KS");
    }
}