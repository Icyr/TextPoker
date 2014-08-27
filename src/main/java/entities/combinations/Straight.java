package entities.combinations;

import util.Utils;

public class Straight implements Combination
{
    private final int nominal;

    public Straight(int nom)
    {
        this.nominal = nom;
    }

    public int getNominal()
    {
        return nominal;
    }

    public String toString()
    {
        return "Straight starting with " + Utils.getCorrectNominalString(nominal);
    }

    @Override
    public int getPower()
    {
        return 53 + nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Straight.class))
        {
            Straight otherStraight = (Straight) other;
            return this.nominal == otherStraight.nominal;
        }
        return false;
    }
}
