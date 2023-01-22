package org.example.base.domain.Components;

import org.example.base.domain.Enums.Card;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Pair {
    @NonNull
    @Getter
    final Card card1;

    @NonNull
    @Getter
    final Card card2;
}
