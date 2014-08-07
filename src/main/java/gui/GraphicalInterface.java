package gui;

import entities.Hand;
import entities.Table;
import entities.players.Player;
import gui.presenter.LogPresenter;
import gui.presenter.OpponentPresenter;
import gui.presenter.PlayerPresenter;
import gui.presenter.TablePresenter;
import gui.view.OpponentView;
import gui.view.OpponentViewFactory;
import gui.view.PlayerView;
import gui.view.TableView;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalInterface extends TextualInterface implements Interface
{
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    private JPanel gameFieldPanel;

    private List<OpponentView> opponentViews;
    private TableView tableView;
    private PlayerView playerView;

    private TablePresenter tablePresenter;
    private List<OpponentPresenter> opponentPresenters;
    private PlayerPresenter playerPresenter;
    private LogPresenter logPresenter;

    JLabel combinationText;
    private int bankValue;

    @SuppressWarnings("unused")
    public GraphicalInterface()
    {
        createOpponents(4);
    }

    public GraphicalInterface(int numberOfOpponents)
    {
        createOpponents(numberOfOpponents);
    }

    private void createOpponents(int count)
    {
        opponentViews = new ArrayList<OpponentView>(count);
        opponentPresenters = new ArrayList<OpponentPresenter>(count);
        for (int i = 0; i < count; i++)
        {
            OpponentView view = OpponentViewFactory.createOpponentView();
            opponentViews.add(view);
            opponentPresenters.add(new OpponentPresenter(view));
        }
    }

    public void initialize()
    {
        initializeComponents();
        layout();
        addElementsToPanel();
    }

    private void layout()
    {
        frame.setTitle("Poker Graphical Interface");
        Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocation(screenSize.width / 2 - FRAME_WIDTH / 2, screenSize.height / 2 - FRAME_HEIGHT / 2);
        frame.setResizable(false);
        panel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameFieldPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT * 3 / 4 - 10);
        gameFieldPanel.setBackground(Constants.BACKGROUND_COLOR);
        logView.setBounds(5, FRAME_HEIGHT * 3 / 4 - 5, FRAME_WIDTH / 2, FRAME_HEIGHT / 4);
        decisionModuleView.setBounds(10 + FRAME_WIDTH / 2, FRAME_HEIGHT * 3 / 4 - 5, FRAME_WIDTH, FRAME_HEIGHT);
        layoutOpponents();
        combinationText.setBounds(10, 450, 100, 30);
        combinationLabel.setBounds(120, 450, 200, 30);

        playerView.setBounds(FRAME_WIDTH / 2, FRAME_HEIGHT / 4 * 3 - 50);
        tableView.setBounds(FRAME_WIDTH / 2, FRAME_HEIGHT * 5 / 16);
    }

    private void layoutOpponents()
    {
        switch (opponentViews.size())
        {
            case 1:
            {
                opponentViews.get(0).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                break;
            }
            case 2:
            {
                opponentViews.get(0).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(1).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                break;
            }
            case 3:
            {
                opponentViews.get(0).setBound(40, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                opponentViews.get(2).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 4:
            {
                opponentViews.get(0).setBound(40, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(2).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 5:
            {
                opponentViews.get(0).setBound(40, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(2).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(4).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 3 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 6:
            {
                opponentViews.get(0).setBound(40, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(40, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(2).setBound(3 * FRAME_WIDTH / 8 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(5 * FRAME_WIDTH / 8 - Constants.CARD_WIDTH, 30);
                opponentViews.get(4).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(5).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }
            case 7:
            {
                opponentViews.get(0).setBound(40, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(1).setBound(40, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(2).setBound(FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(3).setBound(FRAME_WIDTH / 2 - Constants.CARD_WIDTH, 30);
                opponentViews.get(4).setBound(3 * FRAME_WIDTH / 4 - Constants.CARD_WIDTH, 30);
                opponentViews.get(5).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 2 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                opponentViews.get(6).setBound(FRAME_WIDTH - 40 - 2 * Constants.CARD_WIDTH, 4 * FRAME_HEIGHT / 8 - Constants.CARD_HEIGHT / 2);
                break;
            }

        }
    }

    protected void initializeComponents()
    {
        super.initializeComponents();
        gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(null);
        combinationText = new JLabel("Your combination:");

        playerView = new PlayerView();
        playerPresenter = new PlayerPresenter(playerView);

        tableView = new TableView();
        tablePresenter = new TablePresenter(tableView);

        logPresenter = new LogPresenter(logView);
    }

    protected void addElementsToPanel()
    {
        super.addElementsToPanel();
        for (OpponentView opponent : opponentViews)
        {
            opponent.addToPanel(gameFieldPanel);
        }
        tableView.addToPanel(gameFieldPanel);
        playerView.addToPanel(gameFieldPanel);

        panel.add(gameFieldPanel);
    }

    @Override
    public void updateTable(Table table)
    {
        logView.printlnText(table.tableCardsToString());
        switch (table.getCardsOnTable().size())
        {
            case 0:
                break;
            case 3:
                tablePresenter.flop(table);
                break;
            case 4:
                tablePresenter.turn(table);
                break;
            case 5:
                tablePresenter.river(table);
                break;
        }
    }

    @Override
    public void prepareForRound()
    {
        setBetAmount(0);
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.setBet(0);
        }
        playerPresenter.discardCards();
        tablePresenter.discardTableCards();

    }

    @Override
    public void moveButton(int button)
    {
        logPresenter.moveButton(button);
    }

    @Override
    public void deal(List<Player> players)
    {
        logPresenter.deal();
        for (OpponentPresenter opponentPresenter : opponentPresenters)
        {
            opponentPresenter.showCardBacks();
        }
        playerPresenter.setHandCards(players.get(0).getHand());
    }

    @Override
    public void fold(int indexOfPlayer)
    {
        super.fold(indexOfPlayer);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).fold();
        }
    }

    @Override
    public void call(int indexOfPlayer, int callValue, boolean isAllIn)
    {
        super.call(indexOfPlayer, callValue, isAllIn);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).addToBet(callValue);
        }
    }

    @Override
    public void raise(int indexOfPlayer, int raiseValue, boolean isAllIn)
    {
        super.raise(indexOfPlayer, raiseValue, isAllIn);
        if (indexOfPlayer != 0)
        {
            opponentPresenters.get(indexOfPlayer - 1).addToBet(raiseValue);
        } else
        {
            playerPresenter.setBet(raiseValue);
        }
    }

    @Override
    public void betBlinds(int firstPlayerNumber, int secondPlayerNumber, int blindSize)
    {
        super.betBlinds(firstPlayerNumber, secondPlayerNumber, blindSize);
        if (firstPlayerNumber == 0)
        {
            playerPresenter.setBet(blindSize);
            opponentPresenters.get(0).setBet(blindSize * 2);
        } else if (secondPlayerNumber == 0)
        {
            playerPresenter.setBet(blindSize * 2);
            opponentPresenters.get(opponentPresenters.size() - 1).setBet(blindSize);
        } else
        {
            opponentPresenters.get(firstPlayerNumber - 1).setBet(blindSize);
            opponentPresenters.get(secondPlayerNumber - 1).setBet(blindSize * 2);
        }
    }

    @Override
    public void showPlayersHand(int index, Hand hand)
    {
        super.showPlayersHand(index, hand);
        if (index != 0)
        {
            opponentPresenters.get(index - 1).showCards(hand);
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

    @Override
    public void removeBankruptPlayer(int index)
    {
        super.removeBankruptPlayer(index);
        if (index != 0)
        {
            opponentPresenters.get(index - 1).bankrupt();
            opponentPresenters.remove(index - 1);
        } else
        {
            JOptionPane.showMessageDialog(null, "Sorry, but you lost all your money! Good luck next time!");
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

    //todo: there should be a game model with players models which should update as the game goes on. Now it is just a wrap.
    @Override
    public void showWinnerAndHisPrize(Player player, int playerIndex, int wonAmount)
    {
        super.showWinnerAndHisPrize(player, playerIndex, wonAmount);
        if (playerIndex != 0)
        {
            opponentPresenters.get(playerIndex - 1).showWin();
        } else
        {
            playerPresenter.showWin();
        }
    }

    @Override
    public void prepareForGame(List<Player> players)
    {
        setBank(0);
        updatePlayersCash(players);
    }

    @Override
    public void setCash(int value)
    {
        playerPresenter.setCash(value);
    }

    @Override
    public void setBank(int value)
    {
        bankValue = value;
        tablePresenter.setBank(value);
    }

    //todo:this is something strange:refactor!!!
    @Override
    public void setBetAmount(int currentBet)
    {
        playerPresenter.setBet(currentBet);
    }

    //todo: bank value is temp resolution and hack. Game model will solve this problem.
    @Override
    public String getDecision(int callValue, int cash, int tableCardCount)
    {
        logView.printlnText("Your turn...");
        return decisionModuleView.getDecision(callValue, cash, tableCardCount, bankValue);
    }
}
