import entities.players.Player;
import entities.Card;
import entities.players.ComputerPlayer;
import entities.Hand;
import logic.GameManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameManagerTest
{
    @Test
    public void testForTests()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("S", 6));
        tableCards.add(new Card("C", 11));
        tableCards.add(new Card("C", 6));
        tableCards.add(new Card("H", 3));
        tableCards.add(new Card("D", 14));
        List<Card> firstPlayerCards = new ArrayList<Card>();
        firstPlayerCards.add(new Card("H", 13));
        firstPlayerCards.add(new Card("C", 13));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(firstPlayerCards));
        List<Card> secondPlayerCards = new ArrayList<Card>();
        secondPlayerCards.add(new Card("S", 13));
        secondPlayerCards.add(new Card("D", 13));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(secondPlayerCards));
        List<Player> testPlayers = new ArrayList<Player>();
        testPlayers.add(playerOne);
        testPlayers.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(testPlayers, tableCards);
        Assert.assertEquals(winners.size(), 2);
    }

    @Test
    public void testTableWin()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("D", 14));
        tableCards.add(new Card("C", 14));
        tableCards.add(new Card("S", 13));
        tableCards.add(new Card("H", 11));
        List<Card> firstPlayerCards = new ArrayList<Card>();
        firstPlayerCards.add(new Card("H", 8));
        firstPlayerCards.add(new Card("S", 2));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(firstPlayerCards));
        List<Card> SecondPlayerCards = new ArrayList<Card>();
        SecondPlayerCards.add(new Card("C", 8));
        SecondPlayerCards.add(new Card("S", 3));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(SecondPlayerCards));
        List<Player> testPlayers = new ArrayList<Player>();
        testPlayers.add(playerOne);
        testPlayers.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(testPlayers, tableCards);
        Assert.assertEquals(winners.size(), 2);
    }

    @Test
    public void testKickerConflict()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("D", 11));
        tableCards.add(new Card("C", 12));
        tableCards.add(new Card("S", 9));
        tableCards.add(new Card("H", 7));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("D", 2));
        cardsOne.add(new Card("S", 8));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("S", 2));
        cardsTwo.add(new Card("S", 4));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> test = new ArrayList<Player>();
        test.add(playerOne);
        test.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(test, tableCards);
        Assert.assertEquals(winners.size(), 1);
        Assert.assertEquals(winners.get(0), playerOne);
    }

    @Test
    public void testPairConflict()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("D", 5));
        tableCards.add(new Card("C", 12));
        tableCards.add(new Card("S", 9));
        tableCards.add(new Card("H", 7));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("D", 12));
        cardsOne.add(new Card("S", 8));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("S", 12));
        cardsTwo.add(new Card("S", 4));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> test = new ArrayList<Player>();
        test.add(playerOne);
        test.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(test, tableCards);
        Assert.assertEquals(winners.size(), 1);
        Assert.assertEquals(winners.get(0), playerOne);
    }

    @Test
    public void testPairsConflict()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("D", 13));
        tableCards.add(new Card("C", 12));
        tableCards.add(new Card("S", 9));
        tableCards.add(new Card("H", 7));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("D", 14));
        cardsOne.add(new Card("S", 13));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("C", 14));
        cardsTwo.add(new Card("S", 12));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> test = new ArrayList<Player>();
        test.add(playerOne);
        test.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(test, tableCards);
        Assert.assertEquals(winners.size(), 1);
        Assert.assertEquals(winners.get(0), playerOne);
    }

    @Test
    public void testQuadsConflictTableQuads()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("S", 14));
        tableCards.add(new Card("D", 14));
        tableCards.add(new Card("C", 14));
        tableCards.add(new Card("H", 10));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("H", 9));
        cardsOne.add(new Card("S", 2));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("H", 8));
        cardsTwo.add(new Card("S", 3));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> test = new ArrayList<Player>();
        test.add(playerOne);
        test.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(test, tableCards);
        Assert.assertEquals(2, winners.size());
    }

    @Test
    public void testQuadsConflictTableQuadsWithKicker()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("S", 14));
        tableCards.add(new Card("D", 14));
        tableCards.add(new Card("C", 14));
        tableCards.add(new Card("H", 7));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("H", 9));
        cardsOne.add(new Card("S", 2));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("H", 8));
        cardsTwo.add(new Card("S", 3));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> test = new ArrayList<Player>();
        test.add(playerOne);
        test.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(test, tableCards);
        Assert.assertEquals(1, winners.size());
        Assert.assertEquals(winners.get(0), playerOne);
    }

    @Test
    public void testFullHouseConflict()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("S", 14));
        tableCards.add(new Card("D", 12));
        tableCards.add(new Card("C", 11));
        tableCards.add(new Card("H", 5));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("D", 14));
        cardsOne.add(new Card("S", 11));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("C", 14));
        cardsTwo.add(new Card("S", 5));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> test = new ArrayList<Player>();
        test.add(playerOne);
        test.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(test, tableCards);
        Assert.assertEquals(winners.size(), 1);
        Assert.assertEquals(winners.get(0), playerOne);
    }

    @Test
    public void testFlushConflict()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 14));
        tableCards.add(new Card("H", 7));
        tableCards.add(new Card("H", 5));
        tableCards.add(new Card("H", 9));
        tableCards.add(new Card("S", 11));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("H", 2));
        cardsOne.add(new Card("S", 6));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("H", 3));
        cardsTwo.add(new Card("C", 4));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> testPlayers = new ArrayList<Player>();
        testPlayers.add(playerOne);
        testPlayers.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(testPlayers, tableCards);
        Assert.assertEquals(winners.size(), 1);
        Assert.assertEquals(winners.get(0), playerTwo);
    }

    @Test
    public void testSetConflict()
    {
        List<Card> tableCards = new ArrayList<Card>();
        tableCards.add(new Card("H", 7));
        tableCards.add(new Card("S", 7));
        tableCards.add(new Card("H", 5));
        tableCards.add(new Card("H", 9));
        tableCards.add(new Card("S", 2));
        List<Card> cardsOne = new ArrayList<Card>();
        cardsOne.add(new Card("D", 7));
        cardsOne.add(new Card("S", 14));
        Player playerOne = new ComputerPlayer();
        playerOne.setHand(new Hand(cardsOne));
        List<Card> cardsTwo = new ArrayList<Card>();
        cardsTwo.add(new Card("C", 7));
        cardsTwo.add(new Card("C", 3));
        Player playerTwo = new ComputerPlayer();
        playerTwo.setHand(new Hand(cardsTwo));
        List<Player> testPlayers = new ArrayList<Player>();
        testPlayers.add(playerOne);
        testPlayers.add(playerTwo);
        List<Player> winners = GameManager.resolveConflict(testPlayers, tableCards);
        Assert.assertEquals(winners.size(), 1);
        Assert.assertEquals(winners.get(0), playerOne);
    }
}
