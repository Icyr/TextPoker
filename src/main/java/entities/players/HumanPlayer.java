package entities.players;

import entities.*;
import gui.Interface;
import logic.CombinationAnalyzer;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends AbstractPlayer
{
    private Interface gui;

    public HumanPlayer(int cash, Interface gui)
    {
        this.cash = cash;
        this.gui = gui;
        gui.setCash(cash);
    }

    public void setCurrentBet(int currentBet)
    {
        super.setCurrentBet(currentBet);
        gui.setBetAmount(currentBet);
        gui.setCash(cash);
    }

    public void addToCurrentBet(int value) throws BankruptException
    {
        super.addToCurrentBet(value);
        gui.setBetAmount(currentBet);
        gui.setCash(cash);
    }

    public void unsafeAddToCurrentBet(int value)
    {
        super.unsafeAddToCurrentBet(value);
        gui.setBetAmount(currentBet);
        gui.setCash(cash);
    }

    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount)
    {
        List<Card> playersCards = new ArrayList<Card>();
        playersCards.addAll(hand.getCards());
        playersCards.addAll(table.getCardsOnTable());
        gui.showPlayersCombination(CombinationAnalyzer.analyzeCombination(playersCards));
        String decision = gui.getDecision(currentRaise);
        if (decision.contains("raise"))
        {
            int raiseAmount = Utils.safeParseInt(decision.substring(decision.indexOf(" ") + 1, decision.length()));
            if (raiseAmount < 2 * currentRaise)
            {
                gui.displayRaiseError();
                decision = gui.getDecision(currentRaise);
            }
        }
        return  decision;
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
