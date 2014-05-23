package entities.combinations;

public class TwoPairs implements Combination
{
    private final int higherNominal;
    private final int lowerNominal;

    public TwoPairs(int nom1, int nom2)
    {
        higherNominal = nom1;
        lowerNominal = nom2;
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
}
