package entities.combinations;

public class Set implements Combination
{
    private final int nominal;

    public Set(int nominal)
    {
        this.nominal = nominal;
    }

    @Override
    public String toString()
    {
        return "Set of " + nominal;
    }

    @Override
    public int getPower()
    {
        return 39 + nominal;
    }
}
