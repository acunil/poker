package org.example.base.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Stage {
    PREFLOP("Pre flop"),
    FLOP("Flop"),
    TURN("Turn"),
    RIVER("River");

    public final String title;

    @Override
    public String toString() {
        return title;
    }

}
