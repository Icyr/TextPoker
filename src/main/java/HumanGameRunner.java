import entities.Game;
import entities.players.HumanPlayer;
import entities.players.Player;
import gui.EndPoint;
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
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        EndPoint endPoint = Initializer.initializeEndPoint(introWindow);
        endPoint.initialize();

        Game game = Initializer.initializeGame(introWindow, endPoint);
        game.play();
    }
}
