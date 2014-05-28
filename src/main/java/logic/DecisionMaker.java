package logic;

public class DecisionMaker
{
    private double raiseIndex;
    private double bluffIndex;

    public DecisionMaker(double raiseIndex, double bluffIndex)
    {
        this.raiseIndex = raiseIndex;
        this.bluffIndex = bluffIndex;
    }

    public String makeDecision(double probability, int pot, int currentBet, int bigBlindSize, int cash)
    {
        double ev = getEV(probability, pot, currentBet);
        double bankPercent = ev / (pot + currentBet);
        if (isItTimeToBluff(bluffIndex)) bankPercent += 0.5;
        if (bankPercent < 0) return "fold";
        if (bankPercent >= 0 && bankPercent < raiseIndex) return "call";
        if (bankPercent >= raiseIndex)
        {
            int raiseAmount = calculateRaiseAmount(probability, pot, currentBet, bigBlindSize, cash);
            if (raiseAmount == 0) return "call";
            return "raise " + raiseAmount;
        }
        return "fold";
    }

    private boolean isItTimeToBluff(double bluffIndex)
    {
        return Math.random() < bluffIndex;
    }

    private static double getEV(double probability, int pot, int currentBet)
    {
        return (probability) * (pot) - (1 - probability) * currentBet;
    }

    private double getBankPercent(double probability, int pot, int currentBet)
    {
        return getEV(probability, pot, currentBet) / (pot + currentBet);
    }

    private int calculateRaiseAmount(double probability, int pot, int currentBet, int bigBlindSize, int cash)
    {
        int raiseAmount = 0;
        while (getBankPercent(probability, pot, currentBet) > raiseIndex && currentBet <= cash)
        {
            pot += bigBlindSize;
            currentBet += bigBlindSize;
            raiseAmount += bigBlindSize;
        }
        return raiseAmount;
    }
}
