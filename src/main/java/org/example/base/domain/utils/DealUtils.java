package org.example.base.domain.utils;

import org.example.base.domain.enums.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealUtils {

    private DealUtils() {
        throw new IllegalStateException("DealUtils is a static class");
    }

    public static List<Card> shuffledDeck() {
        List<Card> values = new ArrayList<>(List.of(Card.values()));
        Collections.shuffle(values);
        return values;
    }


}
