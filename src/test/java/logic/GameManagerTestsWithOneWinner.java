package logic;

import entities.Card;
import entities.Hand;
import entities.players.ComputerPlayer;
import entities.players.Player;
import logic.GameManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class GameManagerTestsWithOneWinner
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                //Straight Flush conflict
                {new Card[]{
                        new Card("H", 2),
                        new Card("H", 13),
                        new Card("H", 12),
                        new Card("H", 11),
                        new Card("H", 10)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 9),
                        new Card("S", 5)
                }},
        };
        return Arrays.asList(data);
    }

    private List<Player> players = new ArrayList<Player>();
    private List<Card> tableCards;

    public GameManagerTestsWithOneWinner(Card[] tableCards, Card[] firstPlayerCards, Card[] secondPlayerCards)
    {
        this.tableCards = Arrays.asList(tableCards);
        Player winnerPlayer = new ComputerPlayer();
        winnerPlayer.setHand(new Hand(Arrays.asList(firstPlayerCards)));
        Player loserPlayer = new ComputerPlayer();
        loserPlayer.setHand(new Hand(Arrays.asList(secondPlayerCards)));
        players.add(winnerPlayer);
        players.add(loserPlayer);
    }

    @Test
    public void testGetWinners()
    {
        int expectedWinnersSize = 1;
        Player expectedWinner = players.get(0);
        List<Player> actualWinners = GameManager.getWinners(players, tableCards);
        assertThat(actualWinners.size(), equalTo(expectedWinnersSize));
        assertThat(actualWinners.get(0), equalTo(expectedWinner));
    }
}
