package entities.combinations;

public class Kicker implements Combination
{
    private final int nominal;

    public Kicker(int nom)
    {
        this.nominal = nom;
    }

    public String toString()
    {
        return "Kicker " + nominal;
    }
}
