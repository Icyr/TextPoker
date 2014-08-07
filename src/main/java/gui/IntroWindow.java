package gui;

import util.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntroWindow
{
    public static final int WINDOW_WIDTH = 300;
    public static final int WINDOW_HEIGHT = 200;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel labelPanel;
    private JPanel inputPanel;

    private JLabel numberOfOpponentsLabel;
    private JLabel moneyInputLabel;
    private JLabel blindSizeLabel;
    private JLabel reBuyLabel;


    private JSpinner numberOfOpponentsSpinner;
    private JTextField moneyInputTextField;
    private JTextField blindSizeTextField;
    private JCheckBox reBuyCheckBox;
    private JButton okButton;

    public int moneyAmount;
    public int opponentsNumber;
    public boolean isReBuyEnabled;
    public int blindSize;
    public boolean ready = false;

    public IntroWindow()
    {
        initialize();
        measure();
        addComponentsToPanel();
    }

    private void initialize()
    {
        frame = new JFrame("Enter parameters");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
        labelPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        numberOfOpponentsLabel = new JLabel("Select number of opponents:");
        moneyInputLabel = new JLabel("Enter players' cash:");
        blindSizeLabel = new JLabel("Enter small blind size:");
        reBuyLabel = new JLabel("not implemented yet");//"I wish to rebuy if I lose all my money:");

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(4, 1, 7, 1);
        numberOfOpponentsSpinner = new JSpinner(spinnerNumberModel);
        ((JSpinner.DefaultEditor) numberOfOpponentsSpinner.getEditor()).getTextField().setEditable(false);
        moneyInputTextField = new JTextField("1000");
        moneyInputTextField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    parseParameters();
                }
            }
        });
        blindSizeTextField = new JTextField("10");
        blindSizeTextField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    parseParameters();
                }
            }
        });
        reBuyCheckBox = new JCheckBox();
        reBuyCheckBox.setEnabled(false);
        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parseParameters();
            }
        });
        okButton.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    parseParameters();
                }
            }
        });
    }

    private void parseParameters()
    {
        moneyAmount = Utils.safeParseInt(moneyInputTextField.getText());
        opponentsNumber = (Integer) numberOfOpponentsSpinner.getValue();
        blindSize = Utils.safeParseInt(blindSizeTextField.getText());
        isReBuyEnabled = reBuyCheckBox.isSelected();
        ready = true;
        frame.dispose();
    }

    private void measure()
    {
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocation(screenSize.width / 2 - WINDOW_WIDTH / 2, screenSize.height / 2 - WINDOW_HEIGHT / 2);
        frame.setResizable(false);
        mainPanel.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        labelPanel.setSize(new Dimension(WINDOW_WIDTH / 4 * 3, WINDOW_HEIGHT));
        inputPanel.setSize(new Dimension(WINDOW_WIDTH / 4, WINDOW_HEIGHT));
    }

    private void addComponentsToPanel()
    {
        labelPanel.add(numberOfOpponentsLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(moneyInputLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(blindSizeLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(reBuyLabel);
        labelPanel.add(Box.createGlue());

        inputPanel.add(numberOfOpponentsSpinner);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(moneyInputTextField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(blindSizeTextField);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        inputPanel.add(reBuyCheckBox);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        inputPanel.add(okButton);

        mainPanel.add(labelPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        mainPanel.add(inputPanel);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        frame.getRootPane().setDefaultButton(okButton);
        okButton.requestFocus();
    }
}
