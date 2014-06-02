package gui.modules;

import util.Utils;

import javax.swing.*;

public class OpponentModule
{
    private int index;
    private JLabel bet;
    private JLabel firstCard;
    private JLabel secondCard;

    public OpponentModule(int index)
    {
        this.index = index;
        bet = new JLabel();
        firstCard = new JLabel();
        secondCard = new JLabel();
    }

    public void setBound(int x, int y)
    {
        firstCard.setBounds(x, y, 48, 65);
        secondCard.setBounds(x + 50, y, 48, 65);
        bet.setBounds(x + 49, y + 70, 100, 30);
        bet.setHorizontalAlignment(SwingConstants.CENTER);
        bet.setFont(bet.getFont().deriveFont(32.0f));
    }


    public void showHiddenHand()
    {
        firstCard.setIcon(new ImageIcon(Utils.getImage("cards/cardback.png")));
        secondCard.setIcon(new ImageIcon(Utils.getImage("cards/cardback.png")));
    }
}
