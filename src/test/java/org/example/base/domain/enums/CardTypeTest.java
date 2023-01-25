package org.example.base.domain.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTypeTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,ACE",
            "2,TWO",
            "3,THREE",
            "4,FOUR",
            "5,FIVE",
            "6,SIX",
            "7,SEVEN",
            "8,EIGHT",
            "9,NINE",
            "T,TEN",
            "J,JACK",
            "Q,QUEEN",
            "K,KING"
    })
    void fromLabel(String label, CardType expected) {
        assertThat(CardType.fromLabel(label)).isEqualTo(expected);
    }



}