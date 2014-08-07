package gui.view;

import util.Constants;

import javax.swing.*;

public class TableView
{
    private JLabel firstTableCard;
    private JLabel secondTableCard;
    private JLabel thirdTableCard;
    private JLabel forthTableCard;
    private JLabel fifthTableCard;
    private JLabel bank;

    public TableView()
    {
        firstTableCard = new JLabel();
        secondTableCard = new JLabel();
        thirdTableCard = new JLabel();
        forthTableCard = new JLabel();
        fifthTableCard = new JLabel();
        bank = new JLabel();
        bank.setFont(bank.getFont().deriveFont(20.0f));
        bank.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void addToPanel(JPanel panel)
    {
        panel.add(firstTableCard);
        panel.add(secondTableCard);
        panel.add(thirdTableCard);
        panel.add(forthTableCard);
        panel.add(fifthTableCard);
        panel.add(bank);
    }

    public void setBounds(int x, int y)
    {
        firstTableCard.setBounds(x - 5 * Constants.CARD_WIDTH / 2 - 10, y - Constants.CARD_HEIGHT / 2, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        secondTableCard.setBounds(x - 3 * Constants.CARD_WIDTH / 2 - 5, y - Constants.CARD_HEIGHT / 2, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        thirdTableCard.setBounds(x - Constants.CARD_WIDTH / 2, y - Constants.CARD_HEIGHT / 2, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        forthTableCard.setBounds(x + Constants.CARD_WIDTH / 2 + 5, y - Constants.CARD_HEIGHT / 2, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        fifthTableCard.setBounds(x + 3 * Constants.CARD_WIDTH / 2 + 10, y - Constants.CARD_HEIGHT / 2, Constants.CARD_WIDTH, Constants.CARD_HEIGHT);
        bank.setBounds(x - 50, y + Constants.CARD_HEIGHT / 2 + 5, 100, 30);
    }

    public void setFirstTableCard(ImageIcon icon)
    {
        firstTableCard.setIcon(icon);
    }

    public void setSecondTableCard(ImageIcon icon)
    {
        secondTableCard.setIcon(icon);
    }

    public void setThirdTableCard(ImageIcon icon)
    {
        thirdTableCard.setIcon(icon);
    }

    public void setForthTableCard(ImageIcon icon)
    {
        forthTableCard.setIcon(icon);
    }

    public void setFifthTableCard(ImageIcon icon)
    {
        fifthTableCard.setIcon(icon);
    }

    public void setBank(String value)
    {
        bank.setText(value);
    }
}
