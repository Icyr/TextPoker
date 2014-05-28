package entities.combinations;

public class Flush implements Combination
{
    private final int nominal;
    private final String color;

    public Flush(int nominal, String color)
    {
        this.nominal = nominal;
        this.color = color;
    }

    public String getColor()
    {
        return color;
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

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Flush.class))
        {
            Flush otherFlush = (Flush) other;
            return this.nominal == otherFlush.nominal && this.color.equals(otherFlush.color);
        }
        return false;
    }
}
