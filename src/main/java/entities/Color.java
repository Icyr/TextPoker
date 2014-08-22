package entities;

public enum Color
{
    HEARTS, SPADES, DIAMONDS, CLUBS;

    public String getShortName()
    {
        return this.toString().substring(0, 1);
    }
}
