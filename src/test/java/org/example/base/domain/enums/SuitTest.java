package org.example.base.domain.enums;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SuitTest {

    @ParameterizedTest
    @CsvSource(value = {
            "S,SPADES",
            "C,CLUBS",
            "H,HEARTS",
            "D,DIAMONDS"
    })
    void getLabel(String expectedLabel, Suit suit) {
        assertThat(suit.getLabel()).isEqualTo(expectedLabel);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "S,SPADES",
            "C,CLUBS",
            "H,HEARTS",
            "D,DIAMONDS"
    })
    void fromLabel(String label, Suit expected) {
        Suit result = Suit.fromLabel(label);

        assertThat(result).isEqualTo(expected);
    }
}