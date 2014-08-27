package entities.combinations;

import entities.Color;
import util.Utils;

public class Flush implements Combination
{
    private final int nominal;
    private final Color color;

    public Flush(int nominal, Color color)
    {
        this.nominal = nominal;
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public String toString()
    {
        return "Flush with highest card " + Utils.getCorrectNominalString(nominal);
    }

    @Override
    public int getPower()
    {
        return 66 + nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Flush.class))
        {
            Flush otherFlush = (Flush) other;
            return this.nominal == otherFlush.nominal && this.color.equals(otherFlush.color);
        }
        return false;
    }
}
