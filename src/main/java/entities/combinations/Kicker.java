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

    @Override
    public int getPower()
    {
        return nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Kicker.class))
        {
            Kicker otherKicker = (Kicker) other;
            return this.nominal == otherKicker.nominal;
        }
        return false;
    }
}
