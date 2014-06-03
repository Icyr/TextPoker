package gui;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;

import java.util.List;

public interface Interface
{
    String getDecision(int callValue);

    void setBank(int i);

    void setBetAmount(int currentBet);

    void showPlayersCombination(Combination s);

    void showWinnersCombination(Combination s);

    void setCash(int cash);

    void pause();

    void updatePlayersCash(List<Player> players);

    void deal(List<Player> players);

    void prepareForGame();

    void prepareForRound();

    void moveButton(int button);

    void updateTable(Table table);

    public void initialize();

    void showWinnerAndHisPrize(int playersNumber, int wonAmount);

    void removeBankruptPlayer(int index);

    void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize);

    void check(int indexOfPlayer);

    void fold(int indexOfPlayer);

    void call(int indexOfPlayer, int callValue, boolean isAllIn);

    void raise(int indexOfPlayer, int raiseValue, boolean isAllIn);

    void showPlayersHand(int index, Hand hand);

    void zeroBets();
}
