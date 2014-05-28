package gui;

import entities.Hand;
import entities.Table;

public interface Interface
{

    void setBank(int i);

    void printlnText(String s);

    void setCallAmount(int callValue);

    void setBetAmount(int currentBet);

    String getDecision();

    void setCombination(String s);

    void setHand(Hand hand);

    void setTable(Table table);

    void setCash(int cash);
}
