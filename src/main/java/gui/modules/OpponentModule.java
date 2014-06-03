package gui.modules;

import entities.Hand;
import util.Utils;

import javax.swing.*;

public class OpponentModule
{
    private JLabel bet;
    private JLabel firstCard;
    private JLabel secondCard;
    int betAmount;

    public OpponentModule()
    {
        bet = new JLabel();
        firstCard = new JLabel();
        secondCard = new JLabel();
    }

    public void setBound(int x, int y)
    {
        firstCard.setBounds(x, y, 48, 65);
        secondCard.setBounds(x + 50, y, 48, 65);
        bet.setBounds(x, y + 70, 100, 30);
        bet.setHorizontalAlignment(SwingConstants.CENTER);
        bet.setFont(bet.getFont().deriveFont(16.0f));
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
        firstCard.setIcon(null);
        secondCard.setIcon(null);
    }

    public void showHand(Hand hand)
    {
        firstCard.setIcon(hand.getCards().get(0).getIcon());
        secondCard.setIcon(hand.getCards().get(1).getIcon());
    }
}
