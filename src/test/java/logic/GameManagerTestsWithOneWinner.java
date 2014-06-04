package logic;

import entities.Card;
import entities.Hand;
import entities.players.ComputerPlayer;
import entities.players.Player;
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
                //Quads conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 3),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("H", 10)
                }, new Card[]{
                        new Card("H", 11),
                        new Card("S", 5)
                }},
                //Full House conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 6),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("H", 10)
                }, new Card[]{
                        new Card("H", 11),
                        new Card("S", 5)
                }},
                //Full House conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 6),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("S", 5)
                }},
                //Full House conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 9),
                        new Card("H", 12)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("S", 5)
                }},
                //Flush conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 5),
                        new Card("H", 7),
                        new Card("H", 9),
                        new Card("H", 12)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 4),
                        new Card("S", 4)
                }},
                //Set conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 6),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 13),
                        new Card("S", 5)
                }},
                //Set conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 9),
                        new Card("C", 6),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("D", 3)
                }, new Card[]{
                        new Card("H", 13),
                        new Card("C", 3)
                }},
                //Set conflict
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 14),
                        new Card("H", 9)
                }, new Card[]{
                        new Card("H", 13),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 12),
                        new Card("S", 5)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 9),
                        new Card("D", 10),
                        new Card("C", 14),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 9),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 10),
                        new Card("S", 5)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 9),
                        new Card("D", 10),
                        new Card("C", 14),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 10),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 6)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 10),
                        new Card("D", 10),
                        new Card("C", 14),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 13),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 12),
                        new Card("C", 6)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 9),
                        new Card("D", 10),
                        new Card("C", 14),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 10),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 6)
                }},
                //Pair conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 9),
                        new Card("D", 10),
                        new Card("C", 14),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 13),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 12),
                        new Card("C", 6)
                }},
                //Pair conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 9),
                        new Card("D", 10),
                        new Card("C", 13),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 12),
                        new Card("S", 14)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 14)
                }},
                //Kicker conflict
                {new Card[]{
                        new Card("H", 14),
                        new Card("S", 9),
                        new Card("D", 10),
                        new Card("C", 13),
                        new Card("H", 5)
                }, new Card[]{
                        new Card("H", 8),
                        new Card("S", 2)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 2)
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
        List<Player> actualWinners = ConflictResolver.getWinners(players, tableCards);
        assertThat(actualWinners.size(), equalTo(expectedWinnersSize));
        assertThat(actualWinners.get(0), equalTo(expectedWinner));
    }
}
