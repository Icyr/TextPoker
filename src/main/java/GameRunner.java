import entities.players.ComputerPlayer;
import entities.Game;
import entities.players.Player;
import gui.TextualInterface;
import util.Utils;

public class GameRunner
{
    public static void main(String[] args)
    {
        TextualInterface gui = new TextualInterface();
        Player player1 = new ComputerPlayer(1000, 0.01, 0.05, 10000);
        Player player2 = new ComputerPlayer(1000, 0.05, 0.04, 10000);
        Player player3 = new ComputerPlayer(1000, 0.1, 0.03, 10000);
        Player player4 = new ComputerPlayer(1000, 0.2, 0.02, 10000);
        Player player5 = new ComputerPlayer(1000, 0.3, 0.01, 10000);
        player1.setId("first");
        player2.setId("second");
        player3.setId("third");
        player4.setId("forth");
        player5.setId("fifth");
        Game game = new Game(10, gui, Utils.getRandomInt(0, 4));
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);
        game.addPlayer(player4);
        game.addPlayer(player5);
        game.play();
    }
}
