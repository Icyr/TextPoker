package entities.combinations;

public class Flush implements Combination
{
    private final int nominal;

    public Flush(int nom)
    {
        this.nominal = nom;
    }

    public String toString()
    {
        return "Flush with highest card " + nominal;
    }

    @Override
    public int getPower()
    {
        return 66 + nominal;
    }

}
