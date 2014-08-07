import entities.Game;
import entities.players.HumanPlayer;
import entities.players.Player;
import gui.GraphicalInterface;
import gui.Interface;
import gui.IntroWindow;
import entities.players.ComputerPlayerFactory;
import util.Utils;

import java.util.ArrayList;

public class HumanGameRunner
{
    public static void main(String[] args)
    {
        IntroWindow introWindow = new IntroWindow();
        while (!introWindow.ready)
        {
            try
            {
                //wait for decision
                Thread.sleep(500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        Interface gui = new GraphicalInterface(introWindow.opponentsNumber);
        gui.initialize();
        Player humanPlayer = new HumanPlayer(introWindow.moneyAmount, gui);
        humanPlayer.setId("you");
        java.util.List<Player> gamePlayers = new ArrayList<Player>();
        gamePlayers.add(humanPlayer);
        int numberOfOpponents = introWindow.opponentsNumber;
        for (int i = 0; i < numberOfOpponents; i++)
        {
            gamePlayers.add(ComputerPlayerFactory.createComputerPlayer(introWindow.moneyAmount));
        }
        Game game = new Game(introWindow.blindSize, gui, Utils.getRandomInt(0, gamePlayers.size() - 1));
        game.addPlayers(gamePlayers);
        game.play();
    }
}
