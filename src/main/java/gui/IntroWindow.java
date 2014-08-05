package gui;

import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroWindow
{
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel opponentsNumberPanel;
    private JPanel moneyPanel;
    private JPanel buyInPanel;

    private JLabel numberOfOpponentsLabel;
    private JLabel moneyLabel;
    private JLabel abilityToBuyInLabel;

    private JSpinner opponentsSpinner;
    private JTextField moneyInput;
    private JCheckBox abilityToBuyInCheckBox;
    private JButton okButton;

    public int moneyAmount;
    public boolean ready = false;

    public IntroWindow()
    {
        initialize();
        measure();
        addComponentsToPanel();
    }

    private void initialize()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        opponentsNumberPanel = new JPanel();
        opponentsNumberPanel.setLayout(new FlowLayout());
        opponentsNumberPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        moneyPanel = new JPanel();
        moneyPanel.setLayout(new FlowLayout());
        moneyPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        buyInPanel = new JPanel();
        buyInPanel.setLayout(new FlowLayout());
        buyInPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        numberOfOpponentsLabel = new JLabel("Select number of opponents:");
        moneyLabel = new JLabel("Enter players' cash:");
        abilityToBuyInLabel = new JLabel("I wish to buy in if I lose all my money");

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(4, 1, 8, 1);
        opponentsSpinner = new JSpinner(spinnerNumberModel);
        moneyInput = new JTextField("1000");
        abilityToBuyInCheckBox = new JCheckBox();
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                moneyAmount = Utils.safeParseInt(moneyInput.getText());
                ready = true;
                frame.dispose();
            }
        });
    }

    private void measure()
    {
        mainPanel.setPreferredSize(new Dimension(300, 200));
        opponentsNumberPanel.setPreferredSize(new Dimension(250, 30));
        moneyPanel.setPreferredSize(new Dimension(250, 30));
        buyInPanel.setPreferredSize(new Dimension(250, 30));
        moneyInput.setPreferredSize(new Dimension(50, 30));
    }

    private void addComponentsToPanel()
    {
        opponentsNumberPanel.add(numberOfOpponentsLabel);
        opponentsNumberPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        opponentsNumberPanel.add(opponentsSpinner);

        moneyPanel.add(moneyLabel);
        moneyPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        moneyPanel.add(moneyInput);

        buyInPanel.add(abilityToBuyInLabel);
        buyInPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buyInPanel.add(abilityToBuyInCheckBox);

        mainPanel.add(opponentsNumberPanel);
        mainPanel.add(moneyPanel);
        mainPanel.add(buyInPanel);
        mainPanel.add(okButton);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
