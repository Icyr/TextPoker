package gui;

import entities.Hand;
import entities.Table;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class TextualInterface implements Interface
{
<<<<<<< HEAD:src/main/java/gui/SimpleInterface.java
    JTextPane textPane;
    JFrame frame;
    JPanel panel;
    JScrollPane jsp;
    JButton foldButton;
    JButton callButton;
    JButton raiseButton;
    JTextField raiseTField;
    JLabel handText;
    JLabel handLabel;
    JLabel tableText;
    JLabel tableLabel;
    JLabel betText;
    JLabel betLabel;
    JLabel callLabel;
    JLabel bankText;
    JLabel bankLabel;
    JLabel maxBetText;
    JLabel maxBetLabel;

    String decision = "";

    public SimpleInterface()
=======
    protected JTextPane textPane;
    protected JFrame frame;
    protected JPanel panel;
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

    private String decision = "";

    public TextualInterface()
>>>>>>> 0d9641683d1c875ecc047f9edf4f217adaf3a014:src/main/java/gui/TextualInterface.java
    {
        createGUI();
    }

    private void createGUI()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        frame = new JFrame("Poker Textual Interface");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(380, 360));
        panel.setLayout(null);

        textPane = new JTextPane();
        jsp = new JScrollPane(textPane);
        foldButton = new JButton("Fold/Check");
        callButton = new JButton("Call");
        raiseButton = new JButton("Raise");
        raiseTField = new JTextField();
        handText = new JLabel("Your cards:");
        handLabel = new JLabel();
        tableText = new JLabel("Table cards:");
        tableLabel = new JLabel();
        betText = new JLabel("Your bet:");
        betLabel = new JLabel();
        bankText = new JLabel("Bank:");
        bankLabel = new JLabel();
<<<<<<< HEAD:src/main/java/gui/SimpleInterface.java
        maxBetText = new JLabel("Max bet:");
        maxBetLabel = new JLabel();
=======
        cashText = new JLabel("Your cash:");
        cashLabel = new JLabel();
        combinationLabel = new JLabel();
>>>>>>> 0d9641683d1c875ecc047f9edf4f217adaf3a014:src/main/java/gui/TextualInterface.java

        jsp.setBounds(5, 5, 200, 290);
        foldButton.setBounds(210, 5, 160, 30);
        foldButton.setEnabled(false);
        foldButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "fold";
            }
        });
        callButton.setBounds(210, 50, 160, 30);
        callButton.setEnabled(false);
        callButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "call";
            }
        });
        raiseButton.setBounds(210, 100, 100, 30);
        raiseButton.setEnabled(false);
        raiseButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "raise " + raiseTField.getText();
            }
        });
        raiseTField.setBounds(320, 100, 50, 30);
        raiseTField.setEnabled(false);
        handText.setBounds(210, 150, 100, 30);
        handLabel.setBounds(210, 170, 100, 30);
        tableText.setBounds(210, 190, 100, 30);
        tableLabel.setBounds(210, 210, 160, 30);
        betText.setBounds(210, 230, 100, 30);
        betLabel.setBounds(210, 250, 100, 30);
        bankText.setBounds(10, 300, 100, 30);
        bankLabel.setBounds(10, 320, 100, 30);
<<<<<<< HEAD:src/main/java/gui/SimpleInterface.java
        callLabel.setBounds(210, 85, 100, 30);
        maxBetText.setBounds(210, 260, 100, 30);
        maxBetLabel.setBounds(210, 280, 100, 30);
=======
        cashText.setBounds(280, 230, 100, 30);
        cashLabel.setBounds(280, 250, 100, 30);
        combinationLabel.setBounds(210, 270, 100, 30);
>>>>>>> 0d9641683d1c875ecc047f9edf4f217adaf3a014:src/main/java/gui/TextualInterface.java

        panel.add(jsp);
        panel.add(foldButton);
        panel.add(callButton);
        panel.add(raiseButton);
        panel.add(raiseTField);
        panel.add(handText);
        panel.add(handLabel);
        panel.add(tableText);
        panel.add(tableLabel);
        panel.add(betText);
        panel.add(betLabel);
        panel.add(bankText);
        panel.add(bankLabel);
<<<<<<< HEAD:src/main/java/gui/SimpleInterface.java
        panel.add(callLabel);
        panel.add(maxBetLabel);
        panel.add(maxBetText);
=======
        panel.add(cashText);
        panel.add(cashLabel);
        panel.add(combinationLabel);
>>>>>>> 0d9641683d1c875ecc047f9edf4f217adaf3a014:src/main/java/gui/TextualInterface.java

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

<<<<<<< HEAD:src/main/java/gui/SimpleInterface.java
=======
    private void prepareForPlayersTurn()
    {
        enableDecisionButtons();
        printlnText("Your turn...");
        raiseTField.setText("");
    }

>>>>>>> 0d9641683d1c875ecc047f9edf4f217adaf3a014:src/main/java/gui/TextualInterface.java
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
                Thread.sleep(500);    //wait for decision
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

<<<<<<< HEAD:src/main/java/gui/SimpleInterface.java
    public void setMaxBetLabel(int value)
    {
        maxBetLabel.setText(value + "");
=======
    public void setCallAmount(int value)
    {
        callButton.setText("Call (" + value + ")");
    }

    public void setCash(int value)
    {
        cashLabel.setText(value + "");
    }

    public void setCombination(String combination)
    {
        combinationLabel.setText(combination);
>>>>>>> 0d9641683d1c875ecc047f9edf4f217adaf3a014:src/main/java/gui/TextualInterface.java
    }
}
