package gui;

import entities.Hand;
import entities.Table;

import javax.swing.*;
import java.awt.*;

public class GraphicalInterface extends TextualInterface implements Interface
{
    JLabel firstTableCard;
    JLabel secondTableCard;
    JLabel thirdTableCard;
    JLabel forthTableCard;
    JLabel fifthTableCard;
    JLabel playersFirstCard;
    JLabel playersSecondCard;
    JLabel combinationText;

    public GraphicalInterface()
    {

    }

    public void initialize()
    {
        initializeComponents();
        createGraphicalUI();
        addElementsToPanel();
    }

    private void createGraphicalUI()
    {
        frame.setTitle("Poker Graphical Interface");
        panel.setPreferredSize(new Dimension(640, 480));

        textModule.setBounds(430, 10, 200, 350);
        decisionModule.setBounds(new int[]{10, 370, 150, 30},
                new int[]{165, 370, 150, 30},
                new int[]{320, 370, 150, 30},
                new int[]{475, 370, 150, 30});
        combinationText.setBounds(10, 450, 100, 30);
        combinationLabel.setBounds(120, 450, 200, 30);
        cashText.setBounds(500, 450, 100, 30);
        cashLabel.setBounds(600, 450, 30, 30);
        betLabel.setBounds(198, 240, 100, 30);
        bankLabel.setBounds(198, 120, 100, 30);

        playersFirstCard.setBounds(165, 270, 48, 65);
        playersSecondCard.setBounds(223, 270, 48, 65);
        firstTableCard.setBounds(88, 160, 48, 65);
        secondTableCard.setBounds(141, 160, 48, 65);
        thirdTableCard.setBounds(194, 160, 48, 65);
        forthTableCard.setBounds(247, 160, 48, 65);
        fifthTableCard.setBounds(300, 160, 48, 65);
    }

    protected void initializeComponents()
    {
        super.initializeComponents();
        combinationText = new JLabel("Your combination:");
        playersFirstCard = new JLabel();
        playersSecondCard = new JLabel();
        firstTableCard = new JLabel();
        secondTableCard = new JLabel();
        thirdTableCard = new JLabel();
        forthTableCard = new JLabel();
        fifthTableCard = new JLabel();
    }

    protected void addElementsToPanel()
    {
        super.addElementsToPanel();
        panel.add(combinationText);
        panel.add(playersFirstCard);
        panel.add(playersSecondCard);
        panel.add(firstTableCard);
        panel.add(secondTableCard);
        panel.add(thirdTableCard);
        panel.add(forthTableCard);
        panel.add(fifthTableCard);
    }

    @Override
    public void setHand(Hand hand)
    {
        playersFirstCard.setIcon(hand.getCards().get(0).getIcon());
        playersSecondCard.setIcon(hand.getCards().get(1).getIcon());
    }

    @Override
    public void updateTable(Table table)
    {
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                firstTableCard.setIcon(table.getCardsOnTable().get(0).getIcon());
                secondTableCard.setIcon(table.getCardsOnTable().get(1).getIcon());
                thirdTableCard.setIcon(table.getCardsOnTable().get(2).getIcon());
                break;
            case 4:
                firstTableCard.setIcon(table.getCardsOnTable().get(0).getIcon());
                secondTableCard.setIcon(table.getCardsOnTable().get(1).getIcon());
                thirdTableCard.setIcon(table.getCardsOnTable().get(2).getIcon());
                forthTableCard.setIcon(table.getCardsOnTable().get(3).getIcon());
                break;
            case 5:
                firstTableCard.setIcon(table.getCardsOnTable().get(0).getIcon());
                secondTableCard.setIcon(table.getCardsOnTable().get(1).getIcon());
                thirdTableCard.setIcon(table.getCardsOnTable().get(2).getIcon());
                forthTableCard.setIcon(table.getCardsOnTable().get(3).getIcon());
                fifthTableCard.setIcon(table.getCardsOnTable().get(4).getIcon());
                break;
        }
    }

    @Override
    public void prepareForRound()
    {
        super.prepareForRound();
        playersFirstCard.setIcon(null);
        playersSecondCard.setIcon(null);
        firstTableCard.setIcon(null);
        secondTableCard.setIcon(null);
        thirdTableCard.setIcon(null);
        forthTableCard.setIcon(null);
        fifthTableCard.setIcon(null);
    }
}
