package gui.presenter;

import entities.Hand;
import entities.Table;
import entities.combinations.Combination;
import entities.players.Player;
import gui.EndPoint;
import gui.model.GameModel;
import gui.model.PlayerModel;
import gui.view.GameView;

import javax.swing.*;
import java.util.List;

//todo should not implement interface. Will remove it when endpoint will be implemented.
public class GamePresenter implements EndPoint
{
    private TablePresenter tablePresenter;
    private List<OpponentPresenter> opponentPresenters;
    private PlayerPresenter playerPresenter;
    private LogPresenter logPresenter;
    private DecisionModulePresenter decisionModulePresenter;

    private GameModel gameModel;

    private GameView gameView;

    public GamePresenter(TablePresenter tablePresenter, List<OpponentPresenter> opponentPresenters, PlayerPresenter playerPresenter, LogPresenter logPresenter, DecisionModulePresenter decisionModulePresenter, GameModel gameModel, GameView gameView)
    {
        this.tablePresenter = tablePresenter;
        this.opponentPresenters = opponentPresenters;
        this.playerPresenter = playerPresenter;
        this.logPresenter = logPresenter;
        this.decisionModulePresenter = decisionModulePresenter;
        this.gameModel = gameModel;
        this.gameView = gameView;
    }

    //todo move to controller
    @Override
    public void initialize()
    {
        gameView.initialize();
    }

    @Override
    public void prepareForRound(int button, List<Player> players)
    {
        setBetAmount(0);
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setBet(0);
        }
        playerPresenter.discardCards();
        tablePresenter.discardTableCards();
        logPresenter.moveButton(button);
        logPresenter.deal();
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.showCardBacks();
        }
        playerPresenter.setHandCards(players.get(0).getHand());
        updatePlayersCash(players);
    }

    @Override
    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize)
    {
        logPresenter.printSmallBlindBet(firstPlayerNumber, blindSize);
        logPresenter.printBigBlindBet(secondPlayerNumber, blindSize);
        if (firstPlayerNumber == 0)
        {
            playerPresenter.setBet(blindSize);
            opponentPresenters.get(0).setBet(blindSize * 2);
        }
        else if (secondPlayerNumber == 0)
        {
            playerPresenter.setBet(blindSize * 2);
            opponentPresenters.get(opponentPresenters.size() - 1).setBet(blindSize);
        }
        else
        {
            opponentPresenters.get(firstPlayerNumber - 1).setBet(blindSize);
            opponentPresenters.get(secondPlayerNumber - 1).setBet(blindSize * 2);
        }
    }

    @Override
    public String getDecision(int callValue, int cash, int tableCardCount)
    {
        logPresenter.printPlayerTurn();
        return decisionModulePresenter.getDecision(callValue, cash, tableCardCount, gameModel.getBankValue(), gameModel.getBlindSize());
    }

    //todo: all actions(check, fold, call, raise) should get Player instead of index in parameters. Need GameModel for it.
    @Override
    public void check(int indexOfPlayer)
    {
        logPresenter.check(indexOfPlayer);
    }

    @Override
    public void fold(int indexOfPlayer)
    {
        logPresenter.printPlayerFold(indexOfPlayer);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).fold();
        }
    }

    @Override
    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        logPresenter.call(indexOfPlayer, callValue, isAllIn);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).addToBet(callValue);
        }
    }

    @Override
    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        logPresenter.raise(indexOfPlayer, raiseValue, isAllIn);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).addToBet(raiseValue);
        }
        else
        {
            playerPresenter.setBet(raiseValue);
        }
    }

    @Override
    public void setBank(int value)
    {
        gameModel.setBankValue(value);
        tablePresenter.setBank(value);
    }

    //todo:I think player's bet is set twice when raised and once when called. Move this logic to call().
    @Override
    public void setBetAmount(int currentBet)
    {
        playerPresenter.setBet(currentBet);
    }

    //todo: maybe we can live without this too. Move to updatePlayersCash?
    @Override
    public void setCash(int value)
    {
        playerPresenter.setCash(value);
    }

    @Override
    public void updateTable(Table table)
    {
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                logPresenter.flop(table);
                tablePresenter.flop(table);
                break;
            case 4:
                logPresenter.turn(table);
                tablePresenter.turn(table);
                break;
            case 5:
                logPresenter.river(table);
                tablePresenter.river(table);
                break;
        }
    }

    @Override
    public void updatePlayersCash(List<Player> players)
    {
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setCash(players.get(opponentPresenters.indexOf(opponentPresenter) + 1).getCash());
        }
    }

    @Override
    public void showWinnersCombination(Combination combination)
    {
        logPresenter.printWinningCombination(combination);
    }

    //todo: there should be a game model with players models which should updateModel as the game goes on. Now it is just a wrap.
    @Override
    public void showWinnerAndHisPrize(Player player, int playerIndex, int wonAmount)
    {
        PlayerModel model = new PlayerModel(player, playerIndex);
        logPresenter.printWinningPlayer(model, wonAmount);

        if (playerIndex != 0)
        {
            opponentPresenters.get(playerIndex - 1).showWin();
        }
        else
        {
            playerPresenter.showWin();
        }
    }

    @Override
    public void showPlayersHand(int index, Hand hand)
    {
        //todo do I really need it?
        logPresenter.printPlayerHand(index, hand);
        if (index != 0)
        {
            opponentPresenters.get(index - 1).showCards(hand);
        }
    }

    @Override
    public void removeBankruptPlayer(int index)
    {
        logPresenter.printPlayerBankrupt(index);
        if (index != 0)
        {
            gameView.removePlayer(index - 1);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Sorry, but you lost all your money! Good luck next time!");
        }
    }

    @Override
    public void zeroBets()
    {
        playerPresenter.setBet(0);
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setBet(0);
        }
    }

    //todo:this is not necessary.
    @Override
    public void displayRaiseError()
    {
        logPresenter.printInvalidRaise();
    }

    @Override
    public void pause()
    {
        while (true) ;
    }
}
