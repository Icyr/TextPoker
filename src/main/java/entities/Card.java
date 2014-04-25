package entities;

public class Card
{
    private final String color;
    private final int nominal;

    public Card(String color, int nominal)
    {
        this.color = color;
        this.nominal = nominal;
    }

    public String getColor()
    {
        return this.color;
    }

    public int getNominal()
    {
        return this.nominal;
    }

    public boolean equals(Object other)
    {
        if (other.getClass().equals(Card.class))
        {
            Card otherCard = (Card) other;
            return this.getColor().equals(otherCard.getColor()) && this.getNominal() == otherCard.getNominal();
        }
        return false;
    }
}
