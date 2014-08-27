package entities.combinations;

import util.Utils;

public class Set implements Combination
{
    private final int nominal;

    public Set(int nominal)
    {
        this.nominal = nominal;
    }

    public int getNominal()
    {
        return nominal;
    }

    @Override
    public String toString()
    {
        return "Set of " + Utils.getCorrectNominalString(nominal);
    }

    @Override
    public int getPower()
    {
        return 39 + nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Set.class))
        {
            Set otherSet = (Set) other;
            return this.nominal == otherSet.nominal;
        }
        return false;
    }
}
