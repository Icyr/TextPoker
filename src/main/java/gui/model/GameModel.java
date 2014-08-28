package gui.model;

import entities.Card;
import entities.Table;

import java.util.ArrayList;
import java.util.List;

public class GameModel
{
    List<PlayerModel> playerModels;
    private int bankValue;
    private List<Card> tableCards;
    private int button;
    private int underTheGun;
    private int blindSize;
    private int maxBet;

    public GameModel()
    {
        this.playerModels = new ArrayList<PlayerModel>();
        this.bankValue = 0;
        this.tableCards = new ArrayList<Card>();
        this.button = 0;
        this.underTheGun = 0;
        this.blindSize = 0;
        this.maxBet = 0;
    }

    public GameModel(int button, int blindSize)
    {
        this.playerModels = new ArrayList<PlayerModel>();
        this.bankValue = 0;
        this.tableCards = new ArrayList<Card>();
        this.button = button;
        this.underTheGun = 0;
        this.blindSize = blindSize;
        this.maxBet = 0;
    }

    public void setPlayerModels(List<PlayerModel> playerModels)
    {
        this.playerModels = playerModels;
    }

    public void setTableCards(List<Card> tableCards)
    {
        this.tableCards = tableCards;
    }

    public void setButton(int button)
    {
        this.button = button;
    }

    public void setUnderTheGun(int underTheGun)
    {
        this.underTheGun = underTheGun;
    }

    public void setBlindSize(int blindSize)
    {
        this.blindSize = blindSize;
    }

    public void setMaxBet(int maxBet)
    {
        this.maxBet = maxBet;
    }

    public void setBankValue(int bankValue)
    {
        this.bankValue = bankValue;
    }

    public int getBankValue()
    {
        return bankValue;
    }

    public List<PlayerModel> getPlayerModels()
    {
        return playerModels;
    }

    public List<Card> getTableCards()
    {
        return tableCards;
    }

    public int getButton()
    {
        return button;
    }

    public int getUnderTheGun()
    {
        return underTheGun;
    }

    public int getBlindSize()
    {
        return blindSize;
    }

    public int getMaxBet()
    {
        return maxBet;
    }
}
