package entities.combinations;

public class StraightFlush implements Combination
{
    private final int nominal;

    public StraightFlush(int nominal)
    {
        this.nominal = nominal;
    }

    @Override
    public String toString()
    {
        return "Straight Flush starting with " + nominal;
    }

    @Override
    public int getPower()
    {
        return 105 + nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(StraightFlush.class))
        {
            StraightFlush otherStraightFlush = (StraightFlush) other;
            return this.nominal == otherStraightFlush.nominal;
        }
        return false;
    }
}
