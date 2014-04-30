package entities;

import gui.SimpleInterface;

public class HumanPlayer extends AbstractPlayer
{
    private SimpleInterface gui;

    public HumanPlayer(int cash)
    {
        this.cash = cash;
    }

    public HumanPlayer(int cash, SimpleInterface gui)
    {
        this.cash = cash;
        this.gui = gui;
    }

    public void setCurrentBet(int currentBet)
    {
        super.setCurrentBet(currentBet);
        gui.setBetLabel(currentBet);
    }

    public void addToCurrentBet(int value) throws BankruptException
    {
        super.addToCurrentBet(value);
        gui.setBetLabel(currentBet);
    }

    public void unsafeAddToCurrentBet(int value)
    {
        super.unsafeAddToCurrentBet(value);
        gui.setBetLabel(currentBet);
    }

    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount)
    {
        gui.setHandLabel(hand);
        gui.setTableLabel(table);
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
