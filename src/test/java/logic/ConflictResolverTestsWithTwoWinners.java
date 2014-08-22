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
public class ConflictResolverTestsWithTwoWinners
{
    @Parameterized.Parameters
    public static java.util.Collection<Object[]> data()
    {
        Object[][] data = new Object[][]{
                //Royal FLush on table
                {new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.HEARTS, 13),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.HEARTS, 11),
                        new Card(Color.HEARTS, 10)
                }, new Card[]{
                        new Card(Color.SPADES, 2),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 5)
                }},
                //Quads on table with equal kicker in hands
                {new Card[]{
                        new Card(Color.HEARTS, 13),
                        new Card(Color.SPADES, 13),
                        new Card(Color.DIAMONDS, 13),
                        new Card(Color.CLUBS, 13),
                        new Card(Color.HEARTS, 2)
                }, new Card[]{
                        new Card(Color.SPADES, 2),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 6),
                        new Card(Color.SPADES, 5)
                }},
                //Quads with kicker on table
                {new Card[]{
                        new Card(Color.SPADES, 13),
                        new Card(Color.DIAMONDS, 13),
                        new Card(Color.CLUBS, 13),
                        new Card(Color.HEARTS, 13),
                        new Card(Color.HEARTS, 10)
                }, new Card[]{
                        new Card(Color.SPADES, 2),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 5)
                }},
                //Full House on table
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 2),
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 2)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.SPADES, 9)
                }},
                //Full House with same pair
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.HEARTS, 5),
                        new Card(Color.SPADES, 7)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 6),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 6)
                }},
                //Full House with same pair witn 1 card on table
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 7)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 14)
                }, new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 2)
                }},
                //Full House with same set
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.SPADES, 3),
                        new Card(Color.DIAMONDS, 2),
                        new Card(Color.HEARTS, 2),
                        new Card(Color.SPADES, 7)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 3),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.CLUBS, 3),
                        new Card(Color.CLUBS, 6)
                }},
                //Flush on table
                {new Card[]{
                        new Card(Color.HEARTS, 3),
                        new Card(Color.HEARTS, 5),
                        new Card(Color.HEARTS, 7),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.HEARTS, 10)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 6),
                        new Card(Color.SPADES, 6)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.CLUBS, 6)
                }},
                //Straight on table
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.HEARTS, 3),
                        new Card(Color.CLUBS, 4),
                        new Card(Color.SPADES, 5),
                        new Card(Color.DIAMONDS, 14)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 8),
                        new Card(Color.SPADES, 10)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 10),
                        new Card(Color.CLUBS, 9)
                }},
                //Straight with 1 same card in hand
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.HEARTS, 3),
                        new Card(Color.CLUBS, 4),
                        new Card(Color.SPADES, 5),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 8),
                        new Card(Color.SPADES, 14)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.CLUBS, 9)
                }},
                //Straight with 2 same cards in hand
                {new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.CLUBS, 4),
                        new Card(Color.SPADES, 5),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 6),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 3)
                }},
                //Set with 2 highest kickers on table
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 5),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 6),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 3)
                }},
                //Set with highest kicker on table and in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 5),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.CLUBS, 3)
                }},
                //Set on table with 2 highest kickers in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 5),
                        new Card(Color.HEARTS, 10),
                        new Card(Color.DIAMONDS, 11)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 12),
                        new Card(Color.SPADES, 13)
                }, new Card[]{
                        new Card(Color.HEARTS, 12),
                        new Card(Color.CLUBS, 13)
                }},
                //Set with same cards in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 10),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 3)
                }},
                //Set with same cards in hand and same kicker in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 10),
                        new Card(Color.HEARTS, 12),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 14)
                }, new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 14)
                }},
                //Two pairs on table with kicker
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 6),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 4),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 4),
                        new Card(Color.CLUBS, 3)
                }},
                //Two pairs on table with kicker in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 6),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.CLUBS, 3)
                }},
                //Two pairs in hands
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 7),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 5),
                        new Card(Color.SPADES, 7)
                }, new Card[]{
                        new Card(Color.HEARTS, 7),
                        new Card(Color.CLUBS, 5)
                }},
                //Two pairs on table with kicker
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 6),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 6),
                        new Card(Color.CLUBS, 3)
                }},
                //Two pairs on table with kicker with a higher pair in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 6),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.SPADES, 14)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.CLUBS, 14)
                }},
                //Pair with kickers on table
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 12),
                        new Card(Color.HEARTS, 14),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 4),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 4),
                        new Card(Color.CLUBS, 3)
                }},
                //Pair with kicker in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 5),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.CLUBS, 3)
                }},
                //Pair in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 7),
                        new Card(Color.SPADES, 9),
                        new Card(Color.HEARTS, 6),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 14),
                        new Card(Color.SPADES, 14)
                }, new Card[]{
                        new Card(Color.HEARTS, 14),
                        new Card(Color.CLUBS, 14)
                }},
                //Half pair in hand
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 7),
                        new Card(Color.SPADES, 6),
                        new Card(Color.HEARTS, 9),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 4),
                        new Card(Color.SPADES, 13)
                }, new Card[]{
                        new Card(Color.HEARTS, 4),
                        new Card(Color.CLUBS, 13)
                }},
                //Total table win
                {new Card[]{
                        new Card(Color.HEARTS, 5),
                        new Card(Color.CLUBS, 7),
                        new Card(Color.SPADES, 9),
                        new Card(Color.HEARTS, 11),
                        new Card(Color.DIAMONDS, 13)
                }, new Card[]{
                        new Card(Color.DIAMONDS, 2),
                        new Card(Color.SPADES, 3)
                }, new Card[]{
                        new Card(Color.HEARTS, 2),
                        new Card(Color.CLUBS, 3)
                }},
        };
        return Arrays.asList(data);
    }

    private List<Player> players = new ArrayList<Player>();
    private List<Card> tableCards;

    public ConflictResolverTestsWithTwoWinners(Card[] tableCards, Card[] firstPlayerCards, Card[] secondPlayerCards)
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
