package gui;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;

import java.util.List;

public interface Interface
{
    String getDecision();

    void setBank(int i);

    void printlnText(String s);

    void setCallAmount(int callValue);

    void setBetAmount(int currentBet);

    void showCombination(Combination s);

    void setHand(Hand hand);

    void setTable(Table table);

    void setCash(int cash);

    void pause();

    void updatePlayersCash(List<Player> players);

    void deal();

    void prepareForGame();

    void prepareForRound();

    void moveButton(int button);

    void updateTable(Table table);

    public void initialize();
}
