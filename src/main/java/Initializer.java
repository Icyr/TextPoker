import gui.EndPoint;
import gui.IntroWindow;
import gui.model.GameModel;
import gui.presenter.*;
import gui.view.*;

import java.util.ArrayList;
import java.util.List;

public class Initializer
{
    public static EndPoint initialize(IntroWindow introWindow)
    {
        GameModel gameModel = new GameModel();
        gameModel.setBlindSize(introWindow.blindSize);

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

        return new GamePresenter(tablePresenter, opponentPresenters, playerPresenter, logPresenter, decisionModulePresenter, gameModel, gameView);
    }
}
