package gui.presenter;

import entities.players.Player;
import gui.view.LogView;

import java.util.List;

public class LogPresenter
{
    private LogView view;

    public LogPresenter(LogView view)
    {
        this.view = view;
    }

    public void deal()
    {
        view.printlnText("Dealing cards..");
    }

    public void moveButton(int button)
    {
        view.printlnText("Moved button to " + button);
    }
}
