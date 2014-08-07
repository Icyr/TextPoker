package gui.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OpponentView
{
    private JLabel bet;
    private JLabel firstCard;
    private JLabel secondCard;
    private JLabel cash;

    public OpponentView()
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

    public void addToPanel(JPanel panel)
    {
        panel.add(firstCard);
        panel.add(secondCard);
        panel.add(bet);
        panel.add(cash);
    }

    public void setFirstCardImage(ImageIcon icon)
    {
        firstCard.setIcon(icon);
    }

    public void setSecondCardImage(ImageIcon icon)
    {
        secondCard.setIcon(icon);
    }

    public void setBetText(String value)
    {
        bet.setText(value);
    }

    public void setCash(String value)
    {
        cash.setText(value);
    }

    public void dispose()
    {
        bet.setVisible(false);
        firstCard.setVisible(false);
        secondCard.setVisible(false);
        cash.setVisible(false);
    }
}
