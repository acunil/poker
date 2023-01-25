package org.example.base.domain.utils;

import org.assertj.core.api.Assertions;
import org.example.base.domain.components.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

class CompareHandsUtilsTest {

    @Test
    void getWinningHands_returnsBestHand() {
        Hand aceHighPairHand = IdentifyHandUtils.getBestHand("AD,AS,8D,9S,4C,5D,7H");
        Hand tenHighPairHand = IdentifyHandUtils.getBestHand("KD,TH,TS,9S,4C,5D,7H");
        Hand highCardHand = IdentifyHandUtils.getBestHand("KD,3D,TS,9S,4C,5D,7H");
        List<Hand> result = CompareHandsUtils.getWinningHands(List.of(tenHighPairHand, aceHighPairHand, highCardHand));
        Assertions.assertThat(result)
                .hasSize(1)
                .containsOnly(aceHighPairHand);
    }

    @Test
    void getWinningHands_returnsBestHands() {
        Hand aceHighPairHand1 = IdentifyHandUtils.getBestHand("AD,AS,8D,9S,4C,5D,7H");
        Hand aceHighPairHand2 = IdentifyHandUtils.getBestHand("AC,AH,8D,9S,4C,5D,7H");
        Hand tenHighPairHand = IdentifyHandUtils.getBestHand("KD,TH,TS,9S,4C,5D,7H");
        List<Hand> result = CompareHandsUtils.getWinningHands(List.of(aceHighPairHand1, tenHighPairHand, aceHighPairHand2));
        Assertions.assertThat(result)
                .hasSize(2)
                .containsOnly(aceHighPairHand1, aceHighPairHand2);
    }

}