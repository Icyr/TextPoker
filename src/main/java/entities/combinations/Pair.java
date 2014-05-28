package entities.combinations;

public class Pair implements Combination
{
    private final int nominal;

    public Pair(int nom)
    {
        this.nominal = nom;
    }

    public int getNominal()
    {
        return nominal;
    }

    public String toString()
    {
        return "Pair of " + nominal;
    }

    @Override
    public int getPower()
    {
        return 13 + nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Pair.class))
        {
            Pair otherPair = (Pair) other;
            return this.nominal == otherPair.nominal;
        }
        return false;
    }
}
