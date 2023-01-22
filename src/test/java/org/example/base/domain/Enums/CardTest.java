package org.example.base.domain.Enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class CardTest {

    @ParameterizedTest
    @CsvSource(value = {
            "KH,KING_HEARTS",
            "5C,FIVE_CLUBS",
            "AS,ACE_SPADES",
            "9D,NINE_DIAMONDS"
    })
    void fromLabel(String label, Card expected) {
        assertThat(Card.fromLabel(label)).isEqualTo(expected);
    }

    @Test
    void compareTo() {
        assertThat(Card.KING_HEARTS.compare(Card.QUEEN_DIAMONDS)).isEqualTo(1);
    }

    @Test
    void compareToFalse() {
        assertThat(Card.SIX_SPADES.compare(Card.QUEEN_DIAMONDS)).isEqualTo(-1);
    }

    @Test
    void isHigherThan() {
        assertThat(Card.EIGHT_DIAMONDS.isHigherThan(Card.FIVE_HEARTS)).isTrue();
    }

    @Test
    void isLowerThan() {
        assertThat(Card.THREE_CLUBS.isLowerThan(Card.FIVE_HEARTS)).isTrue();
    }

    @Test
    void isSameAs_True() {
        assertThat(Card.THREE_CLUBS.isSameAs(Card.THREE_DIAMONDS)).isTrue();
    }

    @Test
    void isSameAs_False() {
        assertThat(Card.THREE_CLUBS.isSameAs(Card.TWO_CLUBS)).isFalse();
    }
}