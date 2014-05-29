import entities.players.ComputerPlayer;
import entities.Game;
import entities.players.HumanPlayer;
import entities.players.Player;
import gui.TextualInterface;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class HumanGameRunner
{
    public static void main(String[] args)
    {
        TextualInterface gui = new TextualInterface();
        Player player1 = new HumanPlayer(1000, gui);
        Player player2 = new ComputerPlayer(1000, 0.05, 0.04);
        Player player3 = new ComputerPlayer(1000, 0.1, 0.03);
        Player player4 = new ComputerPlayer(1000, 0.2, 0.02);
        Player player5 = new ComputerPlayer(1000, 0.01, 0.01);
        player1.setId("first");
        player2.setId("second");
        player3.setId("third");
        player4.setId("forth");
        player5.setId("fifth");
        List<Player> gamePlayers = new ArrayList<Player>();
        gamePlayers.add(player1);
        gamePlayers.add(player2);
        gamePlayers.add(player3);
        gamePlayers.add(player4);
        gamePlayers.add(player5);
        Game game = new Game(10, gui, Utils.getRandomInt(0, gamePlayers.size() - 1));
        game.addPlayers(gamePlayers);
        game.play();
    }
}
