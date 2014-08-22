package gui.presenter;

import gui.view.LogView;

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
