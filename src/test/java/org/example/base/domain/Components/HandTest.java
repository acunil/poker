package org.example.base.domain.Components;

import lombok.val;
import org.example.base.domain.Helpers.GeneralUtil;
import org.example.base.domain.Helpers.IdentifyHandUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    public static final Hand FULL_HOUSE_HAND = IdentifyHandUtil.getBestHand("KS,KC,KH,4H,4D,9S,TH");
    public static final Hand PAIR_HAND = IdentifyHandUtil.getBestHand("AS,AC,4D,5C,7H,8S,KC");

    @Test
    void compare_givenHandIsHigher_returns1() {
        Integer result = FULL_HOUSE_HAND.compare(PAIR_HAND);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void compare_givenHandIsLower_returnsMinus1() {
        Integer result = PAIR_HAND.compare(FULL_HOUSE_HAND);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void compare_givenHandHasIdenticalValues_returns0() {
        Hand targetPairHand = IdentifyHandUtil.getBestHand("AH,AD,4D,5C,7H,8S,KC");
        Integer result = PAIR_HAND.compare(targetPairHand);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void compare_givenHandHasIdenticalCards_returns0() {
        Integer result = PAIR_HAND.compare(PAIR_HAND);
        assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "KH,KD,TH,TS,4H,7D,9C:KH,KD,JD,JC,4H,7D,9C:-1",
            "KH,KD,KS,KC,JH,7D,9C:KH,KD,KS,KC,TH,7D,9C:1",
            "6S,5S,4S,3S,2S,KS,QS:7S,6S,5S,4S,3S,KS,QS:-1",
            "QD,QC,AS,9C,8H,5S,4S:QH,QD,AS,TC,8H,5S,4S:-1",
            "4H,7H,8H,AH,JH,KD,2D:4H,7H,8H,KH,JH,KD,2D:1",
    }, delimiter = ':')
    void compare_givenHandTypeIsSame_returnsExpectedBasedOnProgressiveHighCard(
        String handInput,
        String targetHandInput,
        Integer expected
    ) {
        Hand hand = IdentifyHandUtil.getBestHand(handInput);
        Hand targetHand = IdentifyHandUtil.getBestHand(targetHandInput);
        Integer result = hand.compare(targetHand);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "JH,AD,4S,9C,JD,3S,2H:11,14,9,4",
            "4D,KS,4H,AC,TH,4S,KC:4,13",
            "AS,5H,4D,2D,JH,TS,3C:5,4,3,2,14",
            "TH,TD,AS,TS,TC,4C,5D:10,14"
    }, delimiter = ':')
    void getUniqueValuesForComparison(String input, String expected) {
        val expectedValues = Arrays.stream(expected.split(",")).map(Integer::valueOf).toList();
        Hand hand = IdentifyHandUtil.getBestHand(GeneralUtil.getCardsFromLabel(input));
        assertThat(hand.getUniqueValuesForComparison()).isEqualTo(expectedValues);
    }
}