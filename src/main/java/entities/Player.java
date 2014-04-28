package entities;

public interface Player
{
    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount);

    public void setId(String id);

    public String getId();

    public void addToCash(int value);

    public void setAllIn(boolean allIn);

    public boolean isAllIn();

    public int getCash();

    public void setCurrentBet(int currentBet);

    public void addToCurrentBet(int value) throws BankruptException;

    public void unsafeAddToCurrentBet(int value);

    public boolean isFolded();

    public void setFolded(boolean folded);

    public int getCurrentBet();

    public void setHand(Hand hand);

    public Hand getHand();

    public Combination getCurrentCombination(Table table);
}
