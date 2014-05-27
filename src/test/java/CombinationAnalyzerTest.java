import entities.Card;
import entities.combinations.Combination;
import entities.combinations.Quads;
import entities.combinations.StraightFlush;
import logic.CombinationAnalyzer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CombinationAnalyzerTest
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                //RoyalFlush
                {new Card[]{
                        new Card("H", 10),
                        new Card("H", 11),
                        new Card("H", 12),
                        new Card("H", 13),
                        new Card("H", 14)
                }, new StraightFlush(10)},
                //StraightFlush
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 4),
                        new Card("H", 5),
                        new Card("H", 6),
                        new Card("H", 7)
                }, new StraightFlush(3)},
                //Quads
                {new Card[]{
                        new Card("H", 2),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 3),
                        new Card("H", 3),
                        new Card("S", 4)
                }, new Quads(3)}
        };
        return Arrays.asList(data);
    }

    private Card[] cards;
    private Combination expectedCombination;

    public CombinationAnalyzerTest(Card[] cards, Combination expectedCombination)
    {
        this.cards = cards;
        this.expectedCombination = expectedCombination;
    }

    @Test
    public void testRoyalFlush()
    {
        Combination actualCombination = CombinationAnalyzer.analyzeCombination(Arrays.asList(cards));
        assertThat(actualCombination, equalTo(expectedCombination));
    }

    /*@Test
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
        Assert.assertEquals(108, CombinationAnalyzer.analyzeCombination(cards).getPower());
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
        Assert.assertEquals(95, CombinationAnalyzer.analyzeCombination(cards).getPower());
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
        Assert.assertEquals(82, CombinationAnalyzer.analyzeCombination(cards).getPower());
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
        Assert.assertEquals(79, CombinationAnalyzer.analyzeCombination(cards).getPower());
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
        Assert.assertEquals(56, CombinationAnalyzer.analyzeCombination(cards).getPower());
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
        Assert.assertEquals(54, CombinationAnalyzer.analyzeCombination(cards).getPower());
    }

    @Test
    public void testSet()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("C", 4));
        cards.add(new Card("H", 4));
        cards.add(new Card("S", 4));
        Assert.assertEquals(43, CombinationAnalyzer.analyzeCombination(cards).getPower());
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
        Assert.assertEquals(13, CombinationAnalyzer.analyzeCombination(cards).getPower());
    }*/
}
