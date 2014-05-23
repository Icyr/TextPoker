import entities.*;
import entities.players.ComputerPlayer;
import entities.players.Player;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class GameTest
{
    //todo need more tests
    //@Test
    public void testBankruptBlind()
    {
        Game game = new Game(10, null);
        Player player = new ComputerPlayer(5, 0.0, 0.0);
        Player otherPlayer = new ComputerPlayer(500, 0.0, 0.0);
        Player otherOtherPlayer = new ComputerPlayer(500, 0.0, 0.0);
        game.addPlayer(otherOtherPlayer);
        game.addPlayer(otherPlayer);
        game.addPlayer(player);
        game.deal();
        game.betBlinds();
        Assert.assertEquals(2, game.getActivePlayersCount());
        Assert.assertEquals(490, otherPlayer.getCash());
        Assert.assertEquals(480, otherOtherPlayer.getCash());
    }

    //@Test
    public void testCalculateAllInAmount()
    {
        Game game = new Game(1, null);
        Player player1 = new ComputerPlayer(100, 0.0, 0.0);
        player1.setAllIn(true);
        player1.setCurrentBet(5);
        Player player2 = new ComputerPlayer(100, 0.0, 0.0);
        player2.setAllIn(true);
        player2.setCurrentBet(10);
        Player player3 = new ComputerPlayer(100, 0.0, 0.0);
        player3.setAllIn(false);
        player3.setCurrentBet(10);
        Player player4 = new ComputerPlayer(100, 0.0, 0.0);
        player4.setCurrentBet(2);
        player4.setAllIn(false);
        player4.setFolded(true);
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        game.addPlayers(players);
        game.deal();
        int second = game.calculateAllInWinAmount(player2, players);
        Assert.assertEquals(27, second);
    }

    //@Test
    public void testMoneyDistribution()
    {
        Game game = new Game(1, null);
        Player player1 = new ComputerPlayer(100, 0.0, 0.0);
        player1.setAllIn(true);
        player1.setCurrentBet(5);
        Player player2 = new ComputerPlayer(100, 0.0, 0.0);
        player2.setAllIn(true);
        player2.setCurrentBet(10);
        Player player3 = new ComputerPlayer(100, 0.0, 0.0);
        player3.setAllIn(false);
        player3.setCurrentBet(10);
        Player player4 = new ComputerPlayer(100, 0.0, 0.0);
        player4.setCurrentBet(3);
        player4.setAllIn(false);
        player4.setFolded(true);
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        game.addPlayers(players);
        game.deal();
        List<Player> winners = new ArrayList<Player>();
        winners.add(player1);
        winners.add(player2);
        game.setBank(28);
        game.distributeWonMoney(winners);
        Assert.assertEquals(player1.getCash(), 104);
        Assert.assertEquals(player2.getCash(), 109);
    }
}
