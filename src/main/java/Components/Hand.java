package Components;

import Enums.Card;
import Enums.HandType;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

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
