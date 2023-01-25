package org.example.base.domain.Helpers;

import org.assertj.core.api.Assertions;
import org.example.base.domain.Components.Hand;
import org.junit.jupiter.api.Test;

import java.util.List;

class CompareHandsUtilTest {

    @Test
    void getWinningHands_returnsBestHand() {
        Hand aceHighPairHand = IdentifyHandUtil.getBestHand("AD,AS,8D,9S,4C,5D,7H");
        Hand tenHighPairHand = IdentifyHandUtil.getBestHand("KD,TH,TS,9S,4C,5D,7H");
        Hand highCardHand = IdentifyHandUtil.getBestHand("KD,3D,TS,9S,4C,5D,7H");
        List<Hand> result = CompareHandsUtil.getWinningHands(List.of(tenHighPairHand, aceHighPairHand, highCardHand));
        Assertions.assertThat(result)
                .hasSize(1)
                .containsOnly(aceHighPairHand);
    }

    @Test
    void getWinningHands_returnsBestHands() {
        Hand aceHighPairHand1 = IdentifyHandUtil.getBestHand("AD,AS,8D,9S,4C,5D,7H");
        Hand aceHighPairHand2 = IdentifyHandUtil.getBestHand("AC,AH,8D,9S,4C,5D,7H");
        Hand tenHighPairHand = IdentifyHandUtil.getBestHand("KD,TH,TS,9S,4C,5D,7H");
        List<Hand> result = CompareHandsUtil.getWinningHands(List.of(aceHighPairHand1, tenHighPairHand, aceHighPairHand2));
        Assertions.assertThat(result)
                .hasSize(2)
                .containsOnly(aceHighPairHand1, aceHighPairHand2);
    }

}