package gui;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;
import gui.model.PlayerModel;
import gui.view.LogView;
import gui.view.DecisionModuleView;

import java.awt.*;
import java.util.List;
import javax.swing.*;

//todo: Move all logic to textModule and delete this class
public abstract class TextualInterface implements Interface
{
    protected JFrame frame;
    protected JPanel panel;
    protected LogView logView;
    protected DecisionModuleView decisionModuleView;
    protected JLabel combinationLabel;

    public TextualInterface()
    {
        logView = new LogView();
        decisionModuleView = new DecisionModuleView();
    }

    public void initialize()
    {
        initializeComponents();
        createTextualUI();
        addElementsToPanel();
    }

    protected void initializeComponents()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(null);

        combinationLabel = new JLabel();
    }

    private void createTextualUI()
    {
        frame.setTitle("Poker Textual Interface");
        panel.setPreferredSize(new Dimension(380, 360));
        logView.setBounds(5, 5, 200, 290);
        combinationLabel.setBounds(210, 270, 200, 30);
    }

    protected void addElementsToPanel()
    {
        logView.addToPanel(panel);
        decisionModuleView.addToPanel(panel);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void showPlayersCombination(Combination combination)
    {
        combinationLabel.setText(combination.toString());
    }

    @Override
    public void pause()
    {
        while (true) ;
    }

    @Override
    public void updatePlayersCash(List<Player> players)
    {
        logView.printlnText("Players' money:");
        for (Player player : players)
        {
            logView.printlnText(player.getCash() + " ");
        }
    }



    @Override
    public void updateTable(Table table)
    {
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                logView.printlnText("Flop:");
                break;
            case 4:
                logView.printlnText("Turn:");
                break;
            case 5:
                logView.printlnText("River:");
                break;
        }
        logView.printlnText(table.tableCardsToString());
    }

    @Override
    public void showWinnerAndHisPrize(Player player, int playerIndex, int wonAmount)
    {
        PlayerModel model = new PlayerModel(player, playerIndex);
        logView.printlnText(model.getDescription() + " won " + wonAmount);
    }

    @Override
    public void showWinnersCombination(Combination combination)
    {
        logView.printlnText("Winning combination: " + combination.toString());
    }

    @Override
    public void removeBankruptPlayer(int index)
    {
        logView.printlnText("Player " + index + " has lost all of his money!");
    }

    @Override
    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize)
    {
        logView.printlnText(firstPlayerNumber + " player bet small blind: " + blindSize);
        logView.printlnText(secondPlayerNumber + " player bet big blind: " + blindSize * 2);
    }

    @Override
    public void check(int indexOfPlayer)
    {
        logView.printlnText(indexOfPlayer + " player checked");
    }

    @Override
    public void fold(int indexOfPlayer)
    {
        logView.printlnText(indexOfPlayer + " player folded");
    }

    @Override
    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player called " + callValue;
        if (isAllIn) message += " . ALL IN!";
        logView.printlnText(message);
    }

    @Override
    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player raised " + raiseValue;
        if (isAllIn) message += " . ALL IN!";
        logView.printlnText(message);
    }

    @Override
    public void showPlayersHand(int index, Hand hand)
    {
        logView.printlnText("Player's " + index + " hand: " + hand.toString());
    }

    @Override
    public void displayRaiseError()
    {
        logView.printlnText("Invalid raise");
    }
}
