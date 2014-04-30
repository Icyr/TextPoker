package gui;

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
    JLabel callLabel;
    JLabel bankText;
    JLabel bankLabel;

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
        panel.setPreferredSize(new Dimension(320, 360));
        panel.setLayout(null);

        textPane = new JTextPane();
        jsp = new JScrollPane(textPane);
        foldButton = new JButton("Fold");
        callButton = new JButton("Call/Check");
        raiseButton = new JButton("Raise");
        raiseTField = new JTextField();
        handText = new JLabel("Your cards:");
        handLabel = new JLabel();
        tableText = new JLabel("Table cards:");
        tableLabel = new JLabel();
        betText = new JLabel("Your bet:");
        betLabel = new JLabel();
        callLabel = new JLabel();
        bankText = new JLabel("Bank:");
        bankLabel = new JLabel();

        jsp.setBounds(5, 5, 200, 290);
        foldButton.setBounds(210, 5, 100, 30);
        foldButton.setEnabled(false);
        foldButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                decision = "fold";
            }
        });
        callButton.setBounds(210, 50, 100, 30);
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
        raiseTField.setBounds(210, 150, 100, 30);
        raiseTField.setEnabled(false);
        handText.setBounds(210, 180, 100, 30);
        handLabel.setBounds(210, 200, 100, 30);
        tableText.setBounds(70, 300, 100, 30);
        tableLabel.setBounds(70, 320, 140, 30);
        betText.setBounds(210, 220, 100, 30);
        betLabel.setBounds(210, 240, 100, 30);
        bankText.setBounds(10, 300, 100, 30);
        bankLabel.setBounds(10, 320, 100, 30);
        callLabel.setBounds(210, 85, 100, 30);

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
        panel.add(callLabel);

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

    public void disableDecisionButtons()
    {
        foldButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        raiseTField.setEnabled(false);
    }

    public String getDecision()
    {
        enableDecisionButtons();
        String curDecision = readDecision();
        disableDecisionButtons();
        decision = "";
        return curDecision;
    }

    private String readDecision()
    {
        while (decision.equals(""))
        {
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
}
