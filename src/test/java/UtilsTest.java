import entities.Card;
import entities.Color;
import org.junit.Assert;
import org.junit.Test;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest
{
    @Test
    public void testSorter()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Color.HEARTS, 2));
        cards.add(new Card(Color.SPADES, 3));
        cards.add(new Card(Color.DIAMONDS, 4));
        cards.add(new Card(Color.CLUBS, 5));
        cards.add(new Card(Color.HEARTS, 6));
        cards.add(new Card(Color.SPADES, 4));
        cards = Utils.sortCards(cards);
        int[] res = new int[6];
        int i = 0;
        for (Card card : cards)
        {
            res[i] = card.getNominal();
            i++;
        }
        Assert.assertArrayEquals(new int[]{6, 5, 4, 4, 3, 2}, res);
    }

    @Test
    public void testGetSameColor()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Color.HEARTS, 10));
        cards.add(new Card(Color.HEARTS, 11));
        cards.add(new Card(Color.HEARTS, 12));
        cards.add(new Card(Color.HEARTS, 13));
        cards.add(new Card(Color.HEARTS, 14));
        cards.add(new Card(Color.SPADES, 14));
        Assert.assertEquals(5, Utils.getSameColorCount(Color.HEARTS, cards));
    }

    @Test
    public void testGetCardsWithPreferredColor()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Color.HEARTS, 10));
        cards.add(new Card(Color.HEARTS, 11));
        cards.add(new Card(Color.DIAMONDS, 12));
        cards.add(new Card(Color.CLUBS, 13));
        cards.add(new Card(Color.HEARTS, 14));
        cards.add(new Card(Color.SPADES, 14));
        List<Card> expectedCards = new ArrayList<Card>();
        expectedCards.add(new Card(Color.HEARTS, 10));
        expectedCards.add(new Card(Color.HEARTS, 11));
        expectedCards.add(new Card(Color.HEARTS, 14));
        Assert.assertEquals(Utils.getCardsWithPreferredColor(Color.HEARTS, cards), expectedCards);
    }
}
