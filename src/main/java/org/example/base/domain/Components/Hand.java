package org.example.base.domain.Components;

import javax.validation.constraints.Size;
import java.util.ArrayList;
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

    public Integer getRank() {
        return handType.getRank();
    }

    public Integer compare(Hand target) {
        if (getRank() > target.getRank()) {
            return 1;
        } else if (getRank() < target.getRank()) {
            return -1;
        } else {
            // hand types are the same
            List<Integer> theseValues = getUniqueValuesForComparison();
            List<Integer> targetValues = target.getUniqueValuesForComparison();
            for (int i = 0; i < theseValues.size(); i++) {
                if (theseValues.get(i) > targetValues.get(i)) {
                    return 1;
                } else if (theseValues.get(i) < targetValues.get(i)) {
                    return -1;
                }
            }
            // hand values are identical
            return 0;
        }
    }

    protected List<Integer> getUniqueValuesForComparison() {
        ArrayList<Integer> unique = new ArrayList<>();
        cards.forEach(card -> {
            Integer value = card.getValue();
            if (!unique.contains(value)) {
                unique.add(value);
            }
        });
        return unique;
    }

}
