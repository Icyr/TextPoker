package gui;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;
import gui.modules.DecisionModule;
import gui.modules.TextModule;

import java.awt.*;
import java.util.List;
import javax.swing.*;


public class TextualInterface implements Interface
{
    protected JFrame frame;
    protected JPanel panel;
    TextModule textModule;
    DecisionModule decisionModule;
    protected JLabel handText;
    protected JLabel handLabel;
    protected JLabel tableText;
    protected JLabel tableLabel;
    protected JLabel betText;
    protected JLabel betLabel;
    protected JLabel bankText;
    protected JLabel bankLabel;
    protected JLabel cashText;
    protected JLabel cashLabel;
    protected JLabel combinationLabel;

    public TextualInterface()
    {
        textModule = new TextModule();
        decisionModule = new DecisionModule();
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

        betText = new JLabel("Your bet:");
        betLabel = new JLabel();
        bankText = new JLabel("Bank:");
        bankLabel = new JLabel();
        cashText = new JLabel("Your cash:");
        cashLabel = new JLabel();
        combinationLabel = new JLabel();
    }

    private void createTextualUI()
    {
        frame.setTitle("Poker Textual Interface");
        panel.setPreferredSize(new Dimension(380, 360));

        handText = new JLabel("Your cards:");
        handLabel = new JLabel();
        tableText = new JLabel("Table cards:");
        tableLabel = new JLabel();

        textModule.setBounds(5, 5, 200, 290);
        decisionModule.setBounds(new int[]{210, 5, 160, 30},
                new int[]{210, 50, 160, 30},
                new int[]{210, 100, 100, 30},
                new int[]{320, 100, 50, 30});
        handText.setBounds(210, 150, 100, 30);
        handLabel.setBounds(210, 170, 100, 30);
        tableText.setBounds(210, 190, 100, 30);
        tableLabel.setBounds(210, 210, 160, 30);
        betText.setBounds(210, 230, 100, 30);
        betLabel.setBounds(210, 250, 100, 30);
        bankText.setBounds(10, 300, 100, 30);
        bankLabel.setBounds(10, 320, 100, 30);
        cashText.setBounds(280, 230, 100, 30);
        cashLabel.setBounds(280, 250, 100, 30);
        combinationLabel.setBounds(210, 270, 200, 30);

        panel.add(handText);
        panel.add(handLabel);
        panel.add(tableText);
        panel.add(tableLabel);

    }

    protected void addElementsToPanel()
    {
        textModule.addToPanel(panel);
        decisionModule.addToPanel(panel);
        panel.add(betText);
        panel.add(betLabel);
        panel.add(bankText);
        panel.add(bankLabel);
        panel.add(cashText);
        panel.add(cashLabel);
        panel.add(combinationLabel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public String getDecision(int callValue)
    {
        textModule.printlnText("Your turn...");
        return decisionModule.getDecision(callValue);
    }

    public void setHand(Hand hand)
    {
        handLabel.setText(hand.toString());
    }

    public void setBetAmount(int value)
    {
        betLabel.setText(value + "");
    }

    public void setBank(int value)
    {
        bankLabel.setText(value + "");
    }

    public void setCash(int value)
    {
        cashLabel.setText(value + "");
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
        textModule.printlnText("Players' money:");
        for (Player player : players)
        {
            textModule.printlnText(player.getCash() + " ");
        }
    }

    @Override
    public void deal()
    {
        textModule.printlnText("Dealing cards..");
    }

    @Override
    public void prepareForGame()
    {
        setBank(0);
        setBetAmount(0);
    }

    @Override
    public void prepareForRound()
    {
        setBetAmount(0);
        tableLabel.setText("");
    }

    @Override
    public void moveButton(int button)
    {
        textModule.printlnText("Moved button to " + button);
    }

    @Override
    public void updateTable(Table table)
    {
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                textModule.printlnText("Flop:");
                break;
            case 4:
                textModule.printlnText("Turn:");
                break;
            case 5:
                textModule.printlnText("River:");
                break;
        }
        textModule.printlnText(table.tableCardsToString());
        tableLabel.setText(table.tableCardsToString());
    }

    @Override
    public void showWinnerAndHisPrize(int playersNumber, int wonAmount)
    {
        textModule.printlnText("Player " + playersNumber + " won " + wonAmount);
    }

    @Override
    public void showWinnersCombination(Combination combination)
    {
        textModule.printlnText("Winning combination: " + combination.toString());
    }

    @Override
    public void removeBankruptPlayer(int index)
    {
        textModule.printlnText("Player " + index + " has lost all of his money!");
    }

    @Override
    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize)
    {
        textModule.printlnText(firstPlayerNumber + " player bet small blind: " + blindSize);
        textModule.printlnText(secondPlayerNumber + " player bet big blind: " + blindSize * 2);
    }

    @Override
    public void check(int indexOfPlayer)
    {
        textModule.printlnText(indexOfPlayer + " player checked");
    }

    @Override
    public void fold(int indexOfPlayer)
    {
        textModule.printlnText(indexOfPlayer + " player folded");
    }

    @Override
    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player called " + callValue;
        if (isAllIn) message += " . ALL IN!";
        textModule.printlnText(message);
    }

    @Override
    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player raised " + raiseValue;
        if (isAllIn) message += " . ALL IN!";
        textModule.printlnText(message);
    }
}
