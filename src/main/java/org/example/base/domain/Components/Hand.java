package org.example.base.domain.Components;

import javax.validation.constraints.Size;
import java.util.List;

import org.example.base.domain.Enums.Card;
import org.example.base.domain.Enums.HandType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Hand {
    @NonNull
    @Getter
    final HandType handType;

    @NonNull
    @Getter
    @Size(min = 5, max = 5)
    final List<Card> cards;

}
