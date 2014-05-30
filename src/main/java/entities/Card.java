package entities;

import util.Utils;

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
        icon = new ImageIcon(Utils.getImage("cards/" + nominal + color + ".png"));
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
