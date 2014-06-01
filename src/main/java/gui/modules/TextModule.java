package gui.modules;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class TextModule
{
    private JTextPane textPane;
    private JScrollPane jsp;

    public TextModule()
    {
        textPane = new JTextPane();
        textPane.setEditable(false);
        jsp = new JScrollPane(textPane);
    }

    public void setBounds(int x, int y, int width, int height)
    {
        jsp.setBounds(x, y, width, height);
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

    public void addToPanel(JPanel panel)
    {
        panel.add(jsp);
    }
}
