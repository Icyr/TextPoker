package gui.presenter;

import entities.Table;
import gui.view.TableView;

public class TablePresenter
{
    private TableView view;

    public TablePresenter(TableView view)
    {
        this.view = view;
    }

    public void flop(Table table)
    {
        view.setFirstTableCard(table.getCardsOnTable().get(0).getIcon());
        view.setSecondTableCard(table.getCardsOnTable().get(1).getIcon());
        view.setThirdTableCard(table.getCardsOnTable().get(2).getIcon());
    }

    public void turn(Table table)
    {
        view.setFirstTableCard(table.getCardsOnTable().get(0).getIcon());
        view.setSecondTableCard(table.getCardsOnTable().get(1).getIcon());
        view.setThirdTableCard(table.getCardsOnTable().get(2).getIcon());
        view.setForthTableCard(table.getCardsOnTable().get(3).getIcon());
    }

    public void river(Table table)
    {
        view.setFirstTableCard(table.getCardsOnTable().get(0).getIcon());
        view.setSecondTableCard(table.getCardsOnTable().get(1).getIcon());
        view.setThirdTableCard(table.getCardsOnTable().get(2).getIcon());
        view.setForthTableCard(table.getCardsOnTable().get(3).getIcon());
        view.setFifthTableCard(table.getCardsOnTable().get(4).getIcon());
    }

    public void discardTableCards()
    {
        view.setFirstTableCard(null);
        view.setSecondTableCard(null);
        view.setThirdTableCard(null);
        view.setForthTableCard(null);
        view.setFifthTableCard(null);
    }

    public void setBank(int value)
    {
        view.setBank(Integer.toString(value));
    }
}
