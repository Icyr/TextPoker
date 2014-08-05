package gui.modules;

import entities.Hand;
import util.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OpponentModule
{
    private JLabel bet;
    private JLabel firstCard;
    private JLabel secondCard;
    private JLabel cash;
    int betAmount;
    int cashAmount;

    public OpponentModule()
    {
        bet = new JLabel();
        cash = new JLabel();
        firstCard = new JLabel();
        secondCard = new JLabel();
    }

    public void setBound(int x, int y)
    {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        firstCard.setBounds(x, y, 47, 66);
        firstCard.setBorder(border);
        secondCard.setBounds(x + 50, y, 47, 66);
        secondCard.setBorder(border);
        bet.setBounds(x, y + 70, 100, 30);
        bet.setHorizontalAlignment(SwingConstants.CENTER);
        bet.setFont(bet.getFont().deriveFont(16.0f));
        cash.setBounds(x, y - 25, 100, 30);
        cash.setHorizontalAlignment(SwingConstants.CENTER);
        cash.setFont(cash.getFont().deriveFont(12.0f));
    }


    public void showHiddenHand()
    {
        firstCard.setIcon(new ImageIcon(Utils.getImage("cards/cardback.png")));
        secondCard.setIcon(new ImageIcon(Utils.getImage("cards/cardback.png")));
    }

    public void addToPanel(JPanel panel)
    {
        panel.add(firstCard);
        panel.add(secondCard);
        panel.add(bet);
        panel.add(cash);
    }

    public void setBet(int value)
    {
        betAmount = value;
        bet.setText(value + "");
    }

    public void addToBet(int value)
    {
        betAmount += value;
        bet.setText(betAmount + "");
    }

    public void fold()
    {
        bet.setText("Fold");
        hideCards();
    }

    private void hideCards()
    {
        firstCard.setIcon(null);
        secondCard.setIcon(null);
    }

    public void showHand(Hand hand)
    {
        firstCard.setIcon(hand.getCards().get(0).getIcon());
        secondCard.setIcon(hand.getCards().get(1).getIcon());
    }

    public void removeFromPanel(JPanel panel)
    {
        hideCards();
        bet.setText("Bankrupt");
        /*panel.remove(bet);
        panel.remove(firstCard);
        panel.remove(secondCard);
        panel.remove(cash);*/
    }

    public void setCash(int value)
    {
        cash.setText(value + "");
    }

    public void showWin()
    {
        bet.setText("WIN!");
    }
}
