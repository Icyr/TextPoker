package gui.presenter;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;
import gui.model.PlayerModel;
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

    public void flop(Table table)
    {
        view.printlnText("Flop: " + table.tableCardsToString());
    }

    public void turn(Table table)
    {
        view.printlnText("Turn: " + table.tableCardsToString());
    }

    public void river(Table table)
    {
        view.printlnText("River: " + table.tableCardsToString());
    }

    public void printPlayerTurn()
    {
        view.printlnText("Your turn...");
    }

    public void printWinningCombination(Combination combination)
    {
        view.printlnText("Winning combination: " + combination.toString());

    }

    public void printPlayerFold(int indexOfPlayer)
    {
        view.printlnText(indexOfPlayer + " player folded");
    }

    public void printSmallBlindBet(int playerNumber, int blindSize)
    {
        view.printlnText(playerNumber + " player bet small blind: " + blindSize);
    }

    public void printBigBlindBet(int playerNumber, int blindSize)
    {
        view.printlnText(playerNumber + " player bet big blind: " + blindSize * 2);
    }

    public void check(int indexOfPlayer)
    {
        view.printlnText(indexOfPlayer + " player checked");
    }

    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player called " + callValue;
        if (isAllIn) message += " . ALL IN!";
        view.printlnText(message);
    }

    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        String message = indexOfPlayer + " player raised " + raiseValue;
        if (isAllIn) message += " . ALL IN!";
        view.printlnText(message);
    }

    public void printWinningPlayer(PlayerModel model, int wonAmount)
    {
        view.printlnText(model.getDescription() + " won " + wonAmount);
    }

    public void printPlayerHand(int index, Hand hand)
    {
        view.printlnText("Player's " + index + " hand: " + hand.toString());
    }

    public void printPlayerBankrupt(int index)
    {
        view.printlnText("Player " + index + " has lost all of his money!");
    }

    public void printInvalidRaise()
    {
        view.printlnText("Invalid raise");
    }
}
