package entities;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Hand
{
    private List<Card> cards = new ArrayList<Card>(2);

    public Hand(List<Card> cards)
    {
        this.cards = cards;
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public String toString(){
        String res = "";
        res += Utils.getCorrectNominalString(this.getCards().get(0).getNominal()) + this.getCards().get(0).getColor().getShortName() + " ";
        res += Utils.getCorrectNominalString(this.getCards().get(1).getNominal()) + this.getCards().get(1).getColor().getShortName();
        return res;
    }
}
