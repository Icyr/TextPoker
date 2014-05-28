package entities.combinations;

public class TwoPairs implements Combination
{
    private final int higherNominal;
    private final int lowerNominal;

    public TwoPairs(int higherNominal, int lowerNominal)
    {
        this.higherNominal = higherNominal;
        this.lowerNominal = lowerNominal;
    }

    public int getLowerNominal()
    {
        return lowerNominal;
    }

    public int getHigherNominal()
    {
        return higherNominal;
    }

    public String toString()
    {
        return "Two pairs: " + higherNominal + " and " + lowerNominal;
    }

    @Override
    public int getPower()
    {
        return 26 + higherNominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(TwoPairs.class))
        {
            TwoPairs otherTwoPairs = (TwoPairs) other;
            return this.higherNominal == otherTwoPairs.higherNominal && this.lowerNominal == otherTwoPairs.lowerNominal;
        }
        return false;
    }
}
