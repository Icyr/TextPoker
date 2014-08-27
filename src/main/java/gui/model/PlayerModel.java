package gui.model;

import entities.Hand;
import entities.players.Player;

public class PlayerModel
{
    private String id;
    private Hand hand;
    private int currentBet;
    private int cash;
    private int number;
    private boolean isFolded;
    private boolean isAllIn;

    public PlayerModel(Player player, int index)
    {
        this.id = player.getId();
        this.hand = player.getHand();
        this.currentBet = player.getCurrentBet();
        this.cash = player.getCash();
        this.number = index;
        this.isFolded = player.isFolded();
        this.isAllIn = player.isAllIn();
    }

    public PlayerModel(String id, Hand hand, int currentBet, int cash, int number, boolean folded, boolean allIn)
    {
        this.id = id;
        this.hand = hand;
        this.currentBet = currentBet;
        this.cash = cash;
        this.number = number;
        isFolded = folded;
        isAllIn = allIn;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public void setCurrentBet(int currentBet)
    {
        this.currentBet = currentBet;
    }

    public void setCash(int cash)
    {
        this.cash = cash;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setFolded(boolean folded)
    {
        isFolded = folded;
    }

    public void setAllIn(boolean allIn)
    {
        isAllIn = allIn;
    }

    public String getId()
    {
        return id;
    }

    public Hand getHand()
    {
        return hand;
    }

    public int getCurrentBet()
    {
        return currentBet;
    }

    public int getCash()
    {
        return cash;
    }

    public int getNumber()
    {
        return number;
    }

    public boolean isFolded()
    {
        return isFolded;
    }

    public boolean isAllIn()
    {
        return isAllIn;
    }

    public String getDescription()
    {
        return "Player #" + number + "(" + id + ")";
    }
}
