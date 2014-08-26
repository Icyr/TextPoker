package entities.players;

import entities.BankruptException;
import entities.Hand;
import entities.Table;
import gui.EndPoint;
import util.Utils;

public class HumanPlayer extends AbstractPlayer
{
    private EndPoint endPoint;

    public HumanPlayer(int cash, EndPoint endPoint)
    {
        this.cash = cash;
        this.endPoint = endPoint;
        endPoint.setCash(cash);
    }

    public void setCurrentBet(int currentBet)
    {
        super.setCurrentBet(currentBet);
        endPoint.setBetAmount(currentBet);
        endPoint.setCash(cash);
    }

    public void addToCurrentBet(int value) throws BankruptException
    {
        super.addToCurrentBet(value);
        endPoint.setBetAmount(currentBet);
        endPoint.setCash(cash);
    }

    public void unsafeAddToCurrentBet(int value)
    {
        super.unsafeAddToCurrentBet(value);
        endPoint.setBetAmount(currentBet);
        endPoint.setCash(cash);
    }

    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount)
    {

        String decision = endPoint.getDecision(currentRaise, cash, table.getCardsOnTable().size());
        if (decision.contains("raise"))
        {
            int raiseAmount = Utils.safeParseInt(decision.substring(decision.indexOf(" ") + 1, decision.length()));
            if (raiseAmount < 2 * currentRaise)
            {
                endPoint.displayRaiseError();
                decision = endPoint.getDecision(currentRaise, cash, table.getCardsOnTable().size());
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
