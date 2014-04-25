package logic;

import entities.*;

import java.util.ArrayList;
import java.util.List;

public class ProbabilityCalculator
{
    //todo make a list with all of the possibilities and we will get result from this list.
    public static double calculateProbability(Hand myHand, int iterations, String stage, List<Card> tableCards, int playerCount)
    {
        int winCount = 0;
        for (int j = 0; j < iterations; j++)
        {
            Dealer dealer = new Dealer();
            dealer.removeCardsFromDeck(myHand.getCards());
            if (tableCards.size() > 0)
                dealer.removeCardsFromDeck(tableCards);
            List<Player> players = new ArrayList<Player>();
            Player myPlayer = new ComputerPlayer();
            myPlayer.setHand(myHand);
            players.add(myPlayer);
            for (int i = 0; i < playerCount - 1; i++)
            {
                Player temp = new ComputerPlayer();
                temp.setHand(new Hand(dealer.getCards(2)));
                players.add(temp);
            }

            Table table = new Table(dealer);
            if (tableCards.size() > 0) table.setCards(tableCards);
            if (stage.equals("Preflop"))
            {
                table.flop();
                table.turn();
                table.river();
            } else if (stage.equals("Flop"))
            {
                table.turn();
                table.river();
            } else if (stage.equals("Turn"))
            {
                table.river();
            }
            List<Player> winners = GameManager.getWinners(players, table);
            if (winners.contains(players.get(0))) winCount++;
        }
        return (double) winCount / iterations;
    }
}
