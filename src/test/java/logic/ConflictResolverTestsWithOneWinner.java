package logic;

import entities.Card;
import entities.Color;
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
public class ConflictResolverTestsWithOneWinner
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                //Straight Flush conflict
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.HEARTS, 13),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.HEARTS, 11),
                        new Card(Color.HEARTS, 10)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 9),
                        new Card(Color.SPADES, 5)
                }},
                //Quads conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 3),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.HEARTS, 10)
                }, new Card[]{
                        new Card(Color.HEARTS, 11),
                        new Card(Color.SPADES, 5)
                }},
                //Full House conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 6),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.HEARTS, 10)
                }, new Card[]{
                        new Card(Color.HEARTS, 11),
                        new Card(Color.SPADES, 5)
                }},
                //Full House conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 6),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.SPADES, 5)
                }},
                //Full House conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 9),
                        new Card(Color.HEARTS, 12)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.SPADES, 5)
                }},
                //Flush conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.HEARTS, 5),
                        new Card(Color.HEARTS, 7),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.HEARTS, 12)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 4),
                        new Card(Color.SPADES, 4)
                }},
                //Set conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 6),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 13),
                        new Card(Color.SPADES, 5)
                }},
                //Set conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 9),
                        new Card(Color.CLUBS, 6),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.DIAMONDS, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 13),
                        new Card(Color.CLUBS, 3)
                }},
                //Set conflict
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 14),
                        new Card(Color.HEARTS, 9)
                }, new Card[]{
                        new Card(Color.HEARTS, 13),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 12),
                        new Card(Color.SPADES, 5)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 9),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 14),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 9),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 10),
                        new Card(Color.SPADES, 5)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 9),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 14),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 10),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 6)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 10),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 14),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 13),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 12),
                        new Card(Color.CLUBS, 6)
                }},
                //Two Pairs conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 9),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 14),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 10),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 6)
                }},
                //Pair conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 9),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 14),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 13),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 12),
                        new Card(Color.CLUBS, 6)
                }},
                //Pair conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 9),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 13),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 12),
                        new Card(Color.SPADES, 14)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 14)
                }},
                //Kicker conflict
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.SPADES, 9),
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 13),
                        new Card(Color.HEARTS, 5)
                }, new Card[]{
                        new Card(Color.HEARTS, 8),
                        new Card(Color.SPADES, 2)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 2)
                }},

        };
        return Arrays.asList(data);
    }

    private List<Player> players = new ArrayList<Player>();
    private List<Card> tableCards;

    public ConflictResolverTestsWithOneWinner(Card[] tableCards, Card[] firstPlayerCards, Card[] secondPlayerCards)
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
