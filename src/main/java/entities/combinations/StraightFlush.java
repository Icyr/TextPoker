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
        return "Straigh Flush starting with " + nominal;
    }

    @Override
    public int getPower()
    {
        return 105 + nominal;
    }
}
