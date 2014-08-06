package gui.modules;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecisionModule
{
    private String decision = "";

    private JPanel decisionPanel;
    private JPanel raisePanel;

    private JButton foldButton;
    private JButton callButton;
    private JButton raiseButton;
    private JTextField raiseTField;
    private JSlider raiseSlider;

    public DecisionModule()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {
            System.out.println(e.toString());
        }
        decisionPanel = new JPanel();
        decisionPanel.setLayout(new GridLayout(2, 2, 10, 10));
        raisePanel = new JPanel();
        raisePanel.setLayout(new BoxLayout(raisePanel, BoxLayout.PAGE_AXIS));
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
        raiseSlider = new JSlider(JSlider.HORIZONTAL);
        raiseSlider.setEnabled(false);
        raiseSlider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                raiseTField.setText(Integer.toString(raiseSlider.getValue()));
            }
        });
    }

    public void addToPanel(JPanel panel)
    {
        raisePanel.add(raiseTField);
        raisePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        raisePanel.add(raiseSlider);
        decisionPanel.add(foldButton);
        decisionPanel.add(callButton);
        decisionPanel.add(raiseButton);
        decisionPanel.add(raisePanel);
        panel.add(decisionPanel);
    }

    public void setBounds(int x, int y, int frameWidth, int frameHeight)
    {
        decisionPanel.setBounds(x, y, frameWidth / 2 - 15, frameHeight / 4);
    }

    public String getDecision(int callValue, int cash)
    {
        raiseTField.setText("");
        callButton.setText("Call (" + callValue + ")");
        raiseSlider.setMinimum(callValue * 2);
        raiseSlider.setMaximum(cash);
        raiseSlider.setValue(callValue * 2);
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
        raiseSlider.setEnabled(true);
    }

    private void disableDecisionButtons()
    {
        foldButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        raiseTField.setEnabled(false);
        raiseSlider.setEnabled(false);
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
