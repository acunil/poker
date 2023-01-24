package org.example.base.domain.Helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.base.domain.Enums.Card.ACE_SPADES;
import static org.example.base.domain.Enums.Card.EIGHT_DIAMONDS;
import static org.example.base.domain.Enums.Card.FIVE_CLUBS;
import static org.example.base.domain.Enums.Card.JACK_SPADES;
import static org.example.base.domain.Enums.Card.KING_SPADES;
import static org.example.base.domain.Enums.Card.TWO_CLUBS;
import static org.example.base.domain.Helpers.GeneralUtil.getCardsFromLabel;

import org.example.base.domain.Components.Hand;
import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.HandType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class IdentifyHandUtilTest {

    public static final String HIGH_CARD_INPUT = "8S,5C,AS,KH,TD,2H,3D";
    public static final String HIGH_CARD_OUTPUT = "AS,KH,TD,8S,5C";
    public static final String PAIR_INPUT = "4D,JH,KC,4C,3H,AS,8D";
    public static final String PAIR_OUTPUT = "4C,4D,AS,KC,JH";
    public static final String TWO_PAIR_INPUT = "KD,7S,7C,4C,3H,KH,8D";
    public static final String TWO_PAIR_OUTPUT = "KH,KD,7S,7C,8D";
    public static final String THREE_KIND_INPUT = "9S,9H,4C,9C,8D,KH,AD";
    public static final String THREE_KIND_OUTPUT = "9S,9C,9H,AD,KH";
    public static final String FOUR_KIND_INPUT = "AH,9H,9C,4C,9S,8D,9D";
    public static final String FOUR_KIND_OUTPUT = "9S,9C,9H,9D,AH";
    public static final String FULL_HOUSE_INPUT = "4H,5D,7D,4S,JH,JC,4D";
    public static final String FULL_HOUSE_OUTPUT = "4S,4H,4D,JC,JH";
    public static final String FLUSH_INPUT = "5D,KD,AS,JD,3D,9H,7D";
    public static final String FLUSH_OUTPUT = "KD,JD,7D,5D,3D";
    public static final String STRAIGHT_INPUT = "8D,TH,7S,2H,KC,9C,6H";
    public static final String STRAIGHT_OUTPUT = "TH,9C,8D,7S,6H";
    public static final String STRAIGHT_FLUSH_INPUT = "4D,5D,6D,8D,7D,TC,JH";
    public static final String STRAIGHT_FLUSH_OUTPUT = "8D,7D,6D,5D,4D";
    public static final String ROYAL_FLUSH_INPUT = "KC,JC,QC,TC,5H,AC,4D";
    public static final String ROYAL_FLUSH_OUTPUT = "AC,KC,QC,JC,TC";

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
    void getPairHand_givenNoPresentPair_returnsNull(String input) {
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

    @Test
    void getTwoPairHand_givenThreeMatchingPairs_returnsHighestTwoPairs() {
        List<Card> input = getCardsFromLabel("9D,4H,QC,QS,4D,JH,9S");
        List<Card> expected = getCardsFromLabel("QS,QC,9S,9D,JH");
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
    void getTwoPairHand_givenNoPresentTwoPair_returnsNull(String input) {
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
    void getThreeOfAKindHand_givenNoPresentThreeOfAKind_ReturnsNull(String input) {
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

    @Test
    void getFourOfAKindHand_givenFourAndThreeKind_returnsFourKind_andHighCardPrioritizedBySuit() {
        List<Card> input = getCardsFromLabel("5D,5C,5S,5H,AC,AD,AH");
        List<Card> expected = getCardsFromLabel("5S,5C,5H,5D,AC");
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
    void getFourOfAKindHand_givenNoPresentFourOfAKind_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getFourOfAKindHand(cards)).isNull();
    }

    @Test
    void getFullHouseHand() {
        List<Card> input = getCardsFromLabel(FULL_HOUSE_INPUT);
        List<Card> expected = getCardsFromLabel(FULL_HOUSE_OUTPUT);
        Hand result = IdentifyHandUtil.getFullHouseHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.FULL_HOUSE);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getFullHouseHand_givenTwoThreeOfAKinds_returnsHigherTripsFullOfLower() {
        List<Card> input = getCardsFromLabel("TS,4H,4D,TC,9D,4S,TD");
        List<Card> expected = getCardsFromLabel("TS,TC,TD,4S,4H");
        Hand result = IdentifyHandUtil.getFullHouseHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.FULL_HOUSE);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getFullHouseHand_givenThreeOfAKindAndTwoPairs_returnsTripsFullOfHigherPair() {
        List<Card> input = getCardsFromLabel("TS,4H,4D,TC,9D,9S,TD");
        List<Card> expected = getCardsFromLabel("TS,TC,TD,9S,9D");
        Hand result = IdentifyHandUtil.getFullHouseHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.FULL_HOUSE);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            TWO_PAIR_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            THREE_KIND_INPUT,
            FOUR_KIND_INPUT
    })
    void getFullHouseHand_givenNoPresentFullHouse_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getFullHouseHand(cards)).isNull();
    }

    @Test
    void getFlushHand() {
        List<Card> input = getCardsFromLabel(FLUSH_INPUT);
        List<Card> expected = getCardsFromLabel(FLUSH_OUTPUT);
        Hand result = IdentifyHandUtil.getFlushHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.FLUSH);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getFlushHand_givenMoreThanFiveSameSuitCards_returnsHighestFlush() {
        List<Card> input = getCardsFromLabel("5H,4H,KH,JH,7H,TH,9D");
        List<Card> expected = getCardsFromLabel("KH,JH,TH,7H,5H");
        Hand result = IdentifyHandUtil.getFlushHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.FLUSH);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            TWO_PAIR_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            THREE_KIND_INPUT,
            FOUR_KIND_INPUT,
            FULL_HOUSE_INPUT
    })
    void getFlushHand_givenNoPresentFlush_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getFlushHand(cards)).isNull();
    }

    @Test
    void getStraightHand() {
        List<Card> input = getCardsFromLabel(STRAIGHT_INPUT);
        List<Card> expected = getCardsFromLabel(STRAIGHT_OUTPUT);
        Hand result = IdentifyHandUtil.getStraightHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getStraightHand_givenMoreThanFiveStraightCards_returnsHighestStraight() {
        List<Card> input = getCardsFromLabel("4H,6D,5S,3C,8C,7H,9C");
        List<Card> expected = getCardsFromLabel("9C,8C,7H,6D,5S");
        Hand result = IdentifyHandUtil.getStraightHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getStraightHand_givenAceToFiveStraight_returnsFiveHighStraight() {
        List<Card> input = getCardsFromLabel("AC,5S,4H,9D,KS,3D,2H");
        List<Card> expected = getCardsFromLabel("5S,4H,3D,2H,AC");
        Hand result = IdentifyHandUtil.getStraightHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getStraightHand_givenRoyalStraight_returnsAceHighStraight() {
        List<Card> input = getCardsFromLabel("AC,8S,4H,TD,KS,QH,JH");
        List<Card> expected = getCardsFromLabel("AC,KS,QH,JH,TD");
        Hand result = IdentifyHandUtil.getStraightHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getStraightHand_givenStraightAndPairs_returnsStraightPrioritizedBySuit() {
        List<Card> input = getCardsFromLabel("2H,3S,4C,4D,5S,6D,6S");
        List<Card> expected = getCardsFromLabel("6S,5S,4C,3S,2H");
        Hand result = IdentifyHandUtil.getStraightHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            TWO_PAIR_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            THREE_KIND_INPUT,
            FOUR_KIND_INPUT,
            FULL_HOUSE_INPUT,
            "JD,QS,KH,AC,2D,3H,4D"
    })
    void getStraightHand_givenNoPresentStraight_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getStraightHand(cards)).isNull();
    }

    @Test
    void getStraightFlushHand() {
        List<Card> input = getCardsFromLabel(STRAIGHT_FLUSH_INPUT);
        List<Card> expected = getCardsFromLabel(STRAIGHT_FLUSH_OUTPUT);
        Hand result = IdentifyHandUtil.getStraightFlushHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT_FLUSH);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getStraightFlushHand_givenHighCardSuitedHigherThanStraight_returnsStraightFlush() {
        List<Card> input = getCardsFromLabel("2C,3C,4C,5C,6C,KC,KD");
        List<Card> expected = getCardsFromLabel("6C,5C,4C,3C,2C");
        Hand result = IdentifyHandUtil.getStraightFlushHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.STRAIGHT_FLUSH);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @Test
    void getStraightFlushHand_givenRoyalFlush_returnsRoyalFlushHand() {
        List<Card> input = getCardsFromLabel(ROYAL_FLUSH_INPUT);
        List<Card> expected = getCardsFromLabel(ROYAL_FLUSH_OUTPUT);
        Hand result = IdentifyHandUtil.getStraightFlushHand(input);
        assertThat(result).isNotNull();
        assertThat(result.getHandType()).isEqualTo(HandType.ROYAL_FLUSH);
        assertThat(result.getCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            TWO_PAIR_INPUT,
            PAIR_INPUT,
            HIGH_CARD_INPUT,
            THREE_KIND_INPUT,
            STRAIGHT_INPUT,
            FLUSH_INPUT
    })
    void getStraightFlushHand_givenNoPresentStraightFlush_ReturnsNull(String input) {
        List<Card> cards = getCardsFromLabel(input);
        assertThat(IdentifyHandUtil.getStraightFlushHand(cards)).isNull();
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