package gui.modules;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecisionModule
{
    private String decision = "";

    private JButton foldButton;
    private JButton callButton;
    private JButton raiseButton;
    private JTextField raiseTField;

    public DecisionModule()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
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
    }

    public void addToPanel(JPanel panel)
    {
        panel.add(foldButton);
        panel.add(callButton);
        panel.add(raiseButton);
        panel.add(raiseTField);
    }

    public void setBounds(int[] foldCoords, int[] callCoords, int[] raiseCoords, int[] raiseTextCoords)
    {
        foldButton.setBounds(foldCoords[0], foldCoords[1], foldCoords[2], foldCoords[3]);
        callButton.setBounds(callCoords[0], callCoords[1], callCoords[2], callCoords[3]);
        raiseButton.setBounds(raiseCoords[0], raiseCoords[1], raiseCoords[2], raiseCoords[3]);
        raiseTField.setBounds(raiseTextCoords[0], raiseTextCoords[1], raiseTextCoords[2], raiseTextCoords[3]);
    }

    public String getDecision(int callValue)
    {
        raiseTField.setText("");
        callButton.setText("Call (" + callValue + ")");
        enableDecisionButtons();
        String curDecision = readDecision();
        disableDecisionButtons();
        decision = "";
        return curDecision;
    }

    private void enableDecisionButtons()
    {
        foldButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        raiseTField.setEnabled(true);
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
}
