package logic;

import entities.Card;
import entities.Color;
import entities.combinations.*;
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
                        new Card(Color.HEARTS, 10),
                        new Card(Color.HEARTS, 11),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.HEARTS, 13),
                        new Card(Color.HEARTS, 14)
                }, new StraightFlush(10)},
                //StraightFlush
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.HEARTS, 4),
                        new Card(Color.HEARTS, 5),
                        new Card(Color.HEARTS, 6),
                        new Card(Color.HEARTS, 7)
                }, new StraightFlush(3)},
                //Quads
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 3),
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 4)
                }, new Quads(3)},
                //Full House
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 4),
                        new Card(Color.HEARTS, 4)
                }, new FullHouse(3, 4)},
                //Flush
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.HEARTS, 3),
                        new Card(Color.HEARTS, 5),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.HEARTS, 13)
                }, new Flush(13, Color.HEARTS)},
                //Straight
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 4),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.HEARTS, 6)
                }, new Straight(2)},
                //Ace Straight
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 4),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.HEARTS, 14)
                }, new Straight(1)},
                //Set
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 2),
                        new Card(Color.DIAMONDS, 2)
                }, new Set(2)},
                //Two Pairs
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 2),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 3)
                }, new TwoPairs(3, 2)},
                //Pair
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 2)
                }, new Pair(2)},
                //Kicker
                {new Card[]{
                        new Card(Color.HEARTS, 2)
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
