import entities.players.ComputerPlayer;
import entities.players.Player;
import entities.Card;
import entities.Hand;
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
        cards.add(new Card("H", 2));
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 4));
        cards.add(new Card("C", 5));
        cards.add(new Card("H", 6));
        cards.add(new Card("S", 4));
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
        cards.add(new Card("H", 10));
        cards.add(new Card("H", 11));
        cards.add(new Card("H", 12));
        cards.add(new Card("H", 13));
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        Assert.assertEquals(5, Utils.getSameColorCount("H", cards));
    }

    @Test
    public void testGetCardsWithPreferredColor()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 10));
        cards.add(new Card("H", 11));
        cards.add(new Card("D", 12));
        cards.add(new Card("C", 13));
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        List<Card> expectedCards = new ArrayList<Card>();
        expectedCards.add(new Card("H", 10));
        expectedCards.add(new Card("H", 11));
        expectedCards.add(new Card("H", 14));
        Assert.assertEquals(Utils.getCardsWithPreferredColor("H", cards), expectedCards);
    }
}
