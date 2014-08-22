package entities;

import util.Utils;

import javax.swing.*;

public class Card
{
    private final Color color;
    private final int nominal;

    public Card(Color color, int nominal)
    {
        this.color = color;
        this.nominal = nominal;
    }

    public Color getColor()
    {
        return this.color;
    }

    public int getNominal()
    {
        return this.nominal;
    }

    public ImageIcon getIcon()
    {
        return new ImageIcon(Utils.getImage("cards/" + nominal + color.getShortName() + ".png"));
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
