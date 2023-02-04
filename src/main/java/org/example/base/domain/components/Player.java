package org.example.base.domain.components;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.base.domain.enums.Card;
import org.example.base.domain.utils.GeneralUtils;
import org.example.base.domain.utils.IdentifyHandUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Player {

    public Player(String name, Integer chips, Integer position) {
        this.name = name;
        this.chips = chips;
        this.position = position;
    }

    @Getter
    @Setter
    private Integer chips;

    @Getter
    @Setter
    private Integer position;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<Card> pocketCards;

    public Hand getBestHand(List<Card> communityCards) {
        ArrayList<Card> combinedCards = new ArrayList<>();
        combinedCards.addAll(communityCards);
        combinedCards.addAll(pocketCards);
        return IdentifyHandUtils.getBestHand(combinedCards);
    }

    public Integer placeBet(Integer bet) {
        if (bet > chips) {
            return null;
        }
        chips -= bet;
        return chips;
    }

    public Integer winPot(Integer pot) {
        chips += pot;
        return chips;
    }

    public String logStatus() {
        StringBuilder status = new StringBuilder();
        status.append("Player: ")
                .append(name)
                .append(" | Position: ")
                .append(position)
                .append(" | Chips: ")
                .append(chips)
                .append(" | Pocket: ")
                .append(GeneralUtils.getLabelFromCards(pocketCards))
                .append("\n");
        return status.toString();
    }

}
