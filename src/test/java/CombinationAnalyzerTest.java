import entities.Card;
import logic.CombinationAnalyzer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationAnalyzerTest
{
    @Test
    public void testGetPower()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        cards.add(new Card("D", 13));
        cards.add(new Card("H", 6));
        cards.add(new Card("H", 7));
        cards.add(new Card("S", 9));
        cards.add(new Card("D", 5));
        Assert.assertEquals(27, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testRoyalFlush()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 10));
        cards.add(new Card("H", 11));
        cards.add(new Card("H", 12));
        cards.add(new Card("H", 13));
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        Assert.assertEquals(118, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testStraightFlush()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 3));
        cards.add(new Card("H", 4));
        cards.add(new Card("H", 5));
        cards.add(new Card("S", 5));
        cards.add(new Card("D", 5));
        cards.add(new Card("H", 6));
        cards.add(new Card("H", 7));
        Assert.assertEquals(108, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testQuads()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 3));
        cards.add(new Card("C", 3));
        cards.add(new Card("H", 3));
        cards.add(new Card("S", 4));
        Assert.assertEquals(95, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testFullHouse()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 3));
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 3));
        cards.add(new Card("C", 4));
        cards.add(new Card("H", 4));
        cards.add(new Card("S", 5));
        cards.add(new Card("S", 5));
        Assert.assertEquals(82, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testFlush()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("H", 9));
        cards.add(new Card("H", 10));
        cards.add(new Card("H", 11));
        cards.add(new Card("H", 13));
        cards.add(new Card("S", 14));
        Assert.assertEquals(79, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testStraight()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 4));
        cards.add(new Card("C", 5));
        cards.add(new Card("H", 5));
        cards.add(new Card("H", 6));
        cards.add(new Card("S", 7));
        Assert.assertEquals(56, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testAceStraight()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 4));
        cards.add(new Card("C", 5));
        cards.add(new Card("S", 7));
        cards.add(new Card("H", 14));
        Assert.assertEquals(54, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testSet()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("C", 4));
        cards.add(new Card("H", 4));
        cards.add(new Card("S", 4));
        Assert.assertEquals(43, CombinationAnalyzer.analyzePower(cards));
    }

    @Test
    public void testGetPairs()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("S", 2));
        cards.add(new Card("D", 3));
        cards.add(new Card("C", 5));
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        int[] arr = CombinationAnalyzer.getAllPairs(cards);
        Assert.assertArrayEquals(arr, new int[]{2, 14});
    }

    @Test
    public void testKicker()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 5));
        cards.add(new Card("C", 7));
        cards.add(new Card("H", 13));
        Assert.assertEquals(13, CombinationAnalyzer.analyzePower(cards));
    }
}
