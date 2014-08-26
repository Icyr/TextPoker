package gui.view;

import entities.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecisionModuleView
{
    private String decision = "";

    private JPanel decisionPanel;
    private JPanel raisePanel;
    private JPanel raiseAmountButtonsPanel;
    private JPanel raiseSliderPanel;

    private JButton foldButton;
    private JButton callButton;
    private JButton raiseButton;
    private JTextField raiseTField;
    private JSlider raiseSlider;
    private JButton smallRaiseAmountButton;
    private JButton mediumRaiseAmountButton;
    private JButton highRaiseAmountButton;

    RaiseButtonActionListener smallRaiseButtonActionListener;
    RaiseButtonActionListener mediumRaiseButtonActionListener;
    RaiseButtonActionListener highRaiseButtonActionListener;

    public DecisionModuleView()
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
        raiseAmountButtonsPanel = new JPanel();
        raiseAmountButtonsPanel.setLayout(new GridLayout(1, 3, 5, 0));
        raiseSliderPanel = new JPanel();
        raiseSliderPanel.setLayout(new GridLayout(1, 2, 5, 0));
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
        raiseSlider.setPaintTicks(true);
        raiseSlider.setPaintTrack(true);
        raiseSlider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                raiseTField.setText(Integer.toString(raiseSlider.getValue()));
            }
        });
        smallRaiseAmountButton = new JButton("1BB");
        mediumRaiseAmountButton = new JButton("2BB");
        highRaiseAmountButton = new JButton("3BB");
        smallRaiseButtonActionListener = new RaiseButtonActionListener();
        mediumRaiseButtonActionListener = new RaiseButtonActionListener();
        highRaiseButtonActionListener = new RaiseButtonActionListener();
        smallRaiseAmountButton.addActionListener(smallRaiseButtonActionListener);
        mediumRaiseAmountButton.addActionListener(mediumRaiseButtonActionListener);
        highRaiseAmountButton.addActionListener(highRaiseButtonActionListener);
    }

    public void addToPanel(JPanel panel)
    {
        raiseAmountButtonsPanel.add(smallRaiseAmountButton);
        raiseAmountButtonsPanel.add(mediumRaiseAmountButton);
        raiseAmountButtonsPanel.add(highRaiseAmountButton);

        raiseSliderPanel.add(raiseTField);
        raiseSliderPanel.add(raiseSlider);

        raisePanel.add(raiseAmountButtonsPanel);
        raisePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        raisePanel.add(raiseSliderPanel);

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

    public void configureRaiseSlider(int callValue, int cash)
    {
        raiseSlider.setMinimum(callValue * 2);
        raiseSlider.setMaximum(cash);
        raiseSlider.setMajorTickSpacing((cash - callValue * 2) / 2);
        raiseSlider.setMinorTickSpacing((cash - callValue * 2) / 4);
        raiseSlider.setValue(callValue * 2);
    }

    public void configureRaiseButtons(int tableCardCount, int bankSize)
    {
        int smallAmount;
        int mediumAmount;
        int highAmount;
        switch (tableCardCount)
        {
            case 0:
            {
                smallRaiseAmountButton.setText("2BB");
                mediumRaiseAmountButton.setText("3BB");
                highRaiseAmountButton.setText("4BB");
                smallAmount = 4 * Game.blindSize;
                mediumAmount = 6 * Game.blindSize;
                highAmount = 8 * Game.blindSize;
                break;
            }
            default:
            {
                smallAmount = bankSize;
                mediumAmount = 2 * bankSize;
                highAmount = 3 * bankSize;
                smallRaiseAmountButton.setText(Integer.toString(smallAmount));
                mediumRaiseAmountButton.setText(Integer.toString(mediumAmount));
                highRaiseAmountButton.setText(Integer.toString(highAmount));
                break;
            }
        }
        smallRaiseButtonActionListener.setAmount(smallAmount);
        mediumRaiseButtonActionListener.setAmount(mediumAmount);
        highRaiseButtonActionListener.setAmount(highAmount);
    }

    public void enableDecisionButtons()
    {
        foldButton.setEnabled(true);
        callButton.setEnabled(true);
        raiseButton.setEnabled(true);
        raiseTField.setEnabled(true);
        raiseSlider.setEnabled(true);
    }

    public void disableDecisionButtons()
    {
        foldButton.setEnabled(false);
        callButton.setEnabled(false);
        raiseButton.setEnabled(false);
        raiseTField.setEnabled(false);
        raiseSlider.setEnabled(false);
    }

    public String readDecision()
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
        String result = decision;
        decision = "";
        return result;
    }

    public void setCallButtonValue(int callValue)
    {
        callButton.setText("Call (" + callValue + ")");
    }

    public void disableRaiseButton()
    {
        raiseButton.setEnabled(false);
    }

    public void setRaiseFieldValue(int value)
    {
        raiseTField.setText(Integer.toString(value));
    }

    class RaiseButtonActionListener implements ActionListener
    {
        private int amount;

        RaiseButtonActionListener()
        {
            this.amount = 0;
        }

        public void setAmount(int value)
        {
            this.amount = value;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            raiseTField.setText(Integer.toString(amount));
        }
    }
}
