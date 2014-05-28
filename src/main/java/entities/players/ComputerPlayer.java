package entities.players;

import entities.Card;
import entities.Hand;
import entities.Table;
import logic.DecisionMaker;
import logic.ProbabilityCalculator;

import java.util.List;

public class ComputerPlayer extends AbstractPlayer
{
    private final int probabilityPrecision = 10000;
    private DecisionMaker brain;

    public ComputerPlayer(int cash, double risk, double bluff)
    {
        this.cash = cash;
        brain = new DecisionMaker(risk, bluff);
        folded = false;
        allIn = false;
    }

    public ComputerPlayer()
    {
        this.cash = 0;
        brain = new DecisionMaker(0.0, 0.0);
        folded = false;
        allIn = false;
    }

    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount)
    {
        List<Card> cardsOnTable = table.getCardsOnTable();
        double probability = 0.0;
        switch (cardsOnTable.size())
        {
            case 0:
                probability = ProbabilityCalculator.calculateProbability(hand, probabilityPrecision, "Preflop", cardsOnTable, playersCount);
                break;
            case 3:
                probability = ProbabilityCalculator.calculateProbability(hand, probabilityPrecision, "Flop", cardsOnTable, playersCount);
                break;
            case 4:
                probability = ProbabilityCalculator.calculateProbability(hand, probabilityPrecision, "Turn", cardsOnTable, playersCount);
                break;
            case 5:
                probability = ProbabilityCalculator.calculateProbability(hand, probabilityPrecision, "River", cardsOnTable, playersCount);
                break;
        }
        return brain.makeDecision(probability, bank, currentRaise, blindSize, cash);
    }

    public boolean equals(Object other)
    {
        if (other.getClass().equals(ComputerPlayer.class))
        {
            Player otherPlayer = (ComputerPlayer) other;
            return this.getHand().getCards().get(0).equals(otherPlayer.getHand().getCards().get(0)) && this.getHand().getCards().get(1).equals(otherPlayer.getHand().getCards().get(1));
        }
        return false;
    }
}
