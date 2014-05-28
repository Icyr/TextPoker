import entities.players.ComputerPlayer;
import entities.players.Player;
import entities.Card;
import entities.Hand;
import org.junit.Assert;
import org.junit.Test;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest
{
    @Test
    public void testSorter()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 2));
        cards.add(new Card("S", 3));
        cards.add(new Card("D", 4));
        cards.add(new Card("C", 5));
        cards.add(new Card("H", 6));
        cards.add(new Card("S", 4));
        cards = Utils.sortCards(cards);
        int[] res = new int[6];
        int i = 0;
        for (Card card : cards)
        {
            res[i] = card.getNominal();
            i++;
        }
        Assert.assertArrayEquals(new int[]{6, 5, 4, 4, 3, 2}, res);
    }

    @Test
    public void testGetSameColor()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 10));
        cards.add(new Card("H", 11));
        cards.add(new Card("H", 12));
        cards.add(new Card("H", 13));
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        Assert.assertEquals(5, Utils.getSameColorCount("H", cards));
    }

    @Test
    public void testGetCardsWithPreferredColor()
    {
        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card("H", 10));
        cards.add(new Card("H", 11));
        cards.add(new Card("D", 12));
        cards.add(new Card("C", 13));
        cards.add(new Card("H", 14));
        cards.add(new Card("S", 14));
        List<Card> expectedCards = new ArrayList<Card>();
        expectedCards.add(new Card("H", 10));
        expectedCards.add(new Card("H", 11));
        expectedCards.add(new Card("H", 14));
        Assert.assertEquals(Utils.getCardsWithPreferredColor("H", cards), expectedCards);
    }

    @Test
    public void testGetPlayersByCards()
    {
        List<List<Card>> winningCards = new ArrayList<List<Card>>();
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("H", 13));
        cardsOne.add(new Card("H", 12));
        cardsOne.add(new Card("H", 11));
        cardsOne.add(new Card("D", 10));
        cardsOne.add(new Card("C", 9));
        cardsOne.add(new Card("S", 9));
        cardsOne.add(new Card("C", 7));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("H", 13));
        cardsTwo.add(new Card("H", 12));
        cardsTwo.add(new Card("H", 11));
        cardsTwo.add(new Card("D", 10));
        cardsTwo.add(new Card("D", 9));
        cardsTwo.add(new Card("S", 7));
        cardsTwo.add(new Card("C", 7));
        winningCards.add(cardsOne);
        winningCards.add(cardsTwo);
        List<Card> playerOneCards = new ArrayList<Card>();
        playerOneCards.add(new Card("C", 9));
        playerOneCards.add(new Card("S", 9));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(playerOneCards));
        List<Card> playerTwoCards = new ArrayList<Card>();
        playerTwoCards.add(new Card("D", 9));
        playerTwoCards.add(new Card("S", 7));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(playerTwoCards));
        List<Card> playerThreeCards = new ArrayList<Card>();
        playerThreeCards.add(new Card("H", 2));
        playerThreeCards.add(new Card("S", 11));
        Player playerThree = new ComputerPlayer();
        playerThree.setHand(new Hand(playerThreeCards));
        List<Player> testListOfPlayers = new ArrayList<Player>();
        testListOfPlayers.add(playerOne);
        testListOfPlayers.add(playerTwo);
        testListOfPlayers.add(playerThree);
        List<Player> res = Utils.getPlayersByCards(winningCards, testListOfPlayers);
        List<Player> etalon = new ArrayList<Player>();
        etalon.add(playerOne);
        etalon.add(playerTwo);
        Assert.assertEquals(res, etalon);
    }
}
