import gui.EndPoint;
import gui.model.GameModel;
import gui.presenter.*;
import gui.view.*;

import java.util.ArrayList;
import java.util.List;

public class Initializer
{
    public static EndPoint initialize(int opponentCount)
    {
        GameModel gameModel = new GameModel();

        LogView logView = new LogView();
        PlayerView playerView = new PlayerView();
        TableView tableView = new TableView();
        DecisionModuleView decisionModuleView = new DecisionModuleView();
        List<OpponentView> opponentViews = new ArrayList<OpponentView>(opponentCount);
        GameView gameView = new GameView(logView, decisionModuleView, opponentViews, tableView, playerView);

        List<OpponentPresenter> opponentPresenters = new ArrayList<OpponentPresenter>(opponentCount);
        for (int i = 0; i < opponentCount; i++)
        {
            OpponentView view = OpponentViewFactory.createOpponentView();
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
