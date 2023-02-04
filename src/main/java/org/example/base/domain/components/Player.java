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

    public Player(String name, Integer chips) {
        this.name = name;
        this.chips = chips;
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

    @Getter
    @Setter
    public boolean isActive = true;

    public Hand getBestHand(List<Card> communityCards) {
        ArrayList<Card> combinedCards = new ArrayList<>();
        combinedCards.addAll(communityCards);
        combinedCards.addAll(pocketCards);
        return IdentifyHandUtils.getBestHand(combinedCards);
    }

    public Integer placeBet(Integer bet) {
        if (bet > chips) {
            throw new IllegalArgumentException(
                String.format("Player %s can not bet %s. Player chips: %s", name, bet, chips));
        }
        chips -= bet;
        return chips;
    }

    public void winPot(Integer pot) {
        chips += pot;
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
