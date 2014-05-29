package logic;

import entities.Card;
import entities.Hand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

@RunWith(Parameterized.class)
public class ProbabilityCalculatorTest
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 4)
                }, new Card[]{}, "Preflop", 0.19},
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 4)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("H", 2),
                        new Card("S", 13)
                }, "Flop", 0.46},
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 4)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("H", 2),
                        new Card("S", 13),
                        new Card("C", 13)
                }, "Turn", 0.27},
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 4)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("H", 2),
                        new Card("S", 13),
                        new Card("C", 13),
                        new Card("C", 12)
                }, "River", 0.0},
        };
        return Arrays.asList(data);
    }

    private Hand testHand;
    private String phase;
    private List<Card> tableCards;
    private double expectedProbability;

    public ProbabilityCalculatorTest(Card[] testHandCards, Card[] tableCards, String phase, double expectedProbability)
    {
        this.testHand = new Hand(Arrays.asList(testHandCards));
        this.tableCards = Arrays.asList(tableCards);
        this.phase = phase;
        this.expectedProbability = expectedProbability;
    }

    @Test
    public void testProbabilityCalculator()
    {
        double delta = 0.05;
        int playerCount = 5;
        int iterations = 100000;
        double actualProbability = ProbabilityCalculator.calculateProbability(testHand, iterations, phase, tableCards, playerCount);
        assertThat(actualProbability, closeTo(expectedProbability, delta));
    }
}
