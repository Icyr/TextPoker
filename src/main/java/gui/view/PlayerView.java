package gui.view;

import util.Constants;

import javax.swing.*;

public class PlayerView
{
    private JLabel playersFirstCard;
    private JLabel playersSecondCard;
    private JLabel cash;
    private JLabel bet;

    public PlayerView()
    {
        playersFirstCard = new JLabel();
        playersSecondCard = new JLabel();
        cash = new JLabel();
        cash.setFont(cash.getFont().deriveFont(16.0f));
        cash.setHorizontalAlignment(SwingConstants.CENTER);
        bet = new JLabel();
        bet.setFont(bet.getFont().deriveFont(20.0f));
        bet.setHorizontalAlignment(SwingConstants.CENTER);

    }

    public void setBounds(int x, int y)
    {
        playersFirstCard.setBounds(x - Constants.CARD_WIDTH - 3, y - Constants.CARD_HEIGHT, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        playersSecondCard.setBounds(x + 3, y - Constants.CARD_HEIGHT, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        cash.setBounds(x - 25, y + 5, 50, 30);
        bet.setBounds(x - 25, y - 35 - Constants.CARD_HEIGHT, 50, 30);
    }

    public void addToPanel(JPanel panel)
    {
        panel.add(playersFirstCard);
        panel.add(playersSecondCard);
        panel.add(cash);
        panel.add(bet);
    }

    public void setPlayersFirstCard(ImageIcon icon)
    {
        playersFirstCard.setIcon(icon);
    }

    public void setPlayersSecondCard(ImageIcon icon)
    {
        playersSecondCard.setIcon(icon);
    }

    public void setCash(String value)
    {
        cash.setText(value);
    }

    public void setBet(String value)
    {
        bet.setText(value);
    }
}
