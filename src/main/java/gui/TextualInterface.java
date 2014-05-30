package gui;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class TextualInterface implements Interface
{
    protected JFrame frame;
    protected JPanel panel;
    protected JTextPane textPane;
    protected JScrollPane jsp;
    protected JButton foldButton;
    protected JButton callButton;
    protected JButton raiseButton;
    protected JTextField raiseTField;
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

    protected String decision = "";

    public TextualInterface()
    {
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

        textPane = new JTextPane();
        textPane.setEditable(false);
        jsp = new JScrollPane(textPane);
        foldButton = new JButton("Fold/Check");
        foldButton.setEnabled(false);
        foldButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "fold";
            }
        });
        callButton = new JButton("Call");
        callButton.setEnabled(false);
        callButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "call";
            }
        });
        raiseButton = new JButton("Raise");
        raiseButton.setEnabled(false);
        raiseButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "raise " + raiseTField.getText();
            }
        });
        raiseTField = new JTextField();
        raiseTField.setEnabled(false);
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

        jsp.setBounds(5, 5, 200, 290);
        foldButton.setBounds(210, 5, 160, 30);
        callButton.setBounds(210, 50, 160, 30);
        raiseButton.setBounds(210, 100, 100, 30);
        raiseTField.setBounds(320, 100, 50, 30);
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
        panel.add(jsp);
        panel.add(foldButton);
        panel.add(callButton);
        panel.add(raiseButton);
        panel.add(raiseTField);
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

    public void printlnText(String newText)
    {
        Document doc = textPane.getDocument();
        try
        {
            doc.insertString(doc.getLength(), newText + "\n", null);
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
        textPane.setCaretPosition(doc.getLength());
    }

    private void enableDecisionButtons()
    {
        foldButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        raiseTField.setEnabled(true);
    }

    private void prepareForPlayersTurn()
    {
        enableDecisionButtons();
        printlnText("Your turn...");
        raiseTField.setText("");
    }

    private void disableDecisionButtons()
    {
        foldButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        raiseTField.setEnabled(false);
    }

    private String readDecision()
    {
        while (decision.equals(""))
        {
            try
            {
                //wait for decision
                Thread.sleep(500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return decision;
    }

    public String getDecision()
    {
        prepareForPlayersTurn();
        String curDecision = readDecision();
        disableDecisionButtons();
        decision = "";
        return curDecision;
    }

    public void setHand(Hand hand)
    {
        handLabel.setText(hand.toString());
    }

    public void setTable(Table table)
    {
        tableLabel.setText(table.tableCardsToString());
    }

    public void setBetAmount(int value)
    {
        betLabel.setText(value + "");
    }

    public void setBank(int value)
    {
        bankLabel.setText(value + "");
    }

    public void setCallAmount(int value)
    {
        callButton.setText("Call (" + value + ")");
    }

    public void setCash(int value)
    {
        cashLabel.setText(value + "");
    }

    public void showCombination(Combination combination)
    {
        combinationLabel.setText(combination.toString());
    }

    @Override
    public void pause()
    {
        while (true)
        {
        }
    }

    @Override
    public void updatePlayersCash(List<Player> players)
    {
        printlnText("Players' money:");
        for (Player player : players)
        {
            printlnText(player.getCash() + " ");
        }
    }

    @Override
    public void deal()
    {
        printlnText("Dealing cards..");
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
    }

    @Override
    public void moveButton(int button)
    {
        printlnText("Moved button to " + button);
    }

    @Override
    public void updateTable(Table table)
    {
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                printlnText("Flop:");
                break;
            case 4:
                printlnText("Turn:");
                break;
            case 5:
                printlnText("River:");
                break;
        }
        printlnText(table.tableCardsToString());
    }
}
