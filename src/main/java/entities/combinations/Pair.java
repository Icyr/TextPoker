package entities.combinations;

public class Pair implements Combination
{
    private final int nominal;

    public Pair(int nom)
    {
        this.nominal = nom;
    }

    public String toString()
    {
        return "Pair of " + nominal;
    }
}
