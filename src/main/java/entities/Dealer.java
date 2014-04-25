package entities;

import java.util.ArrayList;
import java.util.List;

public class Dealer
{
    private List<Card> pack = new ArrayList<Card>();

    public Dealer()
    {
        pack.add(new Card("H", 2));
        pack.add(new Card("H", 3));
        pack.add(new Card("H", 4));
        pack.add(new Card("H", 5));
        pack.add(new Card("H", 6));
        pack.add(new Card("H", 7));
        pack.add(new Card("H", 8));
        pack.add(new Card("H", 9));
        pack.add(new Card("H", 10));
        pack.add(new Card("H", 11));
        pack.add(new Card("H", 12));
        pack.add(new Card("H", 13));
        pack.add(new Card("H", 14));
        pack.add(new Card("C", 2));
        pack.add(new Card("C", 3));
        pack.add(new Card("C", 4));
        pack.add(new Card("C", 5));
        pack.add(new Card("C", 6));
        pack.add(new Card("C", 7));
        pack.add(new Card("C", 8));
        pack.add(new Card("C", 9));
        pack.add(new Card("C", 10));
        pack.add(new Card("C", 11));
        pack.add(new Card("C", 12));
        pack.add(new Card("C", 13));
        pack.add(new Card("C", 14));
        pack.add(new Card("D", 2));
        pack.add(new Card("D", 3));
        pack.add(new Card("D", 4));
        pack.add(new Card("D", 5));
        pack.add(new Card("D", 6));
        pack.add(new Card("D", 7));
        pack.add(new Card("D", 8));
        pack.add(new Card("D", 9));
        pack.add(new Card("D", 10));
        pack.add(new Card("D", 11));
        pack.add(new Card("D", 12));
        pack.add(new Card("D", 13));
        pack.add(new Card("D", 14));
        pack.add(new Card("S", 2));
        pack.add(new Card("S", 3));
        pack.add(new Card("S", 4));
        pack.add(new Card("S", 5));
        pack.add(new Card("S", 6));
        pack.add(new Card("S", 7));
        pack.add(new Card("S", 8));
        pack.add(new Card("S", 9));
        pack.add(new Card("S", 10));
        pack.add(new Card("S", 11));
        pack.add(new Card("S", 12));
        pack.add(new Card("S", 13));
        pack.add(new Card("S", 14));
    }

    public Card getCard()
    {
        int max = pack.size();
        int index = (int) (Math.random() * (max - 1));
        Card returnee = pack.get(index);
        pack.remove(index);
        return returnee;
    }

    public List<Card> getCards(int count)
    {
        List<Card> res = new ArrayList<Card>();
        for (int i = 0; i < count; i++)
        {
            res.add(getCard());
        }
        return res;
    }

    //test method
    public void removeCardFromDeck(Card card)
    {
        if (pack.contains(card)) pack.remove(card);
    }

    //test method`
    public void removeCardsFromDeck(List<Card> cards)
    {
        for (Card card : cards)
        {
            removeCardFromDeck(card);
        }
    }

}
