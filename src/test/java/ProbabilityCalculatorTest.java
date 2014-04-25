import entities.Card;
import entities.Hand;
import junit.framework.Assert;
import logic.ProbabilityCalculator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProbabilityCalculatorTest
{
    private double delta = 0.02;

    @Test
    public void testProbabilityCalculatorForPreflop()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 3));
        cards.add(new Card("H", 4));
        Hand testHand = new Hand(cards);
        double probability = ProbabilityCalculator.calculateProbability(testHand, 100000, "Preflop", Collections.EMPTY_LIST, 5);
        System.out.println(probability);
        Assert.assertEquals(0.19, probability, delta);
    }

    @Test
    public void testProbabilityCalculatorForFlop()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 3));
        cards.add(new Card("H", 4));
        List<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card("H", 5));
        cards1.add(new Card("H", 2));
        cards1.add(new Card("S", 13));
        Hand testHand = new Hand(cards);
        double probability = ProbabilityCalculator.calculateProbability(testHand, 100000, "Flop", cards1, 5);
        System.out.println(probability);
        Assert.assertEquals(0.46, probability, delta);
    }

    @Test
    public void testProbabilityCalculatorForTurn()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 3));
        cards.add(new Card("H", 4));
        List<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card("H", 5));
        cards1.add(new Card("H", 2));
        cards1.add(new Card("S", 13));
        cards1.add(new Card("C", 13));
        Hand testHand = new Hand(cards);
        double probability = ProbabilityCalculator.calculateProbability(testHand, 100000, "Turn", cards1, 5);
        System.out.println(probability);
        Assert.assertEquals(0.27, probability, delta);
    }

    @Test
    public void testProbabilityCalculatorForRiver()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 3));
        cards.add(new Card("H", 4));
        List<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card("H", 5));
        cards1.add(new Card("H", 2));
        cards1.add(new Card("S", 13));
        cards1.add(new Card("C", 13));
        cards1.add(new Card("C", 12));
        Hand testHand = new Hand(cards);
        double res = ProbabilityCalculator.calculateProbability(testHand, 100000, "River", cards1, 5);
        Assert.assertEquals(0.0, res);
    }
}
