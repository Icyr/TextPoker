package entities;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer
{
    public HumanPlayer(int cash)
    {
        this.cash = cash;
    }

    public String makeDecision(Hand hand, Table table, int bank, int currentRaise, int blindSize, int playersCount)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Your cards:");
        System.out.println(hand.toString());
        System.out.println("Table cards:");
        System.out.println(table.tableCardsToString());
        System.out.println("Your current bet:");
        System.out.println(currentBet);
        System.out.println("Your decision (fold, call " + currentRaise + ", raise X):");
        return scan.nextLine();
    }

    public boolean equals(Object other)
    {
        if (other.getClass().equals(HumanPlayer.class))
        {
            Player otherPlayer = (HumanPlayer) other;
            return this.getHand().getCards().get(0).equals(otherPlayer.getHand().getCards().get(0)) && this.getHand().getCards().get(1).equals(otherPlayer.getHand().getCards().get(1));
        }
        return false;
    }
}
