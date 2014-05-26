package entities.players;

import entities.*;
import gui.TextualInterface;
import logic.CombinationAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends AbstractPlayer
{
    private TextualInterface gui;

    public HumanPlayer(int cash)
    {
        this.cash = cash;
    }

    public HumanPlayer(int cash, TextualInterface gui)
    {
        this.cash = cash;
        this.gui = gui;
        gui.setCashLabel(cash);
    }

    public void setCurrentBet(int currentBet)
    {
        super.setCurrentBet(currentBet);
        gui.setBetLabel(currentBet);
        gui.setCashLabel(cash);
    }

    public void addToCurrentBet(int value) throws BankruptException
    {
        super.addToCurrentBet(value);
        gui.setBetLabel(currentBet);
        gui.setCashLabel(cash);
    }

    public void unsafeAddToCurrentBet(int value)
    {
        super.unsafeAddToCurrentBet(value);
        gui.setBetLabel(currentBet);
        gui.setCashLabel(cash);
    }

    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount)
    {
        gui.setHandLabel(hand);
        gui.setTableLabel(table);
        List<Card> playersCards = new ArrayList<Card>();
        playersCards.addAll(hand.getCards());
        playersCards.addAll(table.getCardsOnTable());
        gui.setCombinationLabel(CombinationAnalyzer.analyzeCombination(playersCards).toString());
        return gui.getDecision();
    }

    public boolean equals(Object other)
    {
        if (other.getClass().equals(HumanPlayer.class))
        {
            Player otherPlayer = (HumanPlayer) other;
            return this.getHand().getCards().get(0).equals(otherPlayer.getHand().getCards().get(0)) && this.getHand().getCards().get(1).equals(otherPlayer.getHand().getCards().get(1));
        }
        return false;
    }
}
