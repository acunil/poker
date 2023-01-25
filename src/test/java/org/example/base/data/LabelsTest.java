package org.example.base.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LabelsTest {

    @ParameterizedTest
    @ValueSource(strings = {"AS", "4C", "9D", "JH"})
    void isValid_returnsTrue(String label) {
        assertThat(Labels.isValid(label)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"AS ", "4c", "9D,", ",JH"})
    void isValid_returnsFalse(String label) {
        assertThat(Labels.isValid(label)).isFalse();
    }

}