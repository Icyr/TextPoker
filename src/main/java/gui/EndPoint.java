package gui;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;

import java.util.List;

public interface EndPoint
{
    public void initialize();

    public void prepareForGame(List<Player> players);

    public void prepareForRound();

    public void moveButton(int button);

    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize);

    public void deal(List<Player> players);

    public String getDecision(int callValue, int cash, int tableCardCount);

    public void check(int indexOfPlayer);

    public void fold(int indexOfPlayer);

    public void call(int indexOfPlayer, int callValue, boolean isAllIn);

    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn);

    public void setBank(int value);

    public void setBetAmount(int currentBet);

    public void setCash(int cash);

    public void updateTable(Table table);

    public void updatePlayersCash(List<Player> players);

    public void showWinnersCombination(Combination s);

    public void showWinnerAndHisPrize(Player player, int playerIndex, int wonAmount);

    public void showPlayersHand(int index, Hand hand);

    public void removeBankruptPlayer(int index);

    public void zeroBets();

    public void displayRaiseError();

    public void pause();
}
