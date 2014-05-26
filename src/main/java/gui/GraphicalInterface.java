package gui;

import javax.swing.*;

public class GraphicalInterface extends TextualInterface implements Interface
{
    JLabel firstTableCard;
    JLabel secondTableCard;
    JLabel thirdTableCard;
    JLabel forthTableCard;
    JLabel fifthTableCard;
    JLabel playersFirstCard;
    JLabel playersSecondCard;
    public GraphicalInterface(int numberOfPlayers)
    {
        createGUI(numberOfPlayers);
    }

    public GraphicalInterface()
    {
        createGUI(5);
    }

    private void createGUI(int numberOfPlayers)
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


    }
}
