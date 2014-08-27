package entities.combinations;

import util.Utils;

public class Quads implements Combination
{
    private int nominal;

    public Quads(int nominal)
    {
        this.nominal = nominal;
    }

    @Override
    public String toString()
    {
        return "Quads of " + Utils.getCorrectNominalString(nominal);
    }

    @Override
    public int getPower()
    {
        return 92 + nominal;
    }

    public int getNominal()
    {
        return this.nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Quads.class))
        {
            Quads otherQuads = (Quads) other;
            return this.nominal == otherQuads.nominal;
        }
        return false;
    }
}
