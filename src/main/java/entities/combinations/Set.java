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

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(Set.class))
        {
            Set otherSet = (Set) other;
            return this.nominal == otherSet.nominal;
        }
        return false;
    }
}
