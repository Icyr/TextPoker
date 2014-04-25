package entities;

public abstract class AbstractPlayer implements Player
{
    protected String id;
    protected Hand hand;
    protected int currentBet;
    protected int cash;
    protected boolean folded;
    protected boolean allIn;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void addToCash(int value)
    {
        cash = cash + value;
    }

    public void setAllIn(boolean allIn)
    {
        this.allIn = allIn;
    }

    public boolean isAllIn()
    {
        return allIn;
    }

    public int getCash()
    {
        return cash;
    }

    public void setCurrentBet(int currentBet)
    {
        if (currentBet <= cash)
        {
            this.currentBet = currentBet;
            this.cash = cash - currentBet;
        }
    }

    public void addToCurrentBet(int value)
    {
        if (value <= cash)
        {
            cash = cash - value;
            currentBet = currentBet + value;
        }
    }

    public boolean isFolded()
    {
        return folded;
    }

    public void setFolded(boolean folded)
    {
        this.folded = folded;
    }

    public int getCurrentBet()
    {
        return currentBet;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public Hand getHand()
    {
        return hand;
    }

    public String playersCardsToString()
    {
        String res = "";
        res += hand.getCards().get(0).getNominal() + " " + hand.getCards().get(0).getColor() + " ";
        res += hand.getCards().get(1).getNominal() + " " + hand.getCards().get(1).getColor() + " ";
        return res;
    }

    public boolean equals(Object other)
    {
        if (other.getClass().equals(AbstractPlayer.class))
        {
            AbstractPlayer otherPlayer = (AbstractPlayer) other;
            return this.getHand().getCards().get(0).equals(otherPlayer.getHand().getCards().get(0)) && this.getHand().getCards().get(1).equals(otherPlayer.getHand().getCards().get(1));
        }
        return false;
    }
}
