package gui.presenter;

import entities.Hand;
import gui.view.OpponentView;
import util.Utils;

import javax.swing.*;

public class OpponentPresenter
{
    private OpponentView view;
    private int betAmount;

    public OpponentPresenter(OpponentView view)
    {
        this.view = view;
    }

    public void setBet(int value)
    {
        betAmount = value;
        view.setBetText(Integer.toString(value));
    }

    public void addToBet(int value)
    {
        betAmount += value;
        view.setBetText(Integer.toString(betAmount));
    }

    public void setCash(int value)
    {
        view.setCash(Integer.toString(value));
    }

    public void showCardBacks()
    {
        view.setFirstCardImage(new ImageIcon(Utils.getImage("cards/cardback.png")));
        view.setSecondCardImage(new ImageIcon(Utils.getImage("cards/cardback.png")));
    }

    public void fold()
    {
        view.setBetText("Fold");
        removeCards();
    }

    private void removeCards()
    {
        view.setFirstCardImage(null);
        view.setSecondCardImage(null);
    }

    public void showCards(Hand hand)
    {
        view.setFirstCardImage(hand.getCards().get(0).getIcon());
        view.setSecondCardImage(hand.getCards().get(1).getIcon());
    }

    public void showWin()
    {
        view.setBetText("WIN!");
    }
}
