import entities.Game;
import entities.players.ComputerPlayerFactory;
import entities.players.HumanPlayer;
import entities.players.Player;
import gui.EndPoint;
import gui.IntroWindow;
import gui.model.GameModel;
import gui.presenter.*;
import gui.view.*;
import util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Initializer
{
    public static EndPoint initializeEndPoint(IntroWindow introWindow)
    {
        LogView logView = new LogView();
        PlayerView playerView = new PlayerView();
        TableView tableView = new TableView();
        DecisionModuleView decisionModuleView = new DecisionModuleView();
        int opponentCount = introWindow.opponentsNumber;
        List<OpponentView> opponentViews = new ArrayList<OpponentView>(opponentCount);
        GameView gameView = new GameView(logView, decisionModuleView, opponentViews, tableView, playerView);

        List<OpponentPresenter> opponentPresenters = new ArrayList<OpponentPresenter>(opponentCount);
        for (int i = 0; i < opponentCount; i++)
        {
            OpponentView view = new OpponentView();
            opponentViews.add(view);
            opponentPresenters.add(new OpponentPresenter(view));
        }
        LogPresenter logPresenter = new LogPresenter(logView);
        PlayerPresenter playerPresenter = new PlayerPresenter(playerView);
        TablePresenter tablePresenter = new TablePresenter(tableView);
        DecisionModulePresenter decisionModulePresenter = new DecisionModulePresenter(decisionModuleView);

        return new GamePresenter(tablePresenter, opponentPresenters, playerPresenter, logPresenter, decisionModulePresenter, gameView);
    }

    public static Game initializeGame(IntroWindow introWindow, EndPoint endPoint)
    {
        Player humanPlayer = new HumanPlayer(introWindow.moneyAmount, endPoint);
        humanPlayer.setId("You");
        java.util.List<Player> gamePlayers = new ArrayList<Player>();
        gamePlayers.add(humanPlayer);
        int numberOfOpponents = introWindow.opponentsNumber;
        for (int i = 0; i < numberOfOpponents; i++)
        {
            gamePlayers.add(ComputerPlayerFactory.createRandomComputerPlayer(introWindow.moneyAmount, i));
        }
        Game game = new Game(introWindow.blindSize, endPoint, Utils.getRandomInt(0, gamePlayers.size() - 1));
        game.addPlayers(gamePlayers);
        return game;
    }
}
