package gui.model;

import entities.Card;
import entities.Table;

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

    }

    public GameModel(List<PlayerModel> playerModels, int bankValue, List<Card> tableCards, int button, int underTheGun, int blindSize, int maxBet)
    {
        this.playerModels = playerModels;
        this.bankValue = bankValue;
        this.tableCards = tableCards;
        this.button = button;
        this.underTheGun = underTheGun;
        this.blindSize = blindSize;
        this.maxBet = maxBet;
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
