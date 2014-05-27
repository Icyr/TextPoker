package entities.combinations;

public class Straight implements Combination
{
    private final int nominal;

    public Straight(int nom)
    {
        this.nominal = nom;
    }

    public String toString()
    {
        return "Straight starting with " + nominal;
    }

    @Override
    public int getPower()
    {
        return 53 + nominal;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Straight.class))
        {
            Straight otherStraight = (Straight) other;
            return this.nominal == otherStraight.nominal;
        }
        return false;
    }
}
