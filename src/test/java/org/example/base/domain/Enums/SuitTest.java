package org.example.base.domain.Enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SuitTest {

    @Test
    void getLabel() {
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