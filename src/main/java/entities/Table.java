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

    public Table()
    {
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
            String nominalString = Integer.toString(card.getNominal());
            if (nominalString.equals("11"))
            {
                nominalString = "J";
            }
            if (nominalString.equals("12"))
            {
                nominalString = "Q";
            }
            if (nominalString.equals("13"))
            {
                nominalString = "K";
            }
            if (nominalString.equals("14"))
            {
                nominalString = "A";
            }
            res += nominalString + card.getColor().getShortName() + " ";
        }
        return res;
    }
}
