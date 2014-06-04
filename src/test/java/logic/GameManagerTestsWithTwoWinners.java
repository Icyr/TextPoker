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
public class GameManagerTestsWithTwoWinners
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                //Royal FLush on table
                {new Card[]{
                        new Card("H", 14),
                        new Card("H", 13),
                        new Card("H", 12),
                        new Card("H", 11),
                        new Card("H", 10)
                }, new Card[]{
                        new Card("S", 2),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 5)
                }},
                //Quads on table with equal kicker in hands
                {new Card[]{
                        new Card("H", 13),
                        new Card("S", 13),
                        new Card("D", 13),
                        new Card("C", 13),
                        new Card("H", 2)
                }, new Card[]{
                        new Card("S", 2),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("D", 6),
                        new Card("S", 5)
                }},
                //Quads with kicker on table
                {new Card[]{
                        new Card("S", 13),
                        new Card("D", 13),
                        new Card("C", 13),
                        new Card("H", 13),
                        new Card("H", 10)
                }, new Card[]{
                        new Card("S", 2),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 5)
                }},
                //Full House on table
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 2),
                        new Card("H", 2),
                        new Card("S", 2)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("D", 10),
                        new Card("S", 9)
                }},
                //Full House with same pair
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("H", 5),
                        new Card("S", 7)
                }, new Card[]{
                        new Card("D", 6),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 6)
                }},
                //Full House with same pair witn 1 card on table
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 3),
                        new Card("C", 5),
                        new Card("S", 7)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 14)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("C", 2)
                }},
                //Full House with same set
                {new Card[]{
                        new Card("H", 3),
                        new Card("S", 3),
                        new Card("D", 2),
                        new Card("H", 2),
                        new Card("S", 7)
                }, new Card[]{
                        new Card("D", 3),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("C", 3),
                        new Card("C", 6)
                }},
                //Flush on table
                {new Card[]{
                        new Card("H", 3),
                        new Card("H", 5),
                        new Card("H", 7),
                        new Card("H", 9),
                        new Card("H", 10)
                }, new Card[]{
                        new Card("D", 6),
                        new Card("S", 6)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("C", 6)
                }},
                //Straight on table
                {new Card[]{
                        new Card("H", 2),
                        new Card("H", 3),
                        new Card("C", 4),
                        new Card("S", 5),
                        new Card("D", 14)
                }, new Card[]{
                        new Card("D", 8),
                        new Card("S", 10)
                }, new Card[]{
                        new Card("D", 10),
                        new Card("C", 9)
                }},
                //Straight with 1 same card in hand
                {new Card[]{
                        new Card("H", 2),
                        new Card("H", 3),
                        new Card("C", 4),
                        new Card("S", 5),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 8),
                        new Card("S", 14)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("C", 9)
                }},
                //Straight with 2 same cards in hand
                {new Card[]{
                        new Card("H", 2),
                        new Card("C", 4),
                        new Card("S", 5),
                        new Card("H", 12),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 6),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 3)
                }},
                //Set with 2 highest kickers on table
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 5),
                        new Card("H", 12),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 6),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 3)
                }},
                //Set with highest kicker on table and in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 5),
                        new Card("H", 12),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("C", 3)
                }},
                //Set on table with 2 highest kickers in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 5),
                        new Card("H", 10),
                        new Card("D", 11)
                }, new Card[]{
                        new Card("D", 12),
                        new Card("S", 13)
                }, new Card[]{
                        new Card("H", 12),
                        new Card("C", 13)
                }},
                //Set with same cards in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 10),
                        new Card("H", 12),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("C", 3)
                }},
                //Set with same cards in hand and same kicker in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 10),
                        new Card("H", 12),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 14)
                }, new Card[]{
                        new Card("H", 5),
                        new Card("C", 14)
                }},
                //Two pairs on table with kicker
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 6),
                        new Card("H", 6),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 4),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 4),
                        new Card("C", 3)
                }},
                //Two pairs on table with kicker in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 6),
                        new Card("H", 6),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("C", 3)
                }},
                //Two pairs in hands
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 7),
                        new Card("S", 6),
                        new Card("H", 9),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 5),
                        new Card("S", 7)
                }, new Card[]{
                        new Card("H", 7),
                        new Card("C", 5)
                }},
                //Two pairs on table with kicker
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 6),
                        new Card("H", 9),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 6),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 6),
                        new Card("C", 3)
                }},
                //Two pairs on table with kicker with a higher pair in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 6),
                        new Card("H", 6),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("S", 14)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("C", 14)
                }},
                //Pair with kickers on table
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 12),
                        new Card("H", 14),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 4),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 4),
                        new Card("C", 3)
                }},
                //Pair with kicker in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 5),
                        new Card("S", 6),
                        new Card("H", 9),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("C", 3)
                }},
                //Pair in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 7),
                        new Card("S", 9),
                        new Card("H", 6),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 14),
                        new Card("S", 14)
                }, new Card[]{
                        new Card("H", 14),
                        new Card("C", 14)
                }},
                //Half pair in hand
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 7),
                        new Card("S", 6),
                        new Card("H", 9),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 4),
                        new Card("S", 13)
                }, new Card[]{
                        new Card("H", 4),
                        new Card("C", 13)
                }},
                //Total table win
                {new Card[]{
                        new Card("H", 5),
                        new Card("C", 7),
                        new Card("S", 9),
                        new Card("H", 11),
                        new Card("D", 13)
                }, new Card[]{
                        new Card("D", 2),
                        new Card("S", 3)
                }, new Card[]{
                        new Card("H", 2),
                        new Card("C", 3)
                }},
        };
        return Arrays.asList(data);
    }

    private List<Player> players = new ArrayList<Player>();
    private List<Card> tableCards;

    public GameManagerTestsWithTwoWinners(Card[] tableCards, Card[] firstPlayerCards, Card[] secondPlayerCards)
    {
        this.tableCards = Arrays.asList(tableCards);
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(Arrays.asList(firstPlayerCards)));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(Arrays.asList(secondPlayerCards)));
        players.add(playerOne);
        players.add(playerTwo);
    }

    @Test
    public void testGetWinners()
    {
        int actualWinnersSize = ConflictResolver.getWinners(players, tableCards).size();
        int expectedWinnersSize = 2;
        assertThat(actualWinnersSize, equalTo(expectedWinnersSize));
    }
}
