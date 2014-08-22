package entities;

import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Dealer
{
    private List<Card> pack = new ArrayList<Card>();

    public Dealer()
    {
        pack.add(new Card(Color.HEARTS, 2));
        pack.add(new Card(Color.HEARTS, 3));
        pack.add(new Card(Color.HEARTS, 4));
        pack.add(new Card(Color.HEARTS, 5));
        pack.add(new Card(Color.HEARTS, 6));
        pack.add(new Card(Color.HEARTS, 7));
        pack.add(new Card(Color.HEARTS, 8));
        pack.add(new Card(Color.HEARTS, 9));
        pack.add(new Card(Color.HEARTS, 10));
        pack.add(new Card(Color.HEARTS, 11));
        pack.add(new Card(Color.HEARTS, 12));
        pack.add(new Card(Color.HEARTS, 13));
        pack.add(new Card(Color.HEARTS, 14));
        pack.add(new Card(Color.CLUBS, 2));
        pack.add(new Card(Color.CLUBS, 3));
        pack.add(new Card(Color.CLUBS, 4));
        pack.add(new Card(Color.CLUBS, 5));
        pack.add(new Card(Color.CLUBS, 6));
        pack.add(new Card(Color.CLUBS, 7));
        pack.add(new Card(Color.CLUBS, 8));
        pack.add(new Card(Color.CLUBS, 9));
        pack.add(new Card(Color.CLUBS, 10));
        pack.add(new Card(Color.CLUBS, 11));
        pack.add(new Card(Color.CLUBS, 12));
        pack.add(new Card(Color.CLUBS, 13));
        pack.add(new Card(Color.CLUBS, 14));
        pack.add(new Card(Color.DIAMONDS, 2));
        pack.add(new Card(Color.DIAMONDS, 3));
        pack.add(new Card(Color.DIAMONDS, 4));
        pack.add(new Card(Color.DIAMONDS, 5));
        pack.add(new Card(Color.DIAMONDS, 6));
        pack.add(new Card(Color.DIAMONDS, 7));
        pack.add(new Card(Color.DIAMONDS, 8));
        pack.add(new Card(Color.DIAMONDS, 9));
        pack.add(new Card(Color.DIAMONDS, 10));
        pack.add(new Card(Color.DIAMONDS, 11));
        pack.add(new Card(Color.DIAMONDS, 12));
        pack.add(new Card(Color.DIAMONDS, 13));
        pack.add(new Card(Color.DIAMONDS, 14));
        pack.add(new Card(Color.SPADES, 2));
        pack.add(new Card(Color.SPADES, 3));
        pack.add(new Card(Color.SPADES, 4));
        pack.add(new Card(Color.SPADES, 5));
        pack.add(new Card(Color.SPADES, 6));
        pack.add(new Card(Color.SPADES, 7));
        pack.add(new Card(Color.SPADES, 8));
        pack.add(new Card(Color.SPADES, 9));
        pack.add(new Card(Color.SPADES, 10));
        pack.add(new Card(Color.SPADES, 11));
        pack.add(new Card(Color.SPADES, 12));
        pack.add(new Card(Color.SPADES, 13));
        pack.add(new Card(Color.SPADES, 14));
    }

    public Card getCard()
    {
        int max = pack.size();
        int index = Utils.getRandomInt(0, max - 1);
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
