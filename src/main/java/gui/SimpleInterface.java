package gui;

import entities.Combination;
import entities.Hand;
import entities.Table;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class SimpleInterface
{
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
    JLabel bankText;
    JLabel bankLabel;
    JLabel cashText;
    JLabel cashLabel;
    JLabel combinationLabel;


    String decision = "";

    public SimpleInterface()
    {
        createGUI();
    }

    public void createGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Poker GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        cashText = new JLabel("Your cash:");
        cashLabel = new JLabel();
        combinationLabel = new JLabel();

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
        tableText.setBounds(70, 300, 100, 30);
        tableLabel.setBounds(70, 320, 140, 30);
        betText.setBounds(210, 190, 100, 30);
        betLabel.setBounds(210, 210, 100, 30);
        bankText.setBounds(10, 300, 100, 30);
        bankLabel.setBounds(10, 320, 100, 30);
        cashText.setBounds(280, 190, 100, 30);
        cashLabel.setBounds(280, 210, 100, 30);
        combinationLabel.setBounds(210,230,100,30);

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
        panel.add(cashText);
        panel.add(cashLabel);
        panel.add(combinationLabel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void printText(String newText)
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

    public void enableDecisionButtons()
    {
        foldButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        raiseTField.setEnabled(true);
    }

    public void prepareForPlayersTurn()
    {
        enableDecisionButtons();
        printText("Your turn...");
        raiseTField.setText("");
    }

    public void disableDecisionButtons()
    {
        foldButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        raiseTField.setEnabled(false);
    }

    public String getDecision()
    {
        prepareForPlayersTurn();
        String curDecision = readDecision();
        disableDecisionButtons();
        decision = "";
        return curDecision;
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

    public void setHandLabel(Hand hand)
    {
        handLabel.setText(hand.toString());
    }

    public void setTableLabel(Table table)
    {
        tableLabel.setText(table.tableCardsToString());
    }

    public void setBetLabel(int value)
    {
        betLabel.setText(value + "");
    }

    public void setBankLabel(int value)
    {
        bankLabel.setText(value + "");
    }

    public void setCallAmount(int value)
    {
        callButton.setText("Call (" + value + ")");
    }

    public void setCashLabel(int value)
    {
        cashLabel.setText(value + "");
    }

    public void setCombinationLabel(Combination combination)
    {
        combinationLabel.setText(combination.toString());
    }
}
