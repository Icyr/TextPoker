package entities.combinations;

import util.Utils;

public class FullHouse implements Combination
{
    private final int setNominal;
    private final int pairNominal;

    public FullHouse(int setNominal, int pairNominal)
    {
        this.setNominal = setNominal;
        this.pairNominal = pairNominal;
    }

    @Override
    public String toString()
    {
        return "Full House with " + Utils.getCorrectNominalString(setNominal) + " and " + Utils.getCorrectNominalString(pairNominal);
    }

    @Override
    public int getPower()
    {
        return 79 + setNominal;
    }

    public int getSetNominal()
    {
        return setNominal;
    }

    public int getPairNominal()
    {
        return pairNominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(FullHouse.class))
        {
            FullHouse otherFullHouse = (FullHouse) other;
            return this.setNominal == otherFullHouse.setNominal && this.pairNominal == otherFullHouse.pairNominal;
        }
        return false;
    }
}
