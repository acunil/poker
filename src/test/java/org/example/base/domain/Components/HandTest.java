package org.example.base.domain.Components;

import lombok.val;
import org.example.base.domain.Enums.HandType;
import org.example.base.domain.Helpers.GeneralUtil;
import org.example.base.domain.Helpers.IdentifyHandUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    @Test
    void compare() {
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