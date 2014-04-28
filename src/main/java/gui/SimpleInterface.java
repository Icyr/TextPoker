package gui;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class SimpleInterface
{
    JTextPane textPane;
    JFrame frame;
    JScrollPane jsp;

    public SimpleInterface()
    {
        createGUI();
    }

    public void createGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Poker GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        jsp = new JScrollPane(textPane);
        frame.getContentPane().add(jsp);

        frame.setPreferredSize(new Dimension(800, 600));

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
}
