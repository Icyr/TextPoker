package entities;

import java.util.ArrayList;
import java.util.List;

public class Table
{
    private List<Card> cards;
    private Dealer dealer;

    public Table(Dealer dealer)
    {
        this.dealer = dealer;
        cards = new ArrayList<Card>();
    }

    //method for probability calculations
    public void setCards(List<Card> cards)
    {
        this.cards.addAll(cards);
    }

    public void flop()
    {
        cards.addAll(dealer.getCards(3));
    }

    public void turn()
    {
        cards.add(dealer.getCard());
    }

    public void river()
    {
        cards.add(dealer.getCard());
    }

    public List<Card> getCardsOnTable()
    {
        return cards;
    }

    public String tableCardsToString()
    {
        String res = "";
        for (Card card : cards)
        {
            res += card.getNominal() + " " + card.getColor() + " ";
        }
        return res;
    }
}
