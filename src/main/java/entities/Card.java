package entities;

import javax.swing.*;

public class Card
{
    private final String color;
    private final int nominal;
    private ImageIcon icon;

    public Card(String color, int nominal)
    {
        this.color = color;
        this.nominal = nominal;
        icon = new ImageIcon(getClass().getResource("\\cards\\" + nominal + color + ".png"));
    }

    public String getColor()
    {
        return this.color;
    }

    public int getNominal()
    {
        return this.nominal;
    }

    public ImageIcon getIcon()
    {
        return icon;
    }

    public boolean equals(Object other)
    {
        if (other.getClass().equals(Card.class))
        {
            Card otherCard = (Card) other;
            return this.getColor().equals(otherCard.getColor()) && this.getNominal() == otherCard.getNominal();
        }
        return false;
    }
}
