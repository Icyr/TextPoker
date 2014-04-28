package entities;

public enum Combination
{
    KICKER, PAIR, TWO_PAIRS, SET, STRAIGHT, FLUSH, FULL_HOUSE, QUADS, STRAIGHT_FLUSH, ROYAL_FLUSH;

    public static Combination getCombinationByPower(int power)
    {
        if (power == 118) return ROYAL_FLUSH;
        if (power > 106)
        {
            return STRAIGHT_FLUSH;
        }
        if (power < 107 && power > 93)
        {
            return QUADS;
        }
        if (power > 80 && power < 94)
        {
            return FULL_HOUSE;
        }
        if (power > 67 && power < 81)
        {
            return FLUSH;
        }
        if (power > 53 && power < 68)
        {
            return STRAIGHT;
        }
        if (power > 40 && power < 54)
        {
            return SET;
        }
        if (power < 41 && power > 28)
        {
            return TWO_PAIRS;
        }
        if (power > 14 && power < 28)
        {
            return PAIR;
        }
        if (power > 0 && power < 15)
        {
            return KICKER;
        }
        return null;
    }
}
