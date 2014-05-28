package logic;

import entities.Card;
import entities.combinations.*;
import logic.CombinationAnalyzer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

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
                }, new Quads(3)},
                //Full House
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 4),
                        new Card("H", 4)
                }, new FullHouse(3, 4)},
                //Flush
                {new Card[]{
                        new Card("H", 2),
                        new Card("H", 3),
                        new Card("H", 5),
                        new Card("H", 9),
                        new Card("H", 13)
                }, new Flush(13, "H")},
                //Straight
                {new Card[]{
                        new Card("H", 2),
                        new Card("S", 3),
                        new Card("D", 4),
                        new Card("C", 5),
                        new Card("H", 6)
                }, new Straight(2)},
                //Ace Straight
                {new Card[]{
                        new Card("H", 2),
                        new Card("S", 3),
                        new Card("D", 4),
                        new Card("C", 5),
                        new Card("H", 14)
                }, new Straight(1)},
                //Set
                {new Card[]{
                        new Card("H", 2),
                        new Card("S", 2),
                        new Card("D", 2)
                }, new Set(2)},
                //Two Pairs
                {new Card[]{
                        new Card("H", 2),
                        new Card("S", 2),
                        new Card("D", 3),
                        new Card("C", 3)
                }, new TwoPairs(3, 2)},
                //Pair
                {new Card[]{
                        new Card("H", 2),
                        new Card("S", 2)
                }, new Pair(2)},
                //Kicker
                {new Card[]{
                        new Card("H", 2)
                }, new Kicker(2)},
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
    public void testCombinations()
    {
        Combination actualCombination = CombinationAnalyzer.analyzeCombination(Arrays.asList(cards));
        assertThat(actualCombination, equalTo(expectedCombination));
    }
}
